package qr_code;

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
		Startup();

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
			frame.setVisible(true);
		}
	};

	/**
	 * Startup.
	 */
	private static void Startup() {
		try {
		final QR_Code temp_qr = new QR_Code();
		final String current_path = System.getProperty("user.dir") + "\\Image";
		if (!Utility.isFileExist(current_path)) {
			new File(current_path).mkdir();
			temp_qr.getQrcodeImageByContents(DEFAULT_MESSAGE, current_path + "\\Welcome.png");
		}
		else {
			if (!Utility.isFileExist(current_path + "\\Welcome.png")) {
				temp_qr.getQrcodeImageByContents(DEFAULT_MESSAGE, current_path + "\\Welcome.png");
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
