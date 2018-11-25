package com.sgic.automation.demo.test;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(
        features = {"src/test/resources/features/"},
        glue = {"com.sgic.automation.demo.steps"},
        tags= {"@invalidCredentials"}
        )
@RunWith(Cucumber.class)
public class TestRunenr {

}
