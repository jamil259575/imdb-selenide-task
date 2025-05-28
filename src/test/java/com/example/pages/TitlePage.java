package com.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class TitlePage extends BasePage {
    public SelenideElement getTitleHeading() {
        return $("[data-testid='hero__primary-text']");
    }

    public ElementsCollection getCastList() {
        return $$("[data-testid='title-cast-item']");
    }

    public SelenideElement getTitleCast(){
        return $("[data-testid='title-cast-header']");
    }

    public String getActorNameByIndex(int index) {
        return getCastList()
                .get(index)
                .$("[data-testid='title-cast-item__actor']")
                .getText();
    }

    public void clickActorByIndex(int index) {
        getCastList()
                .get(index)
                .$("[data-testid='title-cast-item__actor']")
                .scrollIntoView(true)
                .shouldBe(Condition.visible)
                .click();
    }

}
