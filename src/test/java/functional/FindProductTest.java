package functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.GoodsPage;
import pages.HomePage;
import selenium.WebDriverFactory;
import utils.Log4Test;
import utils.PropertyLoader;

import java.util.concurrent.TimeUnit;

/**
 * Created by bionic on 11/5/14.
 */
public class FindProductTest {

    private static WebDriver driver;

    @DataProvider
    public Object [][] positive() {
        return new Object[][] {
                new Object[] {"Nexus", "Nexus"},

        };
    }
    @DataProvider
    public Object [][] negative() {
        return new Object[][] {
                new Object[] {"CSDc", "CSDc"},

        };
    }

    @BeforeSuite
    public void setEnv()
    {
        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
    }
    @Test (dataProvider = "positive")
    public void findProductPos(String searchQuery, String verificationName)

    {
        Log4Test.info("Starting find product positive test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        GoodsPage goodsPage = homePage.findProduct(searchQuery);

        Assert.assertTrue(goodsPage.isFound(verificationName), "Failed to find product");

    }
    @Test (dataProvider = "negative")
    public void findProductNeg(String searchQuery, String verificationName)

    {
        Log4Test.info("Starting find product negative test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        GoodsPage goodsPage = homePage.findProduct(searchQuery);

        Assert.assertFalse(goodsPage.isFound(verificationName), "Nagative test failed - found unreal product");

    }
        @AfterSuite
        public void closeEnv()
        {
            if (driver!=null)
            {
                driver.quit();
            }
        }
}
