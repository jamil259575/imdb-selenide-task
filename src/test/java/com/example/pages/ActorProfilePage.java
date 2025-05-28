package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ActorProfilePage extends BasePage {

    @Step("Get actor name from the actor page")
    public SelenideElement getActorNameHeading() {
        return $("[data-testid='hero__pageTitle']");
    }
}
