package qr_code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.jar.Attributes;

public class Utility {
	/**
	 * This function will set the given path file to be hidden
	 * 
	 * @param path
	 *            the file need to be hidden
	 */
	public static void sethidden(final String path) throws Exception {
		try {
			
			final File f = new File(path);
			System.out.println(f.isHidden());
			Path p = Paths.get(path);
			Files.setAttribute(p, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
			System.out.println(f.isHidden());
			//DosFileAttributes dos = Attributes.
		} catch (NullPointerException e0) {
			throw new Exception("File not exist!");
		} catch (Exception e) {
			throw e;
		}
	}
}
