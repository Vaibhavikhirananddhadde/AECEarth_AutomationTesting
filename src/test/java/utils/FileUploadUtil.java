package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FileUploadUtil {

	 public static void uploadMedia(
	            Page page,
	            String mediaType,
	            String filePath
	    ) {

	        Path path = Paths.get(filePath);

	        // Validate file exists
	        if (!Files.exists(path)) {
	            throw new RuntimeException(
	                    "Upload file does not exist: " + filePath
	            );
	        }

	        // Validate media type
	        if (!mediaType.equalsIgnoreCase("Photo")
	                && !mediaType.equalsIgnoreCase("Video")) {

	            throw new IllegalArgumentException(
	                    "mediaType must be Photo or Video"
	            );
	        }

	        FileChooser fileChooser = page.waitForFileChooser(() -> {

	            page.getByRole(AriaRole.BUTTON,
	                    new Page.GetByRoleOptions()
	                            .setName(mediaType)
	                            .setExact(true)
	            ).click();

	        });

	        fileChooser.setFiles(path);

	        System.out.println(
	                mediaType + " uploaded successfully: "
	                        + path.getFileName()
	        );
	    }
	
}
