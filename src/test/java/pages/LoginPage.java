package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage{
	
	public LoginPage(Page page) {
		super(page);
	}
	
	//user login tab
	public Locator userlogin_tab() {
		 return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("User Login"));
	}
	
	//admin login tab
	public Locator adminlogin_tab() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Admin Login"));
	}
	
	//business email textfield
	public Locator txt_email() {
		return page.getByPlaceholder("you@company.com");
	}
	
	//password textfield
	public Locator txt_password() {
		return page.getByPlaceholder("Enter your password");
	}
	
	//forgot password link
	public Locator lnk_forgotPwd() {
		return page.getByText("Forgot password?");
	}
	
	//Remember me checkbox
	public Locator chk_rememberMe() {
		return page.getByLabel("Remember me for 30 days");
	}
	
	//Login button
	public Locator btn_signIn() {
		return page.getByRole(AriaRole.BUTTON, 
				new Page.GetByRoleOptions().setName("Sign In"));
	}
	
	//Join Aecearth link
	public Locator lnk_joinAecearth() {
		return page.getByLabel("Join AECearth");
	}
	
	

}
