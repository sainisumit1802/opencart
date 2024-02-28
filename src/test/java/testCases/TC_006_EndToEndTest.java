package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_006_EndToEndTest extends BaseClass{

	@Test(groups= {"master"})
	public void OrderSuccessfull() throws InterruptedException {
		
		logger.info("Strating TC_006_EndToEndTest.....");
	try {
		//Soft assertions
		SoftAssert myassert=new SoftAssert();
			
		//Account Registration
		System.out.println("Account Registration................");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstname(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		
		String email=randomString() + "@gmail.com";
		regpage.setEmail(email);// randomly generated the email
				
		regpage.setPhone(randomNumber());
		
		String password = randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPolicy();
		regpage.clickContinue();
		Thread.sleep(3000);

		String confmsg = regpage.RagisterMsgConfirmation();
		System.out.println(confmsg);
		
		myassert.assertEquals(confmsg, "Your Account Has Been Created!"); //validation
		
		MyAccountPage mc=new MyAccountPage(driver);
		mc.ClickLogout();
		Thread.sleep(3000);
		
		
		//Login
		System.out.println("Login to application...............");
		hp.clickMyAccount();
		hp.clickLoginLink();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.ClickLoginBtn();
			
		
		System.out.println("Going to My Account Page? "+ mc.ConfirmMyAccount());
		myassert.assertEquals(mc.ConfirmMyAccount(), true); //validation

		
		//search & add product to cart
		System.out.println("search & add product to cart...............");
		hp.searchProduct(p.getProperty("product"));
		hp.searchBtn();
		
		SearchPage sp=new SearchPage(driver);
		
		if(sp.isProductExist(p.getProperty("product")))
		{
			sp.selectProduct(p.getProperty("product"));
			sp.setQuantity(p.getProperty("quantity"));
			sp.addToCart();
			
		}
		Thread.sleep(3000);
		System.out.println("Added product to cart ? "+ sp.checkConfMsg());
		myassert.assertEquals(sp.checkConfMsg(),true);
			
			
		//Shopping cart
		System.out.println("Shopping cart...............");
		ShoppingCartPage sc=new ShoppingCartPage(driver);
		sc.clickItemsToNavigateToCart();
		sc.clickViewCart();
		Thread.sleep(3000);
		String totprice=sc.getTotalPrice();
		System.out.println("total price is shopping cart: "+totprice);
		myassert.assertEquals(totprice, p.getProperty("price"));   //validation
		sc.clickOnCheckout(); //navigate to checkout page
		Thread.sleep(3000);
		
		
		//Checkout Product
		System.out.println("Checkout Product...............");
		CheckoutPage ch=new CheckoutPage(driver);
		
		ch.setfirstName(randomString().toUpperCase());
		Thread.sleep(1000);
		ch.setlastName(randomString().toUpperCase());
		Thread.sleep(1000);
		ch.setaddress1(randomString());
		Thread.sleep(1000);
		ch.setaddress2(randomString());
		Thread.sleep(1000);
		ch.setcity(p.getProperty("city"));
		Thread.sleep(1000);
		ch.setpin(p.getProperty("pin"));
		Thread.sleep(1000);
		ch.setCountry(p.getProperty("country"));
		Thread.sleep(1000);
		ch.setState(p.getProperty("state"));
		Thread.sleep(1000);
		ch.clickOnContinueAfterBillingAddress();
		Thread.sleep(1000);
		ch.clickOnContinueAfterDeliveryAddress();
		Thread.sleep(1000);
		ch.setDeliveryMethodComment("testing...");
		Thread.sleep(1000);
		ch.clickOnContinueAfterDeliveryMethod();
		Thread.sleep(1000);
		ch.selectTermsAndConditions();
		Thread.sleep(1000);
		ch.clickOnContinueAfterPaymentMethod();
		Thread.sleep(2000);
		
		String total_price_inOrderPage=ch.getTotalPriceBeforeConfOrder();
		System.out.println("total price in confirmed order page:"+total_price_inOrderPage);
		myassert.assertEquals(total_price_inOrderPage, p.getProperty("price2")); //validation
		
		//Below code works ony if you configure SMTP for emails 
		/*ch.clickOnConfirmOrder();
		Thread.sleep(3000);
			
		boolean orderconf=ch.isOrderPlaced();
		System.out.println("Is Order Placed? "+orderconf);
		myassert.assertEquals(ch.isOrderPlaced(),true);*/
			
		myassert.assertAll(); //conclusion
		
	}catch(Exception e) {
		Assert.fail();
	}
	
		logger.info("Completed TC_006_EndToEndTest.....");
	}

		
}

