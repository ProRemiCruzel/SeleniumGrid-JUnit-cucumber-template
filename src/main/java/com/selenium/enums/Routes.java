package com.selenium.enums;

import lombok.Getter;

@Getter
public enum Routes {
    LOGIN("/"),
    INVENTORY("/inventory.html");

    private final String endpoint;

    Routes(String endpoint) {
        this.endpoint = endpoint;
    }

}
