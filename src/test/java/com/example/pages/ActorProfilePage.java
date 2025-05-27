package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class ActorProfilePage extends BasePage {
    public SelenideElement getActorNameHeading() {
        return $("[data-testid='hero__pageTitle']");
    }
}
