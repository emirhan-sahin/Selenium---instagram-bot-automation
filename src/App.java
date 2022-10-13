import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    WebDriver driver;
    String app_url = "https://www.instagram.com/";
    String target_url;
    By usernameLocator = new By.ByCssSelector("input[name='username']");
    By passwordLocator = new By.ByCssSelector("input[name='password']");
    By loginButtonLocator = new By.ByCssSelector("button[type='submit']");
    By instagramLogo1 = new By.ByCssSelector("div[class=\"_aakf\"]");
    By likeButton = new By.ByCssSelector("._aamw");

    By htmlTag = By.tagName("html");
    By postCounter = By.className("_ac2a");
    By instagramLogo2 = By.className("_aagx");
    By firstPost = new By.ByCssSelector("div[class=\"_aagw\"]");


    public App() {
        System.setProperty("webdriver.chrome.driver", "D:/Selenium/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(app_url);
        driver.manage().window().maximize();
    }

    public void loginWith(String userName, String password) {
        waitFor(usernameLocator);
        driver.findElement(usernameLocator).sendKeys(userName);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        waitFor(instagramLogo1);
    }

    public void navigateToTargetProfile(String profileName) {
        target_url = "https://www.instagram.com/" + profileName;
        driver.navigate().to(target_url);
    }

    public int getpostCount() {
        String count = driver.findElements(postCounter).get(0).getText();
        return Integer.parseInt(count);
    }

    public void clickFirstPost() {
        waitFor(instagramLogo2);
        driver.findElements(firstPost).get(0).click();
    }

    public void allPost(int count) {
        // getpostCount() metodu ile almış olduğumuz post sayısını burda kullanıyoruz.
        while (0 < count) {
            waitFor(likeButton);
            driver.findElement(likeButton).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT); // klavye yön tuşları ile sırasıyla postları koalyca gezinmiş oluyoruz.
            count--;
        }
        System.out.println("Process Successful : All posts liked.");
        driver.quit(); // işlem bittikten sonra tarayıcı kendini kapatıyor.
    }

    // Kod tekrarı olmaması için:  wait metodu yazarak kullanmak istediğimiz yerlerde waitFor() metodunu çağırarak  kullandık.
    private void waitFor(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
