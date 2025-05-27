package com.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TitlePage extends BasePage {
    public SelenideElement getTitleHeading() {
        return $("[data-testid='hero__primary-text']");
    }

    public ElementsCollection getCastList() {
        return $$("[data-testid='title-cast-item']");
    }

    public SelenideElement getActorLink(int index) {
        return getCastList().get(index).$("[data-testid='title-cast-item__actor']");
    }

    public SelenideElement getTitleCast(){
        return $("[data-testid='title-cast-header']");
    }

    public void shouldHaveAtLeastCastMembers(int minCount) {
        getTitleCast().scrollIntoView(true).shouldBe(visible);
        getCastList().shouldHave(sizeGreaterThan(minCount));
    }

}
