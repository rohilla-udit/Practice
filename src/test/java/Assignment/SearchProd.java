package Assignment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProd {

	 WebDriver driver;
	 // Constructor
	 SearchProd(WebDriver driver){
	     this.driver=driver;
	     PageFactory.initElements( driver,this);
	 }
	 // Getting locators
	 @FindBy(xpath="//input[@placeholder='Search on Nykaa']")
	 WebElement search_box;

	 public void SearchAProduct(String Prod_name) {
	     search_box.sendKeys(Prod_name,Keys.ENTER);
	 }
}