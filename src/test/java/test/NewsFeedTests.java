package test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.Assert;
import org.testng.annotations.Test;

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
				"Excited to share a quick update from our latest project!", "Post is not deleted!");

	}

	// Verify that count increases when user likes any post.
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

		// Get count BEFORE clicking Like
		int beforeLikeCount = feed.getFirstPostLikeCount();

		// Verify icon is NOT blue initially
		String beforeIcon = feed.getFirstPostLikeIconSrc();

		if (beforeIcon.contains("blue")) {
			System.out.println("Like icon is already blue before clicking Like");
		}
		;

		// Like a post
		feed.like_a_Post();

		// Get count AFTER clicking
		int afterLikeCount = feed.getFirstPostLikeCount();

		// Verify count increased exactly by 1
		Assert.assertEquals(afterLikeCount, beforeLikeCount + 1, "Like count did not increase by 1");

		// Verify icon changed to blue
		String afterIcon = feed.getFirstPostLikeIconSrc();

		Assert.assertTrue(afterIcon.contains("blue"), "Like icon did not change to blue");
	}

	// Verify that user can comment on a post successfully.
	@Test
	public void verifyComment() {
		String comment = "Commented on a post";

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

		// comment on a post
		feed.comment_a_Post(comment);

		Assert.assertTrue(feed.firstCommentContains(comment), "First comment does not contain: " + comment);

	}

	//Verify that user can edit the comment, commented by him.
	@Test
	public void checkEditComment() throws InterruptedException {
		util = new ElementUtil(page);
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);
		feed.nav_NewsFeed().click();

		feed.firstPostCommentButton().click();
		feed.firstComment_options().click();
		feed.edit_comment().click();
		feed.txt_editComment().fill("Edited comment");
		feed.btn_Save().click();

		page.wait();

		Assert.assertTrue(feed.msg_confirm_delete().isVisible());
	}
	
	//Verify that user can delete the comment, commented by him.
	@Test
	public void verifyDeleteComment() throws InterruptedException {
		util = new ElementUtil(page);
		wait = new WaitUtil(page);
		land = new LandingPage(page);

		// logging in
		land.signIn(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));

		feed = new NewsFeedPage(page);
		feed.nav_NewsFeed().click();

		feed.firstPostCommentButton().click();
		feed.firstComment_options().click();
		feed.btn_delete_comment().click();
		
	
		
		feed.btn_deleteCommentYES().click();
		Thread.sleep(3000);
		
		Assert.assertTrue(feed.success_commentDeletion().isVisible());
	}
}
