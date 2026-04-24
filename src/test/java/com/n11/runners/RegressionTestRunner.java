package com.n11.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features  = "src/test/resources/features",
        glue      = {"com.n11.stepDefinitions"},
        plugin    = {"pretty", "html:target/regression-report/regression.html"},
        monochrome = true,
        tags = "@regression"
)
public class RegressionTestRunner extends AbstractTestNGCucumberTests {}