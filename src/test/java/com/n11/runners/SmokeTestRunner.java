package com.n11.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = {"com.n11.stepDefinitions"},
        plugin    = {"pretty", "html:target/smoke-report/smoke.html"},
        monochrome = true,
        tags = "@smoke"
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {}