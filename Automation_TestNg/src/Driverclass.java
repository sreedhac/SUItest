import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//This class handles all the driver setup 
public class Driverclass {

	// initializing webdriver
	WebDriver driver = null;

	// method to create webdriver based on browser value passed and returns
	// webdriver object
	public WebDriver driverClass(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			// setting system property
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\New\\Downloads\\chromedriver_win32\\chromedriver.exe");
			// initialing the appropriate browser
			driver = new ChromeDriver();
		
		} else
			System.out.println("Enter correct value for browser");

		return driver;
	}

}
