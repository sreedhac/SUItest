import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//This class handles all the automation related activities
public class Automation {

	// create webdriver object and initialize with null value
	WebDriver wbDriver = null;
	ExcelWriter ew;

	// browser value passed as parameter from Testng.xml
	@BeforeTest
	@Parameters({ "browser" })
	public void startTest(String browser) throws IOException {

		// creating driver class object
		Driverclass driver = new Driverclass();
		ew = new ExcelWriter();

		// passing browser value to driverclass method which creates and returns the
		// appropriate driver
		wbDriver = driver.driverClass(browser);
	}

	@Test(priority = 1)
	public void runScript() throws Exception {

		// navigating to website, reading url from excel
		wbDriver.get(ew.getvalue(0, 1));

		// set windowsize to fullscreen
		//wbDriver.manage().window().fullscreen();
		wbDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement btnCookies = wbDriver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
		btnCookies.click();
		//logging into the application
		
		String strUserMailID = ew.getvalue(1, 1);
		String strUserPassword = ew.getvalue(2, 1);
		
		WebElement btnLogin = wbDriver.findElement(By.xpath("//ul[@class='header__list tracking-event-module']//li//a[@href='/login?redirectUrl=https%253A%252F%252Fwww.payback.de%252Fhome']"));
		btnLogin.click();
				
		Thread.sleep(7000);
		
		//entering user Email id and password
		WebElement txtEmailID = wbDriver.findElement(By.xpath("//input[@placeholder='E-Mail oder Kundennummer']"));
		txtEmailID.sendKeys(strUserMailID);
		
		//enter password
		WebElement txtPassWord = wbDriver.findElement(By.xpath("//input[@name='passwordName']"));
		txtPassWord.sendKeys(strUserPassword);
		
		//and continue to website by clicking "Einloggen" button
		WebElement btnEinloggen = wbDriver.findElement(By.xpath("//button[@id='loginSubmitButtonSecure']"));
		btnEinloggen.click();
		
		Thread.sleep(3000);
		
		//navigating to ecoupons section
		WebElement btnEcoupon = wbDriver.findElement(By.xpath("//a[@href='/coupons']"));
		btnEcoupon.click();
		
		//select REWE from the filtering options
		WebElement root = wbDriver.findElement(By.xpath("//pb-coupon-center[@id='coupon-center']"));
		WebElement shadowDOMa = getShadowDOM(root, wbDriver);
		
		WebElement root2 = wbDriver.findElement(By.xpath("//div[@class='coupon-center']//div[@class='coupon-center__filter']//pb-coupon-filter[@class='coupon-center__filter-select']")) ;
		WebElement shadowDOMb = getShadowDOM(root2,wbDriver);
		
		WebElement root3 = shadowDOMb.findElement(By.tagName("pb-coupon-partner-picker"));
		WebElement shadowDOMc = getShadowDOM(root3,wbDriver);
		
		WebElement root4 = shadowDOMc.findElement(By.xpath("//pbc-select[@id='coupon-partner-select'"));
		WebElement shadowDOMd = getShadowDOM(root4,wbDriver);
		WebElement drpFilter = shadowDOMd.findElement(By.xpath("//div[@class='pbc-select-block']//select[@class='pbc-select__element']"));
		
		String strVouchercentre = ew.getvalue(3, 1);
		Select selVal = new Select(drpFilter);
		selVal.selectByVisibleText(strVouchercentre);
		
		WebElement strVoucher= wbDriver.findElement(By.xpath("//*[@class='coupon-call-to-action__button coupon__activate-button not-activated']"));
		strVoucher.click();
		
				
		

	}
	static WebElement getShadowDOM(WebElement wbElement, WebDriver webdriver) {
        JavascriptExecutor js = (JavascriptExecutor)webdriver;
        WebElement shadowDOM1 = (WebElement)js.executeScript("return arguements[0].shadowRootM", wbElement);
        return shadowDOM1;
		}
	@AfterTest
	public void quit() throws IOException {

		// closing the driver after test
		wbDriver.close();

		// closing excel sheet
		ew.close();
	}

}
