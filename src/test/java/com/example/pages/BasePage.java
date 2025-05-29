package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    private final SelenideElement acceptCookiesButton = $("[data-testid='accept-button']");

    @Step("Accept cookies")
    public void acceptCookies() {
        if (acceptCookiesButton.exists()) {
            acceptCookiesButton.click();
        }
    }
}
