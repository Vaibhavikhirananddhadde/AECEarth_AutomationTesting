package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import base.BaseClass;
import pages.CreatePostPage;
import pages.LandingPage;
import pages.NewsFeedPage;
import utils.ConfigReader;
import utils.ElementUtil;
import utils.WaitUtil;

public class NewsFeedTests extends BaseClass {
	WaitUtil wait;
	LandingPage land;
	NewsFeedPage feed;
	CreatePostPage post;
	ElementUtil util;

	// Verify that user can post an image.
	@Test
	public void feedPhoto() {
		wait = new WaitUtil(page);
		land = new LandingPage(page);
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);
		feed.nav_NewsFeed().click();

		feed.btn_Media().click();

		post = new CreatePostPage(page);
		post.createPost_Image();

		page.reload();
		wait.waitForPageLoad();

		String contentPost = feed.posted_content_afterPost().textContent();

		Assert.assertTrue(contentPost.contains("Excited to share a quick update from our latest project!"));
	}

	// Verify that user can post a video.
	@Test
	public void feedVideo() {
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// Logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);

		// Uploading a video
		feed.nav_NewsFeed().click();
		feed.btn_Media().click();

		post = new CreatePostPage(page);
		post.createPost_Video();

		wait.waitForPageLoad();

		String contentPost = feed.posted_content_afterPost().textContent();

		Assert.assertTrue(contentPost.contains("Excited to share a quick update from our latest project!"));
	}

	// Check that user can edit a post successfully.
	@Test
	public void verify_EditPost() throws InterruptedException {
		util = new ElementUtil(page);
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);

		// posting
		feed.nav_NewsFeed().click();
		feed.btn_Media().click();

		post = new CreatePostPage(page);
		post.txt_postText().fill("Excited to share a quick update from our latest project!");
		post.btn_Post().click();

		// page load
		wait.waitForPageLoad();

		// Edit post
		feed.first_post_3Dots().click();
		feed.btn_edit().click();
		post.txt_postText().fill("Edited content posted");
		feed.btn_saveChanges().click();

		// page load
		wait.waitUntilContentLoad();

		Assert.assertTrue(feed.posted_content_afterPost().textContent().contains("Edited content posted"));
	}

	// Check that Confirmation popup is displayed before deleting a post.
	@Test
	public void check_confirmBeforeDlt() {
		util = new ElementUtil(page);
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);

		// posting
		feed.nav_NewsFeed().click();
		feed.btn_Media().click();

		post = new CreatePostPage(page);
		post.txt_postText().fill("Excited to share a quick update from our latest project!");
		post.btn_Post().click();

		// page load
		wait.waitForPageLoad();

		// Delete post
		feed.first_post_3Dots().click();
		feed.btn_delete().click();

		assertThat(feed.msg_confirm_delete()).isVisible();
	}

	// Check that user can delete a post successfully.
	@Test
	public void deletePost() {
		util = new ElementUtil(page);
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);

		// posting
		feed.nav_NewsFeed().click();
		feed.btn_Media().click();

		post = new CreatePostPage(page);
		post.txt_postText().fill("Excited to share a quick update from our latest project!");
		post.btn_Post().click();

		// page load
		wait.waitForPageLoad();
		
		// Delete post
		feed.first_post_3Dots().click();
		feed.btn_delete().click();
		feed.btn_YesDelete().click();
		
		// page load
		wait.waitForPageLoad();
				
		Assert.assertNotSame(feed.posted_content_afterPost().textContent(), 
				"Excited to share a quick update from our latest project!", 
				"Post is not deleted!");

	}
	
	//Verify that count increases when user likes any post.
	@Test
	public void like_Post() {
		util = new ElementUtil(page);
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);

		// posting
		feed.nav_NewsFeed().click();
		feed.btn_Media().click();

		post = new CreatePostPage(page);
		post.txt_postText().fill("Excited to share a quick update from our latest project!");
		post.btn_Post().click();
		
		// page load
	    wait.waitForPageLoad();
		
	    //Like a post
	    feed.like_a_Post();
	   
	    
	    
		
	}
}
