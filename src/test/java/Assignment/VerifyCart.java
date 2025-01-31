package Assignment;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class VerifyCart {
   WebDriver driver;
   WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));

   // Constructor
   VerifyCart(WebDriver driver){
       this.driver=driver;
       PageFactory.initElements(driver,this);
   }

   //Locators
   @FindBy(xpath="//*[@id=\"header-bag-icon\"]")
   WebElement open_cart;

   // Prod Name
   @FindBy(xpath="//div[@class=\"css-18cknfl eh6wigd2\"]/span")
   WebElement product;
   
   // Prod Size
   @FindBy(xpath="//div[@class=\"css-1no8deu eh6wigd3\"]/p")
   WebElement size;
   
   //Actions
   public void opening_cart() {
       WebElement action = myWait.until(ExpectedConditions.elementToBeClickable(open_cart));
       action.click();
   }
   
   public void verifying_Product(String prodName) {
      driver.switchTo().frame(0);
      String rem = size.getText();
      String cartProdName = product.getText();
      String prodNameMod = prodName.substring(0,cartProdName.length());
      
      if(prodNameMod.equals(cartProdName)) {
          System.out.println("Product is Added to Cart!");
          System.out.println("Product is verified in Cart");
          Assert.assertEquals(prodNameMod, cartProdName);
      }
      else {
          System.out.println("Product is not added to cart!!");
          Assert.fail();
      }
   }
}
