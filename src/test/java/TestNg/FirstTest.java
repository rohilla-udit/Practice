package TestNg;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class FirstTest {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    JavascriptExecutor js;

    String scrollElementIntoMiddle;

    @Test (priority = 1)
    void openApp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.nykaa.com/");
        driver.manage().window().maximize();
    }

    @Test (priority = 2)
    void searchProduct() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);

        action.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Search on Nykaa']")))
                .click().build().perform();
        System.out.println("Opening Search Box");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        action.sendKeys("Hair Mask").build().perform();
        System.out.println("Entered Product Name");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        action.sendKeys(Keys.RETURN).build().perform();
        System.out.println("Searching Product...");
        Thread.sleep(2000);
    }

    @Test (priority = 3)
    void openPDP() throws InterruptedException {

        // Wait for search result to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-1rd7vky']")));
        System.out.println("PLP Displayed");

        // Locating 2nd element using xpath
        WebElement secondProduct = driver.findElement(By.xpath("(//div[@class='css-1rd7vky'])[2]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='css-1rd7vky'])[2]")));

        // Scroll to secondProduct using JavaScript
        js = (JavascriptExecutor) driver;
        scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        System.out.println("Scrolling to search 2nd Product");
        js.executeScript(scrollElementIntoMiddle, secondProduct);
        wait.until(ExpectedConditions.elementToBeClickable(secondProduct));

        // Move over secondProduct and click
        System.out.println("Moving Cursor to 2nd Product");
        action.moveToElement(secondProduct).click().build().perform();
        System.out.println("Clicked on Product");
        Thread.sleep(2000);
    }

    @Test (priority = 4)
    void addToBag() throws InterruptedException {
        // Switching Window
        Object[] windowHandles = driver.getWindowHandles().toArray();
        Thread.sleep(2000);
        driver.switchTo().window((String) windowHandles[1]);
        System.out.println("Switching Window");

        // Scrolling Add to Bag in the Center of Page
        WebElement addToBag = driver.findElement(By.xpath("//div[@class='css-f81pz4']//button[@class=' css-13zjqg6']"));

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, addToBag);
        Thread.sleep(2000);

        // PDP to Cart
        action.moveToElement(addToBag).click().build().perform();
        System.out.println("Added to Cart");
    }

    @Test (priority = 5)
    void verifyCart() {
        // Verifying Whether Product is Successfully Added to Cart or Not
        WebElement cartNum = driver.findElement(By.xpath("//span[@class='cart-count']"));
        String cartCount = cartNum.getText();
        if(Integer.parseInt(cartCount) > 0) {
            System.out.println("Product Successfully Verified & Added to Cart");
        } else {
            System.out.println("Product Not Added to cart");
        }
    }

    @Test (priority = 6)
    void quitApp() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
