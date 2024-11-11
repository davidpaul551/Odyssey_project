package Odyssey.Odyssey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Project {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Project_POM obj = new Project_POM(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String projectPath = System.getProperty("user.dir");
        System.out.println("Project path is: " + projectPath);

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("report.html");
        extent.attachReporter(spark);

        ExtentTest test1 = extent.createTest("Test Case 1 - Account Creation");

        FileInputStream input = new FileInputStream("C:\\Users\\david.doggala\\eclipse-workspace\\Odyssey\\Details.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(input);

        Sheet createAccountSheet = workbook.getSheet("Create Account");
        if (createAccountSheet == null) {
            System.out.println("The sheet 'Create Account' was not found in the Excel file.");
            workbook.close();
            return;
        }

        int noOfRows = createAccountSheet.getPhysicalNumberOfRows();
        for (int i = 1; i < noOfRows; i++) {
            String firstName = createAccountSheet.getRow(i).getCell(0).getStringCellValue();
            String lastName = createAccountSheet.getRow(i).getCell(1).getStringCellValue();
            String userName = createAccountSheet.getRow(i).getCell(2).getStringCellValue();
            String password = createAccountSheet.getRow(i).getCell(3).getStringCellValue();

            driver.get("https://odyssey.in");
            Thread.sleep(3000);
            driver.manage().window().maximize();
            Thread.sleep(3000);
            obj.clickMyAccountButton();
            obj.clickCreateAccountButton();
            obj.enterFirstName(firstName);
            Thread.sleep(5000);
            obj.enterLastName(lastName);
            Thread.sleep(5000);

            obj.enterEmail(userName);
            Thread.sleep(5000);

            obj.enterPassword(password);

            obj.clickMyAccountButtonenter();
            
            obj.clickMyAccountButton();
            boolean isLogoutButtonVisible = obj.isLogoutButtonDisplayed();
            if (isLogoutButtonVisible) {
                String screenshotpath1 =captureScreenshot(driver, "Account_Creation_Passed.jpg");

                test1.pass("Logout button is displayed. Account creation is successful.").addScreenCaptureFromPath(screenshotpath1);
                System.out.println("Test passed for account creation");
            } else {
                test1.fail("Login button is not displayed. Account creation or login failed.");
            }
        }
        
        ExtentTest test2 = extent.createTest("Test Case 2 - Logout Functionality");

        obj.clickLogoutButton();
        obj.clickMyAccountButton();
        

        boolean isLogoutSuccess = obj.isLoginButtonDisplayed();
        if (isLogoutSuccess) {
            String screenshotpath2 =captureScreenshot(driver, "logout_success.jpg");

            test2.pass("Logout successful.").addScreenCaptureFromPath(screenshotpath2);
            System.out.println("Logout is successful");

        } else {
            test2.fail("Logout failed.");
        }

           
        

        ExtentTest test3 = extent.createTest("Test Case 3 - Login using Existing User");
        Sheet loginSheet = workbook.getSheet("Login");
        if (loginSheet == null) {
            System.out.println("The sheet 'Login' was not found in the Excel file.");
            workbook.close();
            return;
        }

        int noOfRows1 = loginSheet.getPhysicalNumberOfRows();
        for (int j = 1; j < noOfRows1; j++) {
            String loginUserName = loginSheet.getRow(j).getCell(0).getStringCellValue();
            String loginPassword = loginSheet.getRow(j).getCell(1).getStringCellValue();
            System.out.println(loginUserName);
            System.out.println(loginPassword);

            
            
            Thread.sleep(3000);
            obj.clickMyAccountButton();
            Thread.sleep(3000);
            obj.clickMyAccountButton();
            Thread.sleep(3000);
            obj.enterEmailalready(loginUserName);

            Thread.sleep(3000);
            obj.enterPasswordalready(loginPassword);

            Thread.sleep(3000);

            obj.clickLoginButton();
            
            obj.clickMyAccountButton1();
            boolean isLogoutButtonVisible = obj.isLogoutButtonDisplayed();
            if (isLogoutButtonVisible) {
                String screenshotpath3 =captureScreenshot(driver, "login_passed.jpg");

                test3.pass("Logout button is displayed.  Login successful.").addScreenCaptureFromPath(screenshotpath3);
                System.out.println("Test passsed for Login");
            } else {
                test3.fail("Login button is not displayed. Account creation or login failed.");
            }

           
        }

        
        extent.flush();
        workbook.close(); 
        driver.quit(); 
    }

    private static String captureScreenshot(WebDriver driver, String screenshotName) {
        String projectPath = System.getProperty("user.dir"); 
        String screenshotPath = projectPath + "/screenshots/" + screenshotName;
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (IOException e) {
            System.out.println("Error while capturing screenshot: " + e.getMessage());
        }
        return screenshotPath; 
    }

}
