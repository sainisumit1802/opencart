package testCases;


import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups= {"regression","master"})
	public void verify_Account_Registration() {
		logger.info("*** Starting TC_001_AccountRegistrationTest ***");
		logger.debug("Debugging Start..");
		try {
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked on MyAccount..");
		hp.clickRegister();
		logger.info("clicked on Register Button..");
		
		logger.info("Entering Details....");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		regpage.setFirstname(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		regpage.setEmail(randomString().toLowerCase()+"@gmail.com");
		regpage.setPhone(randomNumber());
		
		String password = randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPolicy();
		regpage.clickContinue();
		logger.info("Clicked on continue....");
		
		logger.info("Validating confirmation message....");
		String comMsg = regpage.RagisterMsgConfirmation();
		
		if(comMsg.equals("Your Account Has Been Created!")) {
			logger.info("Test passed.....");
			Assert.assertTrue(true);
		}else {
			logger.error("Test failed.....");
			Assert.fail();
		}
		}catch(Exception e) {
			logger.error("Exception Occured.....");
			Assert.fail();
		}
		logger.debug("debug complete.......");
		logger.info("*** Complete TC_001_AccountRegistrationTest***");
	}
	
	
}
