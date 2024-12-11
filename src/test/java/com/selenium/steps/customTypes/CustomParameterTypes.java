package com.selenium.steps.customTypes;

import com.selenium.enums.Routes;
import io.cucumber.java.ParameterType;

public class CustomParameterTypes {

    @ParameterType("[a-zA-Z]+")
    public Routes route(String routeName) {
        try {
            return Routes.valueOf(routeName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown route: " + routeName, e);
        }
    }
}
