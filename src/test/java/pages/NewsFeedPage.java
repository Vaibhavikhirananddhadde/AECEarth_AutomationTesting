package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NewsFeedPage extends BasePage{
	
	public NewsFeedPage(Page page) {
		super(page);
	}
	
	//News Feed navigation link
	public Locator nav_NewsFeed() {
		return page.getByText("News Feed");
	}
	
	//Media button
	public Locator btn_Media() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Media"));
	}
	
	//content displayed after post
	public Locator posted_content_afterPost() {
		return page.locator("(//div[@class='relative w-full mt-3'])[1]");
	}
	
	//First post after posting in feed page - 3 dots.
	public Locator first_post_3Dots() {
		return page.locator("(//div[@class='w-full transition-shadow duration-300 ']//div[@class='relative'])[1]");
	}
	
	//Edit button
	public Locator btn_edit() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Edit Post"));
	}
	
	//Delete button
	public Locator btn_delete() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Delete Post"));
	}
	
	//Save changes button
	public Locator btn_saveChanges() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Save Changes"));
	}
	
	//Confirmation popup message before deleting a post
	public Locator msg_confirm_delete() {
		return page.getByText("Are you sure you want to delete this post?");
	}
	
	//Yes delete button in confirmation popup
	public Locator btn_YesDelete() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Yes, Delete"));
	}
	
	//like button
	public Locator icon_like() {
		return page.locator(
			    "button:has(img[src*='like'])"
				);
	}
	
	//comment icon
	public Locator icon_comment() {
		return page.locator(
				"button:has(img[src*='message'])"
				);
	}
	
	//all posts
	public Locator all_Posts() {
		return page.locator("div.w-full.transition-shadow.duration-300");
	}
	
	//first post
	public Locator first_Post() {
		return page.locator("div.w-full.transition-shadow.duration-300").first();
	}
	
	//comment input field
	public Locator txt_comment() {
		return page.getByPlaceholder("Add a Comment");
	}
	
	//send comment button
	public Locator btn_send() {
		return page.locator("button:has(img[src*='send'])");
	}
	
	//like a post
	public void like_a_Post() {
		first_Post().locator(icon_like()).click();
	}
	
	public void comment_a_Post() {
		first_Post().locator(icon_comment()).click();
		txt_comment().fill("Commented on a post");
		btn_send().click();
	}
	
}
