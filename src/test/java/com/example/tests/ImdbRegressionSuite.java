package com.example.tests;

import com.codeborne.selenide.Condition;

import com.example.dataprovider.TestDataProvider;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.time.Duration;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.testng.Assert.*;

@Epic("IMDb Test")
@Feature("Search and Actor Profile Navigation")
public class ImdbRegressionSuite extends BaseTest {
    @Test(dataProvider = "searchTerms", dataProviderClass = TestDataProvider.class, groups = {"regression"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search for a title, open its page, then open the 3rd top cast profile and verify actor name matches.")
    public void itShouldOpenActorProfileAndVerifyActorName(String searchTerm) {
        homePage.search(searchTerm);
        String savedTitle = homePage.getFirstSearchResultTitle();
        homePage.cLickFirstSearchResult();

        String actualTitle = titlePage.getTitleHeading()
                .shouldBe(Condition.visible)
                .getText();
        assertEquals(actualTitle, savedTitle);

        titlePage.acceptCookies();
        titlePage.getTitleCast().scrollIntoView(true).shouldBe(Condition.visible,Duration.ofSeconds(3));
        titlePage.getCastList().shouldHave(sizeGreaterThan(3), Duration.ofSeconds(12));

        String thirdActorName = titlePage.getActorNameByIndex(2);
        titlePage.clickActorByIndex(2);

        String actorNameFromProfile = actorPage.getActorNameHeading()
                .shouldBe(Condition.visible)
                .getText();
        assertEquals(actorNameFromProfile, thirdActorName);
    }
}
