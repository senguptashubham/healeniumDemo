package com.healenium.pageObjects.testEnv;

import com.epam.healenium.SelfHealingDriver;
import com.healenium.pageObjects.FrameworkBasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumBasePage extends FrameworkBasePage {
    protected String mainPageUrl = "https://elenastepuro.github.io/test_env/index.html";
    protected SelfHealingDriver driver;

    public SeleniumBasePage(SelfHealingDriver driver) {
        this.driver = driver;
    }

    public static void sleepForSecondsToSeeTheAlertWhileTestIsRunning(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getAlertText() {
        sleepForSecondsToSeeTheAlertWhileTestIsRunning(1);
        String foundAlertText = "";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ZERO /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlertText = driver.switchTo().alert().getText();
        } catch (TimeoutException eTO) {
            foundAlertText = "no alert text";
        }
        return foundAlertText;
    }

    public SeleniumBasePage confirmAlert() {
        String windowHandle=driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30) /*timeout in seconds*/);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().window(windowHandle);
        return this;
    }
}
