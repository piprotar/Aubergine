package general;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import steps.flipkart; 

public class webAction extends flipkart {
	static webFunctions f = new webFunctions();
	
	public void CLick(String ObjectName) throws Exception {
		try {
			Thread.sleep(1000);
			WebElement myEle = f.Element(ObjectName);
			myEle.click();
		} catch (Exception e) {
			Exception myException = new Exception("Failed to click on object: "+ObjectName);
			System.out.println("Failed to click on object: "+ObjectName +e.toString());
			throw myException;
		}
	}
	
	public void SetValue(String ObjectName,String txtValue) throws Exception {
		try {
			WebElement myEle = f.Element(ObjectName);
			myEle.clear();
			myEle.sendKeys(txtValue);
		} catch (Exception e) {
			Exception myException = new Exception("Failed to set value on object: "+ObjectName);
			System.out.println("Failed to set value on object: "+ObjectName +e.toString());
			throw myException;
		}
	}
		
	public void enterOnObject(String ObjectName) throws Exception {
		try {
			WebElement myEle = f.Element(ObjectName);
			myEle.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			Exception myException = new Exception("Failed to enter on object: "+ObjectName);
			System.out.println("Failed to enter on object: "+ObjectName +e.toString());
			throw myException;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void navigateBack() throws Exception {
		try {
			f.waitForPageLoaded();
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			f.waitForPageLoaded();
		} catch (Exception e) {
			Exception myException = new Exception("Failed to navigate back");
			System.out.println("Failed to navigate back");
			throw myException;
		}
	}
	
	public String getPageTitle() throws Exception {
		String pageTitle;
		try {
			pageTitle = driver.getTitle().trim();
		} catch (Exception e) {
			Exception myException = new Exception("Failed to get page title");
			System.out.println("Failed to get page title");
			throw myException;
		}
		return pageTitle;
	}

	public String getTextValue(String ObjectName) throws Exception {
		String objectText;
		try {
			WebElement myEle = f.Element(ObjectName);
			objectText = myEle.getText().trim();
		} catch (Exception e) {
			Exception myException = new Exception("Failed to get text of object: "+ObjectName);
			System.out.println("Failed to get text of object: "+ObjectName +e.toString());
			throw myException;
		}
		return objectText;
	}
	
}
