package de.codelix.entitymanagementsystem.http;

import lombok.Getter;

@Getter
public class HttpException extends RuntimeException{
    private final Response.Error error;

    public HttpException(Response.Error error) {
        super(error.title());
        this.error = error;
    }
}
