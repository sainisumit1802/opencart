package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class)
	public void verify_LoginDDT(String email,String pass,String exp) {
		
		logger.info("*** Start TC_003_LoginDDT ***");
		try 
		{
			// home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLoginLink();
					
					
			//Login page
			LoginPage lp = new LoginPage(driver);
					
			lp.setEmail(email);
			lp.setPassword(pass);
			lp.ClickLoginBtn();
					
					
			//MyAccountPage
			MyAccountPage macc = new MyAccountPage(driver);
			boolean status = macc.ConfirmMyAccount();
			
				if(exp.equalsIgnoreCase("Valid")) {
					if(status==(true)) {
						macc.ClickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertTrue(false);
					}
				}else if(exp.equalsIgnoreCase("Invalid"))
				{
					if(status==(true)) {
						macc.ClickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
				}
		}
		catch(Exception e) {
			logger.info("Exception occured........");
			Assert.fail();
		}
		
		logger.info("*** Finished TC_003_LoginDDT ***");
					
	}
	
}
