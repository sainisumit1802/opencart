package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_005_AddToCartTest extends BaseClass {
	
	//WebDriver driver;
	
	@Test(groups= {"master"})
	public void addtoCart() {
		
		logger.info("Starting TC_005_AddToCartTest.......");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.searchProduct(p.getProperty("product"));
			hp.searchBtn();
			
			SearchPage sp = new SearchPage(driver);
			
			if(sp.isProductExist(p.getProperty("product"))) {
				sp.selectProduct(p.getProperty("product"));
				sp.setQuantity(p.getProperty("quantity"));
				sp.addToCart();
				
			}
			Assert.assertEquals(sp.checkConfMsg(), true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
		logger.info("Completed TC_005_AddToCartTest.......");
	}
}
