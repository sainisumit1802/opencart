package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_SearchProductTest extends BaseClass{

	//WebDriver driver;
	
	@Test(groups= {"master"})
	public void verify_Search() {
		
		logger.info("Starting TC_004_SearchProductTest");
		
		try {
			HomePage hm = new HomePage(driver);
			hm.searchProduct(p.getProperty("product"));
			
			hm.searchBtn();
			
			SearchPage sp = new SearchPage(driver);
			
			sp.isProductExist(p.getProperty("product"));
			Assert.assertEquals(sp.isProductExist(p.getProperty("product")),true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("Completed TC_004_SearchProductTest");
		
		
	}
	
}
