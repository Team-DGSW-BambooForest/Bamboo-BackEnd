package com.bamboo.userservice.global.enums;

import lombok.Getter;

@Getter
public enum EndPoint {
    AUTH("http://dauth.b1nd.com/api"),
    OPEN_API("http://open.dodam.b1nd.com/api");

    private final String endPoint;

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
