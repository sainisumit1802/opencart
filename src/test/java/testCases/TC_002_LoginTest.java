package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"sanity","master"})
	public void loginTest() {
		
		logger.info("*** Starting TC_002_LoginTest ***");
		logger.debug("Debug Start.....");
		try {
		// home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on myAccount...");
		hp.clickLoginLink();
		logger.info("Clicked on Login..."); // home page
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		logger.info("Entering Login details........");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("pass"));
		lp.ClickLoginBtn();
		logger.info("Click on login Button"); //Login page
		
		//MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean status = macc.ConfirmMyAccount();
			if(status == true) {
				macc.ClickLogout();
				logger.info("Test Passed......");
				Assert.assertTrue(true);
				
			}else {
				logger.error("Test Failed.........");
				Assert.fail();
			}
		}
		catch(Exception e) {
			logger.info("Test Failed ---> Exception Occured......");
			Assert.fail();
		}
		logger.debug("debug completed........");
		logger.info("*** Finished TC_002_LoginTest ***");
		
	}
}
