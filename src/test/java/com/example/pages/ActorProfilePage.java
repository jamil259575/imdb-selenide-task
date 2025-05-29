package com.example.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ActorProfilePage extends BasePage {

    @Step("Get actor name from the actor page")
    public String getActorNameHeading() {
        return $("[data-testid='hero__pageTitle']").shouldBe(Condition.visible).getText();
    }
}
