package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.example.config.Config;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    private final SelenideElement acceptCookiesButton = $("[data-testid='accept-button']");

    @Step("Accept cookies")
    public void acceptCookies() {
        if(acceptCookiesButton.exists()){
            acceptCookiesButton.click();
        }
    }

    protected Duration defaultTimeout() {
        return Duration.ofMillis(Config.getInt("timeout"));
    }
}
