package com.example.tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static org.testng.Assert.*;

@Epic("IMDb Test")
@Feature("Search and Actor Profile Navigation")
public class ImdbRegressionSuite extends BaseTest {
    @DataProvider(name = "searchTerms")
    public Object[][] searchTerms() {
        return new Object[][]{
                {"QA"}
        };
    }

    @Test(dataProvider = "searchTerms")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search for a title, open its page, then open the 3rd top cast profile and verify actor name matches.")
    public void itShouldOpenActorProfileAndVerifyActorName(String searchTerm) {
        // Open homepage
        homePage.openUrl("/");
        // Perform search
        homePage.search(searchTerm);
        // Save the first result title
        var firstResult = homePage.getFirstSearchResult();
        String savedTitle = firstResult.$(".searchResult__constTitle").getText();
        firstResult.click();

        // Validate title on the resulting page
        String actualTitle = titlePage.getTitleHeading()
                .shouldBe(Condition.visible)
                .getText();
        assertEquals(actualTitle, savedTitle);

        // Accept cookies and scroll to cast
        titlePage.acceptCookies();
        titlePage.getTitleCast().scrollIntoView(true).shouldBe(Condition.visible);

        // Validate cast list is more than 3
        var castList = titlePage.getCastList()
                .shouldHave(sizeGreaterThan(3), Duration.ofSeconds(10));

        // Get and click 3rd cast member
        var thirdActorLink = titlePage.getActorLink(2);
        String thirdActorName = thirdActorLink.getText();
        thirdActorLink.scrollIntoView(true).shouldBe(Condition.visible).click();

        // Verify actor profile
        String actorNameFromProfile = actorPage.getActorNameHeading()
                .shouldBe(Condition.visible)
                .getText();
        assertEquals(actorNameFromProfile, thirdActorName);
    }
}
