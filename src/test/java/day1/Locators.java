package day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		// Opening Nykaa.com
		driver.get("https://nykaa.com");

		//Maximizing Window Size
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		Thread.sleep(2000);

		// Checking is page Title is as expected or Not
		String title = driver.getTitle();
		if(title.equals("Buy Cosmetics Products & Beauty Products Online in India at Best Price | Nykaa")) {
			System.out.println("Successfully opened Nykaa.com");
		} else {
			System.out.println("Not Nykaa.com");
		}
		
		// Selecting Search Bar
		action.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Search on Nykaa']")))
		.click().build().perform();
		System.out.println("Opening Search Box");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Typing in Search Bar
		action.sendKeys("Hair Mask").build().perform();
		System.out.println("Entered Product Name");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Searching...
		action.sendKeys(Keys.RETURN).build().perform();
		System.out.println("Searching Product...");
		Thread.sleep(2000);
		
		// Wait for search result to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-1rd7vky']")));
		System.out.println("PLP Displayed");

		// Locating 2nd element using xpath
		WebElement secondProduct = driver.findElement(By.xpath("(//div[@class='css-1rd7vky'])[2]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='css-1rd7vky'])[2]")));

		// Scroll to secondProduct using JavaScript
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
		System.out.println("Scrolling to search 2nd Product");
		js.executeScript(scrollElementIntoMiddle, secondProduct);
		wait.until(ExpectedConditions.elementToBeClickable(secondProduct));

		// Move over secondProduct and Add To Bag
		System.out.println("Moving Cursor to 2nd Product");
		action.moveToElement(secondProduct).click().build().perform();
		System.out.println("Clicked on Product");
		Thread.sleep(2000);
		
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
		
		// Verifying Whether Product is Successfully Added to Cart or Not
		WebElement cartNum = driver.findElement(By.xpath("//span[@class='cart-count']"));
		String cartCount = cartNum.getText();
		if(Integer.parseInt(cartCount) > 0) {
			System.out.println("Product Successfully Verified & Added to Cart");
		} else {
			System.out.println("Product Not Added to cart");
		}
		
//		----------- Practice ----------
		
		// Typing Mac in search Box 
//		WebElement search = driver.findElement(By.name("search"));
//		search.sendKeys("Mac");
//		WebElement searchBox = driver.findElement(By.xpath("//button[@type='button']"));  //defining xpath
//		searchBox.click();
		
		// Total Number of Links in Header
//		List<WebElement> headerLinks = driver.findElements(By.className("list-inline-item"));
//		System.out.println("Total header links: " + headerLinks.size());
		
		//a[@class='nav-link dropdown-toggle show']
		
		// Moving Cursor to Search Bar then Clicking on it and typing "Hair Mask" then hitting "Enter"
//		WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Search on Nykaa']"));
//		action.moveToElement(searchBar);
//		Thread.sleep(3000);
//		
//		action.(driver.findElement(By.xpath("//input[@placeholder='Search on Nykaa']")))
//		.click().sendKeys("Hair Mask")
//		.sendKeys(Keys.RETURN).build().perform();
		
		
		// Explicit Wait
//		Wait<WebDriver> mywait = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(10))
//				.pollingEvery(Duration.ofSeconds(5))
//				.ignoring(NoSuchElementException.class);
//		
//		WebElement txtUsername = mywait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(By.xpath("//div[@class='css-1hd8apt']//button[@class='css-iyqsry']"));
//			}
//		});
		
		
	}

}
