package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LandingPage extends BasePage{
	
	String chk_rememberme = "#remember";
	
	public LandingPage(Page page) {
		super(page);
	}
	
	public void signIn(String businessEmail, String password) {
		page.getByText("Sign In").click();
		
		page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("User Login")).click();
		
		page.getByPlaceholder("you@company.com").fill(businessEmail);
		
		page.getByPlaceholder("Enter your password").fill(password);
		
		page.locator(chk_rememberme).click();
		
		page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Sign In")).click();
		
	}

	public void clickSignin() {
		page.getByText("Sign In").click();
	}
	
}
