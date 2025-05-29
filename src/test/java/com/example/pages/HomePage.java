package com.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    private final SelenideElement searchInput = $("[data-testid='suggestion-search']");

    @Step("Search for {keyword}")
    public void search(String keyword) {
        searchInput.setValue(keyword);
    }
    @Step("Get first search result")
    public SelenideElement getFirstSearchResult() {
        return $$("[data-testid='search-result--const']").first().shouldBe(Condition.visible);
    }

    @Step("Save first search result title")
    public String getFirstSearchResultTitle() {
        return getFirstSearchResult().$(".searchResult__constTitle").getText();
    }

    @Step("Click the 1st search result")
    public void cLickFirstSearchResult() {
        getFirstSearchResult().click();
    }
}
