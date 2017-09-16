package qr_code;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

public class Main {

	public static final String DEFAULT_MESSAGE = "Hello World";

	/**
	 * Main program.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(runJFrame);
	}

	/** The run J frame. */
	static Runnable runJFrame = new Runnable() {

		@Override
		public void run() {
			final JFrame frame = new JFrame("QRCode");
			final QR_Code qr = new QR_Code();
			final Menu menu = new Menu(qr);
			final JMenuBar menubar = menu.getMenuBar();
			final SpawnView view = new SpawnView(menu, qr);
			frame.setJMenuBar(menubar);
			frame.setContentPane(view);
			frame.setSize(view.getSize());
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(WindowEvent e) {

				}

				@Override
				public void windowClosed(WindowEvent e) {

				}

				@Override
				public void windowClosing(WindowEvent e) {
					final String path = System.getProperty("user.dir");
				}

				@Override
				public void windowDeactivated(WindowEvent e) {

				}

				@Override
				public void windowDeiconified(WindowEvent e) {

				}

				@Override
				public void windowIconified(WindowEvent e) {

				}

				@Override
				public void windowOpened(WindowEvent e) {
					Startup();
				}

			});
			frame.setVisible(true);
		}

	};

	/**
	 * Startup.
	 */
	private static void Startup() {
		final String current_path = System.getProperty("user.dir") + "\\Image";
		if (!Utility.isFileExist(current_path)) {
			new File(current_path).mkdir();
		}
		WelcomeImage(current_path);

	}

	private static void WelcomeImage(final String path) {
		final String imgpath = path + "\\Welcome.png";
		final QR_Code wqr = new QR_Code();
		try {
			wqr.getQrcodeImageByContents(DEFAULT_MESSAGE, imgpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
