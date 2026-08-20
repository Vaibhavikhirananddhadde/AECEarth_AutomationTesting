package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import utils.FileUploadUtil;

public class CreatePostPage extends BasePage {

	public CreatePostPage(Page page) {
		super(page);
	}

	// Post Text area field.
	public Locator txt_postText() {
		return page.locator("//div[@class='flex items-center gap-3 mb-4']/following-sibling::textarea");
	}

	// Post Photo button
	public Locator btn_photo() {
		return page.getByRole(AriaRole.BUTTON, 
				new Page.GetByRoleOptions().setName("Photo"));
	}

	// Post Video button
	public Locator btn_video() {
		return page.getByRole(AriaRole.BUTTON, 
				new Page.GetByRoleOptions().setName("Video"));
	}

	// Discard button
	public Locator btn_Discard() {
		return page.getByRole(AriaRole.BUTTON, 
				new Page.GetByRoleOptions().setName("Discard"));
	}

	// Post button
	public Locator btn_Post() {
		return page.locator("//div[@class='flex justify-center gap-4 pt-4']/button[contains(text(),'Post')]");
	}
	
	//Creating new post image
	public void createPost_Image() {
		txt_postText().fill("Excited to share a quick update from our latest project!");
		
		FileUploadUtil.uploadMedia(
		        page,"Photo","src/test/resources/testdata/sample-image.png"
		);
		
		btn_Post().click();
		
	}
	
	//Creating new post video
	public void createPost_Video() {
		txt_postText().fill("Excited to share a quick update from our latest project!");
		
		FileUploadUtil.uploadMedia(
				page, "Video", "src/test/resources/testdata/sample-video.mp4"
				);
		
		btn_Post().click();
	}

}
