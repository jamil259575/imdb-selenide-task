package com.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    private final SelenideElement searchInput = $("[data-testid='suggestion-search']");

    public void search(String keyword) {
        searchInput.setValue(keyword);
    }

    public SelenideElement getFirstSearchResult() {
        return $$("[data-testid='search-result--const']").first();
    }

    public String getFirstSearchResultTitle() {
        return getFirstSearchResult().$(".searchResult__constTitle").getText();
    }
}
