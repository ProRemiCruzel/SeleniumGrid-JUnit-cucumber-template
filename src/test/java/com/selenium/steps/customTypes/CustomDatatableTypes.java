package com.selenium.steps.customTypes;

import com.selenium.models.UserCredentials;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomDatatableTypes {

    @DataTableType
    public UserCredentials userCredentials(Map<String, String> entry) {
        return new UserCredentials(entry.get("username"), entry.get("password"));
    }
}
