package day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Highlight {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
			
		// Opening Nykaa.com
		driver.get("https://nykaa.com");  
		//Maximizing Window Size
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Flash(driver.findElement(By.xpath("//button[@aria-label='Kebab menu']")), driver);
		
		Thread.sleep(10000);
		driver.quit();
	}	
	
	public static void Flash(WebElement element, WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgColor = element.getCssValue("backgroundColor");
		for(int i = 0; i < 5; i++) {
			changeColor("rgb(255, 0, 0)", element, driver);
//			Thread.sleep(400);
			changeColor(bgColor, element, driver);
		}
	}
	
	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element);
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e){

		}
	}
}
