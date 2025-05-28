package com.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.example.config.Config;
import com.example.pages.ActorProfilePage;
import com.example.pages.HomePage;
import com.example.pages.TitlePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;

public class BaseTest {

    protected HomePage homePage;
    protected TitlePage titlePage;
    protected ActorProfilePage actorPage;

    @BeforeMethod
    public void setUp() {
        configureBrowser();
        initPages();
    }

    private void configureBrowser() {
        Configuration.browser = Config.get("browser");
        Configuration.timeout = Config.getInt("timeout");
        Configuration.headless = Config.getBoolean("headless");
        switch (Configuration.browser) {
            case "chrome" -> {
                Configuration.browserCapabilities = getOptions();
            }

            case "firefox" -> {
                org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new org.openqa.selenium.firefox.FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080");
                Configuration.browserCapabilities = firefoxOptions;
            }

            default -> throw new IllegalArgumentException("Unsupported browser: " + Configuration.browser);
        }
    }

    private static ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36"
        );
        return chromeOptions;
    }

    private void initPages() {
        homePage = new HomePage();
        titlePage = new TitlePage();
        actorPage = new ActorProfilePage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            attachScreenshotToAllure();
        }
        Selenide.closeWebDriver();
    }

    private void attachScreenshotToAllure() {
        try {
            File screenshot = takeScreenShotAsFile();
            if (screenshot != null && screenshot.exists()) {
                Allure.addAttachment("Screenshot on Failure", new FileInputStream(screenshot));
            }
        } catch (Exception e) {
            System.err.println("Capture failed: " + e.getMessage());
        }
    }
}
