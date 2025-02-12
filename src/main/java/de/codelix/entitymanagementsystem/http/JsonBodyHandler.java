package de.codelix.entitymanagementsystem.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<W> {
    private static final ObjectMapper OM = new ObjectMapper();

    static {
        OM.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    private static <W> HttpResponse.BodySubscriber<W> asJSON(TypeReference<Response<W>> targetType, HttpResponse.ResponseInfo info) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, targetType, info));
    }

    private static <W> W toSupplierOfType(InputStream inputStream, TypeReference<Response<W>> targetType, HttpResponse.ResponseInfo info) {
        try (InputStream stream = inputStream) {
            String data = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            if (info.statusCode() != 200) {
                Response.Error error = OM.readValue(data, Response.Error.class);
                throw new HttpException(error);
            }
            Response<W> response = OM.readValue(data, targetType);
            return response.data();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private final TypeReference<Response<W>> ref;

    public JsonBodyHandler(TypeReference<Response<W>> ref) {
        this.ref = ref;
    }

    @Override
    public HttpResponse.BodySubscriber<W> apply(HttpResponse.ResponseInfo responseInfo) {
        return asJSON(ref, responseInfo);
    }
}
