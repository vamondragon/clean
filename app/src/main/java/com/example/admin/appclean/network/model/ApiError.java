package com.example.admin.appclean.network.model;

public class ApiError {
    private int statusCode;
    private String message;

    public ApiError() {
    }

    public ApiError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}