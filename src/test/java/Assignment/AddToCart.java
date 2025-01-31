package Assignment;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {
    WebDriver driver; 
    WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(10));
    
    //Constructor
    AddToCart(WebDriver driver){
     this.driver=driver;
     PageFactory.initElements(driver,this);
    }

    // 2nd Product
    @FindBy(xpath="(//div[@class='css-1rd7vky'])[2]")
    WebElement desiredProduct;
   
    // Product Name
    @FindBy(xpath="//div[@class='css-1d5wdox']/h1")
    WebElement Prod_Name;

    // Add To Cart
    @FindBy(xpath="//div[@class='css-vp18r8']/button")
    WebElement adding_product;
    
    // Action for selecting the 2nd product
    public void selectProd() {
        desiredProduct.click();
    }
    
    // Function for giving name of product to main file
    public String getName() {
        return Prod_Name.getText();
    }
    
    // switch window
    public void switchWindow() {
        Set<String> ids = driver.getWindowHandles();
        for(String id : ids) {
            driver.switchTo().window(id);
        }
    }

    //Action for adding the item to cart
    public void addingToCart() {
        myWait.until(ExpectedConditions.elementToBeClickable(adding_product));
        adding_product.click();
    }
}