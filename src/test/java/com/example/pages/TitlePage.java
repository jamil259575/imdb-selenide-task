package com.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class TitlePage extends BasePage {

    @Step("Get title heading")
    public String getTitleHeading() {
        return $("[data-testid='hero__primary-text']")
                .shouldBe(Condition.visible)
                .getText();
    }


    public ElementsCollection getCastMembersList() {
        return $$("[data-testid='title-cast-item']")
                .filter(Condition.visible)
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(3));
    }

    @Step("Verify that cast list size")
    public void verifyCastListSize(int size ) {
        $$("[data-testid='title-cast-item']").filter(Condition.visible)
                .shouldHave(sizeGreaterThan(size), defaultTimeout());
    }

    @Step("Scroll to cast list")
    public void scrollToCastList(){
        $("[data-testid='title-cast']")
                .scrollIntoView(true)
                .shouldBe(Condition.visible, defaultTimeout());
    }

    @Step("Get actor name by index")
    public String getActorNameByIndex(int index) {
        return getCastMembersList()
                .get(index)
                .$("[data-testid='title-cast-item__actor']")
                .getText();
    }

    @Step("Click actor by index")
    public void clickActorByIndex(int index) {
        getCastMembersList()
                .get(index)
                .$("[data-testid='title-cast-item__actor']")
                .scrollIntoView(true)
                .shouldBe(Condition.visible, defaultTimeout())
                .click();
    }
}
