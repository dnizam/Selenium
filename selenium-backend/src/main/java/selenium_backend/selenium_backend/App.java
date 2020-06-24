package selenium_backend.selenium_backend;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class App 
{
    public static void main( String[] args )
    {
    	// create instance of Random class 
        Random rand = new Random(); 
    	System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
	    // Add options to Google Chrome. The window-size is important for responsive sites
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
	options.setExperimentalOption("useAutomationExtension", false);
    	WebDriver driver = new ChromeDriver(options);
    	
        String baseUrl = "http://146.148.75.6:8085/getForm";
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
        driver.get("http://34.71.120.149:8082/user/get-by-email?email="+"test"+random+"@test.com");
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
