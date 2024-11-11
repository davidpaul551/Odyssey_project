package Odyssey.Odyssey;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Project_POM {

	WebDriver driver;
    WebDriverWait wait;

	By myacc = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]");
	By createacc = By.xpath("//*[@id=\"header-login-panel\"]/div/div/p[1]/button");
	By fnametextbox = By.xpath("//*[@id=\"register-customer[first_name]\"]");
	By lnametextbox = By.xpath("//*[@id=\"register-customer[last_name]\"]");
	By emailcreatetextbox = By.xpath("//*[@id=\"register-customer[email]\"]");
	By passcreatetextbox = By.xpath("//*[@id=\"register-customer[password]\"]");
	By createmyaccountenter = By.xpath("//*[@id=\"create_customer\"]/button");
	 By emailTextbox = By.xpath("//*[@id=\"login-customer[email]\"]");
     By passwordTextbox = By.xpath("//*[@id=\"login-customer[password]\"]");
     By loginButton = By.xpath("//*[@id=\"header_customer_login\"]/button");
     
      By myAccountButton = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[3]/div[2]/div/a[2]");
      By logoutButton = By.xpath("//*[@id=\"account-popover\"]/div/div/a[4]");
      public Project_POM(WebDriver driver) {
		// TODO Auto-generated constructor stub
    	  this.driver = driver;
          this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize wait here
          if (this.driver == null) {
              System.out.println("Driver is null!");
          } else {
              System.out.println("Driver is initialized successfully.");
          }
	}

      public void clickMyAccountButtonenter() {
          driver.findElement(createmyaccountenter).click();
      }

      public void clickMyAccountButton() {
    	    WebElement myAccountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(myacc));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAccountElement);
    	}


      public void clickCreateAccountButton() {
          driver.findElement(createacc).click();
      }

      public void enterFirstName(String firstName) {
          driver.findElement(fnametextbox).sendKeys(firstName);
      }
      public void enterLastName(String lastName) {
          driver.findElement(lnametextbox).sendKeys(lastName);
      }

      public void enterEmail(String email) {
          driver.findElement(emailcreatetextbox).sendKeys(email);
      }

      public void enterPassword(String password) {
          driver.findElement(passcreatetextbox).sendKeys(password);
      }
      
      
      

      public void enterEmailalready(String email) {
          wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextbox)).sendKeys(email);
      }

      public void enterPasswordalready(String password) {
          wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTextbox)).sendKeys(password);
      }

      public void clickLoginButton() {
          wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
      }
      
      public void clickMyAccountButton1() {
          driver.findElement(myAccountButton).click();
      }

      public void clickLogoutButton() {
          driver.findElement(logoutButton).click();
      }
      public boolean isLoginButtonDisplayed() {
    	    try {
    	        return driver.findElement(loginButton).isDisplayed();
    	    } catch (NoSuchElementException e) {
    	        return false;
    	    }
    	}
      public boolean isLogoutButtonDisplayed() {
  	    try {
  	        return driver.findElement(logoutButton).isDisplayed();
  	    } catch (NoSuchElementException e) {
  	        return false; 
  	    }
  	}

}
