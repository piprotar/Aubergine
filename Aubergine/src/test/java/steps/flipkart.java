package steps;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import general.webFunctions;
import general.webAction;
import cucumber.api.java.en.Then;

public class flipkart{
	protected static WebDriver driver = null;
	static webFunctions f = new webFunctions();
	static webAction a = new webAction();
	static List<WebElement> allResults;
	static List<WebElement> allProductsName,allProductsPrice;
	static Iterator<WebElement> name,price;
	static String areaPIN1 = "382481";
	static Iterator<WebElement> productName,productAvailability;
	
    @Given("^that given (.+) has been opened$")
    public void openBrowser(String browser) throws Throwable {
    	f.InitDriver(browser);
    }

    @When("^I launch (.+)$")
    public void launchURL(String url) throws Throwable {
    	driver.get(url);
    }

    @Then("^google search page should display$")
    @Given("^google search page is opened$")
    public void verifyGooglePage() throws Throwable {
    	Assert.assertEquals(a.getPageTitle(),"Google");
    }

    @When("^I enter \"([^\"]*)\" in search text box$")
    public void enterSearchKeyword(String searchKeyword) throws Throwable {
    	a.SetValue("txtSearch", searchKeyword);
    	a.enterOnObject("txtSearch");
    }

    @Then("^I verify the Flipkart keyword is searched by google$")
    @Given("^Flipkart keyword is searched from google$")
    public void verifySearchedKeyword() throws Throwable {
    	Assert.assertEquals(a.getPageTitle(), "Flipkart - Google Search");
    }
    
    @When("^I get all the searched results$")
    public void getSearchedResults() throws Throwable {
    	allResults = f.Elements("linksSearchValues");    	
    }
    
    @Then("^I should be able to print search results$")
    public void printSearchedResults() throws Throwable {
    	int resultNo=1;
    	System.out.println("\n");
    	for(WebElement result : allResults) {
    		String stringResult = result.getText();
    		System.out.println("Search result " + resultNo + ": " + stringResult);
    		resultNo++;
    	}
    }
    
    @When("^I open flipkart link from search result$")
    public void openFlipKart() throws Throwable {
    	a.CLick("linkFlipKart");
    }
    
    @Then("^I should be able to see flipkart page is opened$")
    @Given("^I am on Flipkart page$")
    public void verifyFlipkartPage() throws Throwable {
    	f.waitForPageLoaded();
    	Assert.assertTrue(a.getPageTitle().contains("Online Shopping Site"));
    }
    
    @When("^I close login window if opened$")
    public void closeLoginPopup() throws Throwable {
    	f.pressEscape();
    }
    
    @When("^I select window AC$")
    public void SelectAC() throws Throwable {
    	a.CLick("menuTVs&Appliances");
    	a.CLick("menuWindowACs");
    }
      
    @Then("^I should be able to see winodw AC page is opened$")
    @Given("^I am on Window AC page$")
    public void verifyWindowACPage() throws Throwable {
    	f.waitForPageLoaded();
    	Assert.assertTrue(a.getPageTitle().contains("Window AC"));
    }    
    
    @When("^I select compare button of 2nd 3rd and 6th product$")
    public void selectProductForCompare() throws Throwable {
    	a.CLick("2ndACProduct");
    	a.CLick("3rdACProduct");
    	a.CLick("6thACProduct");
    }
    
    @Then("^I should be able to see 2nd 3rd and 6th product are selected$")
    @Given("^I selected products for compare$")
    public void verifyCompareOption() throws Throwable {
    	Assert.assertTrue(f.Element("btnCompare").isDisplayed());
    }  
    
    @When("^I click on compare button$")
    public void clickOnCompare() throws Throwable {
    	a.CLick("btnCompare");
    }    

    @Then("^I moved to copare product page$")
    @Given("^I am in compare product page$")
    public void verifyCompareWindowPage() throws Throwable {
    	f.waitForPageLoaded();
    	Assert.assertTrue(a.getPageTitle().contains("Window AC"));
    }

    @When("^I get Product name and price of all compared product$")
    public void getCompareProductElements() throws Throwable {
    	List<WebElement> allProductsName = f.Elements("lblCompareProductsName");
    	List<WebElement> allProductsPrice = f.Elements("lblCompareProductsPrice");
    	name = allProductsName.iterator();
    	price = allProductsPrice.iterator();
    }
    
    
    @Then("^I print Product name and price of all compared product$")
    public void printProductsNameAndPrice() throws Throwable {
    	System.out.println("\n");
    	int recordCount=1;
    	System.out.println("Compared Product details are:");
    	while(name.hasNext() && price.hasNext()) 
    	{
    		System.out.println("Product " + recordCount + " Name is: '" + name.next().getText() + "' and Price is: " + price.next().getText());
    		recordCount++;
    	}
    }
    
    @SuppressWarnings("deprecation")
	@When("^I add all three product on cart$")
    public void addProductToCart() throws Throwable {
    	a.CLick("btnProduct1AddToCart");
    	a.navigateBack();
    	a.CLick("btnProduct2AddToCart");
    	a.navigateBack();
    	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	a.CLick("btnProduct3AddToCart");
    }

    @Then("^I should be able to see products are added in Cart$")
    @Given("^I am in My Cart page$")
    public void verifyProductsAddeInCart() throws Throwable {
    	String addedKartItems = a.getTextValue("lblCartItemCount");
    	Assert.assertEquals(addedKartItems,"My Cart (3)");
    }
    
    @When("^I enter my (.+) Code number$")
    public void enterAreaPinCode(String areaPinCode) throws Throwable {
    	f.waitForPageLoaded();
    	areaPIN1 = areaPinCode;
    	a.SetValue("txtAreaPIN", areaPIN1);
    	a.enterOnObject("txtAreaPIN");
    	f.waitForPageLoaded();
    }
    
    @When("^I change my (.+) Code number$")
    public void changeAreaPinCode(String areaPinCode) throws Throwable {
    	f.waitForPageLoaded();
    	areaPIN1 = areaPinCode;
    	a.CLick("ddlDeliveredToPin");
    	a.SetValue("txtAreaPIN", areaPIN1);
    	a.enterOnObject("txtAreaPIN");
    	f.waitForPageLoaded();
    }
    
	@Then("^Verify available products for entered PIN Code$")
    public void verifyAvailableProducts() throws Throwable {
    	f.waitForPageLoaded();
		List<WebElement> availableProductsName = f.Elements("lblAvailableProducts");
		System.out.println("\n"+availableProductsName.size()+" Products are available for area PinCode " +areaPIN1);
    }
    
	@When("^I get the available product details$")
    public void getAvailableProducts() throws Throwable {
    	f.waitForPageLoaded();
		/*
		 *List<WebElement> availableProductsName = f.Elements("lblAvailableProducts");
		 * List<WebElement> ProductsAvailability = f.Elements("lblProductsAvailableOn");
		 */
    	productName = f.Elements("lblAvailableProducts").iterator();
    	productAvailability = f.Elements("lblProductsAvailableOn").iterator();    	
    }
	
	@Then("^I print available product details on console$")
    public void printAvailableProductDetails() throws Throwable {
    	System.out.println("\nAvailable Products for area PIN code " + areaPIN1 + " are:");
    	
    	while(productName.hasNext() && productAvailability.hasNext())
    	{
    	   System.out.println("Product Name is: '" + productName.next().getText() + "' and will be available on: '" + productAvailability.next().getText() + "'");
    	}
    }
	
	@When("^I close browser$")
    public void CloseBrowser() throws Throwable {
		driver.close();
		f.GenerateMasterthoughtReport();
    }
}