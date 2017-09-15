package qr_code;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

public class Main {
	/**
	 * Main program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(runJFrame);
	}
	

	static Runnable runJFrame = new Runnable() {

			@Override
			public void run() {
				final JFrame frame = new JFrame("QRCode");
				final QR_Code qr = new QR_Code();
				final Menu menu= new Menu(qr);
				final JMenuBar menubar = menu.getMenuBar();
				final SpawnView view = new SpawnView(menu,qr);
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
						
					}
					
				});
				frame.setVisible(true);
			}
			
		};

		
		
}
