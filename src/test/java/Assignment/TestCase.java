package Assignment;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase {
    WebDriver driver;
    String Prod_Name;
    
    @BeforeClass
    void openApp() {
        driver = new ChromeDriver();
        System.out.println("Browser opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.nykaa.com/");
        System.out.println("Nykaa opened");
    }
    
    @Test(priority = 1)
    void searchingProduct() {
        SearchProd search = new SearchProd(driver);
        search.SearchAProduct("hair serum");
        System.out.println("Searching product");
    }
    
    @Test(priority=2)
    void addToCart() throws InterruptedException {
        AddToCart add = new AddToCart(driver);
        add.selectProd();
        System.out.println("Selecting Product");
        Thread.sleep(2000);
        add.switchWindow();
        System.out.println("Window Switched");
        //Getting Product name form PDP
        Prod_Name = add.getName();
        add.addingToCart();
    }
    
    @Test(priority=3)
    void verifyProduct() throws InterruptedException {
        VerifyCart verify = new VerifyCart(driver);
        verify.opening_cart();
        //Verify product name from PDP to Cart
        verify.verifying_Product(Prod_Name);
    }

    @AfterClass
    void quit() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}