package selenium_backend.selenium_backend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




public class App 
{
    public static void main( String[] args ) throws MalformedURLException
    {
    	// create instance of Random class 
        Random rand = new Random(); 
    	//System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
    	
    	//WebDriver driver = new ChromeDriver()
    	// Add options to Google Chrome. The window-size is important for responsive sites
        /*ChromeOptions options = new ChromeOptions();
        options.setCapability(capabilityName, value);
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");*/
        String baseUrl = "http://34.69.187.199:8085/getForm";
        String nodeURL = "http://172.17.0.1:16021/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        WebDriver driver = new RemoteWebDriver(
                new URL(nodeURL),
                capabilities);
         int random = rand.nextInt(50);
        driver.get(baseUrl);
        //Get the web element corresponding to employee name
        WebElement eName = driver.findElement(By.name("employeeName"));
      //Get the web element corresponding to employee email
        WebElement email = driver.findElement(By.name("employeeEmail"));
        WebElement submit = driver.findElement(By.xpath("/html/body/form/input[3]"));
        eName.sendKeys("test"+random);
        email.sendKeys("test"+random+"@test.com");
        submit.click();
        //validate if the the employee was added to database
        driver.get("http://35.184.142.131:8082/user/get-by-email?email="+"test"+random+"@test.com");
        //get the message
        WebElement message = driver.findElement(By.xpath("/html/body"));
        String messageTxt = message.getText();
        if( messageTxt.contains("The user id is"))
        	//Assert.assertEquals("1", "1");
        	System.out.println("passed");
		else
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("test case failed");
			}
        
        /*String tagName = "";
        
        
        tagName = driver.findElement(By.id("email")).getTagName();
        System.out.println(tagName);*/
        driver.close();
        System.exit(0);
    }
}
