package com.selenium.support;

import com.selenium.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.setDriver(System.getProperty("browser", "chrome"));
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}