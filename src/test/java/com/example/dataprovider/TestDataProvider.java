package com.example.dataprovider;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "searchTerms")
    public static Object[][] searchTerms() {
        return new Object[][] {
                {"QA"}
        };
    }
}
