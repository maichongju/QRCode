package qr_code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO: Auto-generated Javadoc
/**
 * The Class Utility.
 */
public class Utility {
	
	/**
	 * This function will set the given path file to be hidden.
	 *
	 * @param path            the file need to be hidden
	 * @throws Exception the exception if file not exise
	 * @throws NoSuchFileException
	 * 				The file is not exist
	 */
	public static void sethidden(final String path) throws NoSuchFileException,Exception {
			final Path p = Paths.get(path);
			Files.setAttribute(p, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
	}

	/**
	 * This function will delete the given file path file.
	 *
	 * @param filepath            The file path need to be delete
	 */
	public static void deleteFile(final String filepath) {
		try {
			final File f = new File(filepath);
			f.delete();
		} catch (Exception e) {

		}
	}

	/**
	 * This function will return the status of the given file, true if the file is
	 * exist false otherwise.
	 *
	 * @param filepath the filepath
	 * @return boolean true if the file exist otherwise false
	 */
	public static boolean isFileExist(final String filepath) {
		final File f = new File(filepath);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
