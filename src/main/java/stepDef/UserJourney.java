package stepDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.function.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserJourney
{
    WebDriver driver;
    int inCart;

    @Given("^user is on the webpage$")
    public void user_is_on_the_webpage()
    {
        WebDriverManager
                .chromedriver()
                .setup();
        driver = new ChromeDriver();
        driver.
                get("https://skyronic.github.io/vue-spa/#/");
        driver.manage().window().maximize();

    }

    @When("^user see the results list$")
    public void user_see_the_results_list()  {
     driver.manage()
             .timeouts().implicitlyWait(10, TimeUnit.SECONDS);

     List<WebElement> items =
             driver
             .findElements(By.cssSelector("div[class='product']"));
        for (WebElement result : items) {
            System.out.println("Product list : "+result.getText());
        }
    }

    @Then("^user verifies the item$")
    public void user_verifies_the_item() {
        //declare array list object
        List<String> actualResult = new ArrayList<>();
        List<String> expectedResult =
                Arrays.
                        asList("iPad 4 Mini\n$ 500.01", "H&M T-Shirt White\n$ 10.99", "Charli XCX - Sucker CD\n$ 19.99");
        List<WebElement> items =
                driver
                        .findElements(By.cssSelector("div[class='product']"));
        for (WebElement result : items) {
            actualResult.
                    add(result.getText());
        }
            Assert.
                    assertEquals(actualResult, expectedResult);

    }

    @When("^user clicks on(.*)$")
    public void user_clicks_on(String product)  {
        driver.manage().
                timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> items =
                driver
                        .findElements(By.cssSelector("a[class='title']"));
       items.
               get(0).click();
    }

    @When("^user gets item information$")
    public void user_gets_item_information()  {
       String actualinfo =
               driver.
                       findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div")).getText();
       String expInfo = "In Stock: 2";
       Assert.
               assertEquals(actualinfo,expInfo);
        System.out.println("Number of items available " +actualinfo);
       boolean button =
               driver.
                       findElement(By.className("add-button")).isDisplayed();
        System.out.println("Add to card button is available "+button);
    }

    @When("^user adds to cart$")
    public void user_adds_to_cart() {
        driver.
                findElement(By.className("add-button")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
              driver.
                findElement(By.className("add-button")).click();

    }

    @When("^cart is updated$")
    public void cart_is_updated()  {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element =
                wait.
                        until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/div/a[2]")));
        System.out.println(element.getText());
        String actual = "Cart (2)";
        Assert.
                assertEquals(actual,element.getText());
    }

    @Then("^user verifies the stock$")
    public void user_verifies_the_stock()  {
        List<WebElement> noOfItems = driver.findElements(By.className("inventory"));
        String item = noOfItems.get(0).getText();
        String inStockStr = item.replaceAll("In Stock: ", "");
        int inStock =
                Integer.parseInt(inStockStr);


       if (inStock==0){
           System.out.println("we have " + item + " of iPad Mini");
       }

    }

    @And("^quit$")
    public void quit() {
        driver.quit();
    }
}