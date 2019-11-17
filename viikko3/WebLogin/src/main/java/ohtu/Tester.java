package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Users/User1/Downloads/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("akkep");
//        element = driver.findElement(By.name("login"));

         // epäonnistunut kirjautuminen
//       element = driver.findElement(By.name("username"));
//       element.sendKeys("pekka");
//       element = driver.findElement(By.name("password"));
//       element.sendKeys("salasana");
//       element = driver.findElement(By.name("login"));

        //uuden käyttäjätunnuksen luominen
        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("arto" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("pakkasaamu");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("pakkasaamu");
        element = driver.findElement(By.name("signup"));
                
        sleep(2);
        element.submit();

        sleep(3);
        
        //pääsivulle, josta pääsee kirjautumaan ulos
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(3);
        
        //uloskirjautuminen
        element = driver.findElement(By.linkText("logout"));
        element.click();

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
