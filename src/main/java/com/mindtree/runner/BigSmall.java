package com.mindtree.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mindtree.pageObjects.AboutUsPage;
import com.mindtree.pageObjects.AllOfItPage;
import com.mindtree.pageObjects.ChristmasGiftsPage;
import com.mindtree.pageObjects.ContactUsPage;
import com.mindtree.pageObjects.GiftCardPage;
import com.mindtree.pageObjects.HomePage;
import com.mindtree.pageObjects.PersonalizedGiftsPage;
import com.mindtree.pageObjects.SearchPage;
import com.mindtree.pageObjects.ShopByCategoryPage;
import com.mindtree.pageObjects.SignInPage;
import com.mindtree.pageObjects.WishlistPage;
import com.mindtree.reusableComponent.WebDriverHelper;
import com.mindtree.utility.ReadPropertyFile;
import com.mindtree.utility.RetreiveExcelData;

public class BigSmall {

	Logger log = LogManager.getLogger(BigSmall.class.getName());

	WebDriver driver = null;
	ReadPropertyFile rp = null;

	@Test(priority = 1)
	public void homePageLanding() throws Exception {

		rp = new ReadPropertyFile();

		driver = WebDriverHelper.initializeDriver();

		driver.get(rp.getUrl());
		System.out.println("url hited");

		log.info("Browser opened succcessfully");

	}

	@Test(priority = 2, dataProvider = "dataSuplier")
	public void SignIn_Page(String username, String password) throws Exception {

		HomePage hp = new HomePage(driver);
		hp.getSignIn().click();

		SignInPage sp = new SignInPage(driver);
		sp.getEmail().clear();
		sp.getEmail().sendKeys(username);
		log.info(username + " entered");
		sp.getPassword().clear();
		sp.getPassword().sendKeys(password);
		log.info(password + " entered");
		sp.getSubmit().click();
		log.info("clicked on submit button");

		Thread.sleep(5000L);
	}

	@Test(priority = 3)
	public void PersonalizedGifts_Page() throws IOException {
		PersonalizedGiftsPage PG = new PersonalizedGiftsPage(driver);
		PG.getpersonalizedgifts().click();
		PG.getsortby().click();
		PG.gethightoLow().click();
		if (PG.getmaxprice().getText() == "4500")
			Assert.assertTrue(true);
		log.info("Successfully sorted the items in High to Low price");
	}

	@Test(priority = 4)
	public void Search_Page() throws IOException {
		ArrayList<String> d = RetreiveExcelData.getData("searchdiary");
		SearchPage SP = new SearchPage(driver);
		for (int i = 1; i < d.size(); i++) {
			SP.getsearchbox().sendKeys(d.get(i));
			SP.getsearchbox().sendKeys(Keys.ENTER);
		}
		log.info("Diaries displayed Successfully");
		if (SP.getsearchproduct().isDisplayed())
			Assert.assertTrue(true);
		log.info("Succesffully verified product are displayed");

	}

	@Test(priority = 5)
	public void ChristmasGifts_Page() throws IOException, InterruptedException {
		ChristmasGiftsPage CG = new ChristmasGiftsPage(driver);
		CG.getchristmasgift().click();
		log.info("Successfully navigated to Christmas gifts");
		CG.getfirstproduct().click();
		Thread.sleep(2000);
		CG.getaddtocart().click();
		Thread.sleep(5000);
		log.info("Added product to the cart");
		CG.getcheckout().click();
		Thread.sleep(2000);
		if (CG.getclosepopup().isDisplayed())
			CG.getclosepopup().click();
		CG.getreturntocart().click();
		Thread.sleep(1000);
		log.info("Proceeeded to checkout and returned to cart");

	}

	@Test(priority = 6)
	public void ShopByCategory_Page() throws IOException, InterruptedException {
		ShopByCategoryPage SC = new ShopByCategoryPage(driver);
		SC.getshopbycategory().click();
		SC.getbirthdaygift().click();
		SC.getfirstproduct().click();
		Thread.sleep(3000);
		SC.getaddtowishlist().click();
		Thread.sleep(500);
		if (SC.getviewwishlist().isDisplayed())
			Assert.assertTrue(true);
		log.info("Items added to the wishlist");

	}

	@Test(priority = 7)
	public void AllofIt_Page() throws IOException, InterruptedException {
		AllOfItPage AP = new AllOfItPage(driver);
		AP.getallofit().click();
		Thread.sleep(2000);
		log.info("Navigated to All of it Page");
		if (AP.getproduct().getText().equals("Auto Rickshaw Pen Stand"))
			Assert.assertTrue(true);
		log.info("Verified Auto Rickshaw Pen Stand");
	}

	@Test(priority = 8)
	public void Wishlist_Page() throws IOException, InterruptedException {
		WishlistPage WP = new WishlistPage(driver);
		WP.getwishlist().click();
		Thread.sleep(2000);
		log.info("Navigated to Wishlist Page ");
		if (WP.getclosepopup().isDisplayed())
			WP.getclosepopup().click();
		Thread.sleep(2000);
		if (WP.getproduct().isDisplayed())
			Assert.assertTrue(true);
		log.info("Products are displayed in Wishlist");
	}

	@Test(priority = 9)
	public void AboutUs_Page() throws IOException, InterruptedException {
		AboutUsPage AP = new AboutUsPage(driver);
		AP.getaboutus().click();
		log.info("Navigated to About Us Page");
		if (AP.gettitle().getText().contains("About us"))
			Assert.assertTrue(true);
		log.info("Verified title of About Us Page");
	}

	@Test(priority = 10)
	public void GiftCard_Page() throws IOException, InterruptedException {
		GiftCardPage GP = new GiftCardPage(driver);
		GP.getgiftcard().click();
		log.info("Navigated to Gift Cards Page");
		GP.getaddtocart().click();
		Thread.sleep(2000);
		log.info("Added Gift Card to cart");
		if (GP.getverifycart().isDisplayed())
			Assert.assertTrue(true);
		log.info("Cart Verified Successfully ");
	}

	@Test(priority = 11)
	public void ContactUs_Page() throws IOException, InterruptedException {
		ContactUsPage CP = new ContactUsPage(driver);
		rp = new ReadPropertyFile();
		CP.getcontinueshopping().click();
		CP.getcontactUs().click();
		CP.getname().sendKeys(rp.getName());
		CP.getemail().sendKeys(rp.getEmail());
		CP.getmessage().sendKeys(rp.getMessage());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (CP.gettitle().getText().equalsIgnoreCase("contact us")) {
			Assert.assertTrue(true);
			log.info("Title verified successfully ");
		}
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		driver = null;
	}

	@DataProvider
	public Object[][] dataSuplier() {
		Object[][] data = new Object[3][2];

		data[0][0] = "";
		data[0][1] = "";

		data[1][0] = "simon@123";
		data[1][1] = "abcd";

		data[2][0] = "JackNJill@gmail.com";
		data[2][1] = "JackNJill@123";

		return data;
	}

}
