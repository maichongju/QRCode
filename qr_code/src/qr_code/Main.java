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
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosed(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosing(WindowEvent arg0) {
					try {
						final String current_path = System.getProperty("user.dir");
						if (Utility.isFileExist(current_path + "\\Image\\qrcode.png")) {
							Utility.deleteFile(current_path + "\\Image\\qrcode.png");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				@Override
				public void windowDeactivated(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeiconified(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowIconified(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowOpened(WindowEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
			frame.setVisible(true);

		}
	};

	/**
	 * Will check if the Image folder exist, if not exist it will create it, if the
	 * folder is exist then the will check if the the welcome.png exist. if not then
	 * will be create.
	 */
	private static void Startup() {
		try {
			final QR_Code temp_qr = new QR_Code();
			final String current_path = System.getProperty("user.dir") + "\\Image";
			if (!Utility.isFileExist(current_path)) {
				new File(current_path).mkdir();
				temp_qr.getQrcodeImageByContents(DEFAULT_MESSAGE, current_path + "\\Welcome.png");
			} else {
				if (!Utility.isFileExist(current_path + "\\Welcome.png")) {
					temp_qr.getQrcodeImageByContents(DEFAULT_MESSAGE, current_path + "\\Welcome.png");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
