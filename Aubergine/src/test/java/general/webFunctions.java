package general;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Configuration;
import steps.flipkart;

public class webFunctions extends flipkart {
	
	public static String currentDir = System.getProperty("user.dir");
	
	public void InitDriver(String browserName) throws Exception {
		
		String chromeDriverPath = currentDir+"/dependencies/chromedriver.exe";
		try {
			if(browserName=="Firefox") {
			//Todo
			}
			else if(browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver=new ChromeDriver();  
				Thread.sleep(1000);
			}
			else {
				Exception myException = new Exception("Invaid broser name: "+browserName);
				throw myException;
			}
			driver.manage().window().maximize();
		}	
		catch(Exception e){		
			System.out.println(e.toString());
			Exception myException = new Exception("Failed to open browser '" +browserName+ "'");
			throw myException;
		}
	}

	public static String ReadProperty(String PropertyName) {
		String property_Value = null;
		try{
			Properties obj = new Properties();
			FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\ObjectLibrary\\objects.properties");
			obj.load(objfile);
			property_Value = obj.getProperty(PropertyName);
		}
		catch (Exception e) {
			  System.err.println("Exception on get property values: "+e.getMessage());
		}
		return property_Value;
	}
	
	
	public void WaitforObject(By byProperty){
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byProperty));			
	}
	
	@SuppressWarnings("deprecation")
	public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	            }
			};
        try {
        	Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

	public void pressEscape() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();;
	}
	
	public WebElement Element(String ElementName) throws Exception {
		String Object = ReadProperty(ElementName);
		String locatorType = Object.split("->")[0];
        String locatorValue = Object.split("->")[1];
        By ByProp = null;
        
        if(locatorType.toLowerCase().equals("id")) {
        		ByProp = By.id(locatorValue);
        }
        else if(locatorType.toLowerCase().equals("name")) {
        		ByProp = By.name(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class"))) {
        		ByProp = By.className(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag"))) {
        		ByProp = By.className(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))) {
        		ByProp = By.linkText(locatorValue);
        }
        else if(locatorType.toLowerCase().equals("partiallinktext")) {
        		ByProp = By.partialLinkText(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))) {
        		ByProp = By.cssSelector(locatorValue);
        }
        else if(locatorType.toLowerCase().equals("xpath")) {
        		ByProp = By.xpath(locatorValue);
        }
		else {
			throw new Exception("Unknown locator type '" + locatorType + "'");
		}
        WaitforObject(ByProp);
        return driver.findElement(ByProp);
      } 
	
	public List<WebElement> Elements(String ElementsName) throws Exception {
		String Object = ReadProperty(ElementsName);
		String locatorType = Object.split("->")[0];
        String locatorValue = Object.split("->")[1];
        By ByProp = null;
        
        if(locatorType.toLowerCase().equals("id")) {
        		ByProp = By.id(locatorValue);
        }
        else if(locatorType.toLowerCase().equals("name")) {
        		ByProp = By.name(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class"))) {
        		ByProp = By.className(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag"))) {
        		ByProp = By.className(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link"))) {
        		ByProp = By.linkText(locatorValue);
        }
        else if(locatorType.toLowerCase().equals("partiallinktext")) {
        		ByProp = By.partialLinkText(locatorValue);
        }
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css"))) {
        		ByProp = By.cssSelector(locatorValue);
        }
        else if(locatorType.toLowerCase().equals("xpath")) {
        		ByProp = By.xpath(locatorValue);
        }
		else {
			throw new Exception("Unknown locator type '" + locatorType + "'");
		}
        WaitforObject(ByProp);
        return driver.findElements(ByProp);
      } 
	
	public void GenerateMasterthoughtReport(){
        try{
            File reportOutputDirectory = new File("target");
            List<String> jsonFiles = new ArrayList<String>();
            jsonFiles.add("target/cucumber.json");
            String buildNumber = "1";
            String projectName = "Aubergine";
            Configuration configuration = new Configuration(reportOutputDirectory, projectName);
            configuration.setBuildNumber(buildNumber);

            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
        }catch(Exception e){
            e.printStackTrace();
        }
    }   

}
