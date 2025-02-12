package de.codelix.entitymanagementsystem.http;

public record Response<T>(de.codelix.entitymanagementsystem.http.Response.Error error, T data) {
    public record Error(String title, String detail, int status) {}
}
