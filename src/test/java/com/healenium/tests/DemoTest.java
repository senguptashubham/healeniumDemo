package com.healenium.tests;

import com.epam.healenium.SelfHealingDriver;
import com.healenium.pageObjects.pages.MarkupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoTest {
    static SelfHealingDriver driver;
    @BeforeTest
    public static void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadTimeout(Duration.ofMinutes(1));
        WebDriver delegate = new ChromeDriver();
        driver = SelfHealingDriver.create(delegate);
    }

    @AfterTest
    public static void quitDriver(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public static void markupPageTest(){
        MarkupPage markupPage = new MarkupPage(driver);
        markupPage.openPage().fillInputBox1("Test 1st time").clickTestButton();
        markupPage.confirmAlert();
        markupPage.generateMarkup().fillInputBox1("Test 2nd time").clickTestButton();
        markupPage.confirmAlert();

    }

}
