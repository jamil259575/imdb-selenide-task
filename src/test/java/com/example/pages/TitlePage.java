package com.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class TitlePage extends BasePage {

    @Step("Get title heading")
    public SelenideElement getTitleHeading() {
        return $("[data-testid='hero__primary-text']");
    }

    @Step("Get cast list")
    public ElementsCollection getCastList() {
        return $$("[data-testid='title-cast-item']");
    }

    @Step("Get cast title")
    public SelenideElement getTitleCast(){
        return $("[data-testid='title-cast-header']");
    }

    @Step("Get actor name by index")
    public String getActorNameByIndex(int index) {
        return getCastList()
                .get(index)
                .$("[data-testid='title-cast-item__actor']")
                .getText();
    }

    @Step("Click actor by index")
    public void clickActorByIndex(int index) {
        getCastList()
                .get(index)
                .$("[data-testid='title-cast-item__actor']")
                .scrollIntoView(true)
                .shouldBe(Condition.visible)
                .click();
    }

}
