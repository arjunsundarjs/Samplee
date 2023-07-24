import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomation {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the URL
        driver.get("https://www.saucedemo.com/");

        // Login test
        loginTest(driver, "standard_user", "secret_sauce");

        // Products page test
        productsPageTest(driver, 6);

        // Add highest priced product to cart test
        addHighestPricedProductToCart(driver);

        // Verify product added to cart test
        verifyProductAddedToCart(driver);

        // Close the browser
        driver.quit();
    }

    public static void loginTest(WebDriver driver, String username, String password) {
        // Find the username and password fields and enter the credentials
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    public static void productsPageTest(WebDriver driver, int expectedProductCount) {
        // Get the product elements
        WebElement productsContainer = driver.findElement(By.className("inventory_list"));
        int actualProductCount = productsContainer.findElements(By.className("inventory_item")).size();

        // Compare the actual and expected product counts
        if (actualProductCount == expectedProductCount) {
            System.out.println("Products page test passed");
        } else {
            System.out.println("Products page test failed");
        }
    }

    public static void addHighestPricedProductToCart(WebDriver driver) {
        // Get the product prices
        WebElement productsContainer = driver.findElement(By.className("inventory_list"));
        java.util.List<WebElement> productPrices = productsContainer.findElements(By.className("inventory_item_price"));

        // Find the index of the highest priced product
        double maxPrice = Double.MIN_VALUE;
        int maxPriceIndex = -1;
        for (int i = 0; i < productPrices.size(); i++) {
            String priceText = productPrices.get(i).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            if (price > maxPrice) {
                maxPrice = price;
                maxPriceIndex = i;
            }
        }

        // Add the highest priced product to the cart
        java.util.List<WebElement> addToCartButtons = driver.findElements(By.className("btn_primary"));
        addToCartButtons.get(maxPriceIndex).click();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void verifyProductAddedToCart(WebDriver driver) {
        // Go to the cart page
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Check if the product is added to the cart
        WebElement cartItem = driver.findElement(By.className("cart_item"));
        if (cartItem != null) {
            System.out.println("Product added to cart test passed");
        } else {
            System.out.println("Product added to cart test failed");
        }
    }
}
