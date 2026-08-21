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

	
	//all posts
	public Locator all_Posts() {
		return page.locator("div.w-full.transition-shadow.duration-300");
	}
	
	//first post
	public Locator first_Post() {
		return page.locator("div.w-full.transition-shadow.duration-300").first();
	}
	
	//Like button of first post
	public Locator firstPostLikeButton() {
        return page.locator("button")
                .filter(new Locator.FilterOptions()
                        .setHas(page.locator("img[src*='like']")))
                .first();
    }
	
	//Comment button of first post
	public Locator firstPostCommentButton() {
		return page.locator("button")
				.filter(new Locator.FilterOptions()
				.setHas(page.locator("img[src*='message']")))
				.first();
	}
	
	//first post like icon
	 public Locator firstPostLikeIcon() {
	        return firstPostLikeButton().locator("img");
	    }

	 //first post like count
	 public Locator firstPostLikeCount() {
	        return firstPostLikeButton().locator("span");
	 }
	 
	 //count the likes
	 public int getFirstPostLikeCount() {
	 String count = firstPostLikeCount().textContent().trim();   	
	    	return Integer.parseInt(count);
	 }
	 
	 //Check color of like 
	 public String getFirstPostLikeIconSrc() {

	        return firstPostLikeIcon()
	                .getAttribute("src");
	    }
	
	//comment input field
	public Locator txt_comment() {
		return page.getByPlaceholder("Add a Comment").first();
	}
	
	//send comment button
	public Locator btn_send() {
		return page.locator("//div[@class='relative min-w-0 flex-1']//img").first();
	}
	
	//like a post
	public void like_a_Post() {
		first_Post().locator(firstPostLikeIcon()).click();
	}
	
	public void comment_a_Post(String comment) {
		first_Post().locator(firstPostCommentButton()).click();
		txt_comment().fill(comment);
		btn_send().click();
		first_Post().locator(firstPostCommentButton()).click();
	}
	
	//Commented text in the comment box	
	public Locator firstComment() {
	    return page.locator("li[id^='feed-comment-']").first();
	}
	
	//First comment text
	public boolean firstCommentContains(String comment) {

	    String actualText = firstComment().innerText();
	    return actualText.contains(comment);
	}
	
	//first comment options
	public Locator firstComment_options() {
		return firstComment().locator("//button[@aria-label='Comment options']");
	}
	
	//edit comment button
	public Locator edit_comment() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Edit"));
	}
	
	//delete comment button
	public Locator btn_delete_comment() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Delete"));
	}
	
	//Confirm popup before deleting comment
	public Locator conf_deleteComment() {
		return page.getByText("Are you sure you want to delete this comment?");
	}
	
	//Successful comment deletion message
	public Locator success_commentDeletion() {
		return page.getByText("Comment deleted successfully");
	}
	
	//delete comment  confirm button yes delete
	public Locator btn_deleteCommentYES() {
		return page.locator("//button[text()='Yes, Delete']");
	}
	
	//Comment text area
	public Locator txt_editComment() {
		return page.locator("//li//textarea");
	}
	
	//Save edited comment
	public Locator btn_Save() {
		return page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Save"));
	}
	
	//Edit comment success message
	public Locator msg_successEdit() {
		return page.getByText("Comment updated successfully");
	}
}
