package org.example.untitled;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CompetitionsTests {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    @Test
    public void checkIfMlodzik() throws Exception {
        setUp();
        fillElementsOnPage("Adam", "Kowalski", "26-08-2008", true, true);
        sendApplication();
        closeAlert();
        assertEquals("Mlodzik", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfSenior() throws Exception {
        setUp();
        fillElementsOnPage("Adam", "Kowalski", "16-11-1940", false, true);
        sendApplication();
        closeAlert();
        assertEquals("Senior", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfJunior() throws Exception {
        setUp();
        fillElementsOnPage("Agata", "Nowakowska", "16-11-2005", true, true);
        sendApplication();
        closeAlert();
        assertEquals("Junior", closeAlertAndGetItsText());
        afterTest();
    }


    @Test
    public void checkIfDorosly() throws Exception {
        setUp();
        fillElementsOnPage("Jan", "Nowak", "26-04-1978", false, false);
        sendApplication();
        closeAlert();
        assertEquals("Dorosly", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfDorosly2() throws Exception {
        setUp();
        fillElementsOnPage("Paweł", "Adam", "16-11-1974", false, false);
        sendApplication();
        closeAlert();
        assertEquals("Dorosly", closeAlertAndGetItsText());
        afterTest();
    }


    @Test
    public void checkIfDorosly3() throws Exception {
        setUp();
        fillElementsOnPage("Adrian", "Tomaszewski", "16-11-2001", false, false);
        sendApplication();
        closeAlert();
        assertEquals("Dorosly", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfDorosly4() throws Exception {
        setUp();
        fillElementsOnPage("Jan", "Nowak", "26-08-2002", false, false);
        sendApplication();
        closeAlert();
        assertEquals("Dorosly", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfTooYoung() throws Exception {
        setUp();
        fillElementsOnPage("Anna", "Sobczyk", "16-11-2012", true, true);
        sendApplication();
        closeAlert();
        assertEquals("Brak kwalifikacji", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfNoSurname() throws Exception {
        setUp();
        fillElementsOnPage("Weronika", null, "22-10-1982", false, false);
        sendApplication();
        assertEquals("Nazwisko musi byc wypelnione", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfNoFirstName() throws Exception {
        setUp();
        fillElementsOnPage(null, "Kowal", "21-11-1977", false, false);
        sendApplication();
        assertEquals("First name must be filled out", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfNoBirthDate() throws Exception {
        setUp();
        fillElementsOnPage("Tomek", "Paweł", null, false, false);
        sendApplication();
        assertEquals("Data urodzenia nie moze byc pusta", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfWrongData() throws Exception {
        setUp();
        fillElementsOnPage("Tomek", "Nowak", "16-11-1940", false, false);
        sendApplication();
        closeAlert();
        assertEquals("Blad danych", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfWrongData2() throws Exception {
        setUp();
        fillElementsOnPage("Kamil", "Noser", "18-01-2008", true, false);
        sendApplication();
        closeAlert();
        assertEquals("Blad danych", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfWrongData3() throws Exception {
        setUp();
        fillElementsOnPage("Daria", "Nowak", "16-11-2010", false, false);
        sendApplication();
        closeAlert();
        assertEquals("Blad danych", closeAlertAndGetItsText());
        afterTest();
    }

    @Test
    public void checkIfWrongData4() throws Exception {
        setUp();
        fillElementsOnPage("Natalia", "Styrna", "12-10-2003", true, false);
        sendApplication();
        closeAlert();
        assertEquals("Blad danych", closeAlertAndGetItsText());
        afterTest();
    }

    private void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "C:\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://lamp.ii.us.edu.pl/~mtdyd/zawody/");
    }

    private void fillElementsOnPage(String name, String surename, String birthDate, boolean parentalConsent, boolean medicalStatement) {

        if (name != null) {
            driver.findElement(By.id("inputEmail3")).sendKeys(name);
        }

        if (surename != null) {
            driver.findElement(By.id("inputPassword3")).sendKeys(surename);
        }

        if (birthDate != null) {
            driver.findElement(By.id("dataU")).sendKeys(birthDate);
        }

        if (parentalConsent) {
            driver.findElement(By.id("rodzice")).click();
        }

        if (medicalStatement) {
            driver.findElement(By.id("lekarz")).click();
        }

    }

    private void sendApplication(){
        driver.findElement(By.xpath("//button[@type='button']")).click();
    }

    private void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    private void afterTest() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
