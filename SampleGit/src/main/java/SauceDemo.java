import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemo {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.name("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		int productscount=products.size();
		System.out.println("Number of products present is: "+productscount);
		List<WebElement> prodprice = driver.findElements(By.xpath("//div[@class='inventory_item_name']/../../..//div[@class='inventory_item_price']"));
		int pricecount=prodprice.size();
		String st[]=new String[pricecount];
		for (int i = 0; i < st.length; i++) {
			st[i]=prodprice.get(i).getText();
			st[i]=st[i].replace("$", "");
		}
		for (String string : st) {
			System.out.println(string);
		}
		double []ar=new double[pricecount];
		for (int i = 0; i < ar.length; i++) {
			ar[i]=st[i];
		}
		TreeMap<String, String> hm=new TreeMap<String, String>();
		for(int i=0;i<productscount;i++) {
		hm.put(st[i], products.get(i).getText());	
		}
		System.out.println(hm);
//		for(int i=0;i<productscount;i++) {
//			
//		}
//		driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
//		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
//		driver.findElement(By.xpath("//div[@class='cart_item']")).isDisplayed();
//		String PName = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
//		System.out.println("Product in Cart is :"+PName);
		driver.close();
	}

}
