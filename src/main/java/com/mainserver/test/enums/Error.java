package com.mainserver.test.enums;

public enum Error {

    UserNotFound("invalid_user", "User not found"),
    InvalidParameter("invalid_parameter", "Invalid parameter(s) provided"),
    InternalServerError("internal_server_error", "General internal server error");

    private String code;
    private String description;

    Error(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
