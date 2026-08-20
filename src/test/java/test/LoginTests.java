package test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import data.TestDataProvider;
import pages.LandingPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ElementUtil;
import utils.WaitUtil;

public class LoginTests extends BaseClass{
	ElementUtil util;
	WaitUtil wait;
	ConfigReader config;
	LandingPage land;
	LoginPage login;
	
	//Sign in form is displayed with "User Login" and "Admin Login" tabs.
	@Test
	public void loginTabs() {
		land = new LandingPage(page);
		land.clickSignin();
		
		login = new LoginPage(page);
	
		assertThat(login.userlogin_tab()).isVisible();
		
		assertThat(login.adminlogin_tab()).isVisible();
	}
	
	//Verify "User Login" form fields.
	@Test
	public void userLogin_formfields() {
		util = new ElementUtil(page);
		land = new LandingPage(page);
		land.clickSignin();
		
		login = new LoginPage(page);
		login.userlogin_tab().click();
		
		assertThat(login.txt_email()).isVisible();
		assertThat(login.txt_password()).isVisible();
		assertThat(login.lnk_forgotPwd()).isVisible();
		assertThat(login.chk_rememberMe()).isVisible();
		
		util.scrollIfNeeded(login.btn_signIn());		
		assertThat(login.btn_signIn()).isVisible();
		
	}
	
	//Verify "Admin Login" form fields.
	@Test
	public void adminLogin_formfields() {
		land = new LandingPage(page);
		land.clickSignin();
		
		login = new LoginPage(page);
		login.adminlogin_tab().click();
		
		assertThat(login.txt_email()).isVisible();
		assertThat(login.txt_password()).isVisible();
	}
	
	
	//User Sign in with valid credentials.
	@Test
	public void signinValidCredentials() {
		wait = new WaitUtil(page);
		land = new LandingPage(page);
		
		land.signIn("gowdaasha570@gmail.com", "Test@123");
		wait.waitForUrl(ConfigReader.getProperty("feedUrl"));
	    
		Assert.assertTrue(page.url().contains("/feed"));
			
	}
	
	//User Sign in with invalid credentials.
	@Test(dataProvider = "loginData",
	          dataProviderClass = TestDataProvider.class)
	public void signin_InvalidCredentials(String email, String password) {
		wait = new WaitUtil(page);
		land = new LandingPage(page);
		
		land.signIn("gowdaasha570@gmail.com", "Test@123");
		
		Assert.assertTrue(page.url().contains("/login"));
	}
	
	

}
