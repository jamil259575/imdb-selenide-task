package com.example.tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.testng.Assert.*;

@Epic("IMDb Test")
@Feature("Search and Actor Navigation")
public class IMDbTest extends BaseTest {

    @Test(description = "It should navigate actor page and assert the name")
    public void testIMDbSearchAndCast() throws InterruptedException {
        homePage.openUrl("/");
        homePage.acceptCookies();
        // Perform search
        homePage.search("QA");

        // Save the first result title
        var firstResult = homePage.getFirstSearchResult();
        String savedTitle = firstResult.$(".searchResult__constTitle").getText();
        firstResult.click();

        // Validate title on the resulting page
        String actualTitle = titlePage.getTitleHeading().shouldBe(Condition.visible).getText();
        assertEquals(actualTitle, savedTitle);

        // Verify top cast size
        titlePage.acceptCookies();
        titlePage.getTitleCast().scrollIntoView(true);
        titlePage.getTitleCast().shouldBe(Condition.visible);
        var castList = titlePage.getCastList().shouldHave(sizeGreaterThan(3), Duration.ofSeconds(10));
        // Get and click 3rd cast member
        var thirdActorLink = titlePage.getActorLink(2);
        String thirdActorName = thirdActorLink.getText();
        thirdActorLink.scrollIntoView(true).shouldBe(Condition.visible).click();
        // Verify actor profile
        String actorNameFromProfile = actorPage.getActorNameHeading().shouldBe(Condition.visible).getText();
        assertEquals(actorNameFromProfile, thirdActorName);
    }
}
