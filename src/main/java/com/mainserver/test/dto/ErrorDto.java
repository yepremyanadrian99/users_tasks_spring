package com.mainserver.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDto {
    @JsonProperty("error_code")
    private String code;
    @JsonProperty("error_description")
    private String description;

    public ErrorDto() {
    }

    public ErrorDto(String code, String description) {
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
