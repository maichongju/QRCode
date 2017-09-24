package qr_code;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class Preference.
 */
public class Preference {

	/** The qr. */
	private final QR_Code qr;
	
	/** The temp qr. */
	private final QR_Code temp_qr;
	
	/** The frame. */
	private JFrame frame = null;
	
	/** The p. */
	private final JPanel p = new JPanel();
	
	/** The b close. */
	private final JButton bClose = new JButton("Close");
	
	/** The bbackground. */
	private final JButton bbackground = new JButton("Change background color");
	
	/** The bfront. */
	private final JButton bfront = new JButton("Change font color");
	
	/** The bapply. */
	private final JButton bapply = new JButton("Apply and Close");
	
	/** The lcbcolor. */
	private final JLabel lcbcolor = new JLabel("Color");
	
	/** The lcfcolor. */
	private final JLabel lcfcolor = new JLabel("Color");
	
	/** The current. */
	private final JLabel current = new JLabel("Current Color");

	/**
	 * Instantiates a new preference.
	 *
	 * @param qr the qr
	 */
	public Preference(QR_Code qr) {
		this.qr = qr;
		this.temp_qr = new QR_Code(qr);

	}

	/**
	 * This function will determined if the preference is opened already. If the
	 * preference window is open already it will pop up a error message, and will
	 * not show the preference windows again. other wise it will open the preference
	 * window.
	 */
	public void Show() {
		if (frame == null) {
			this.frame = new JFrame("Preference");
			setButton();
			setWindow();
			setPanel();
		} else {
			frame.toFront();
			frame.repaint();
		}
	}

	/**
	 * This function will set up all the window for the preference window.
	 */
	private void setWindow() {
		frame.addWindowListener(new windowslistener());
		frame.setContentPane(p);
		frame.setSize(500, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
	}

	/**
	 * Will show if the Preference is show.
	 *
	 * @return true Preference window is shown false otherwise
	 */
	public boolean isShow() {
		return frame.isVisible();
	}

	/**
	 * This function will set up all the panel for the preference window.
	 */
	private void setPanel() {
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(bbackground).addComponent(bfront));
		hGroup.addGap(30);
		hGroup.addGroup(layout.createParallelGroup().addComponent(current).addComponent(lcbcolor).addComponent(lcfcolor)
				.addComponent(bapply));
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(bClose));
		layout.setHorizontalGroup(hGroup);

		SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap(15);
		vGroup.addGroup(layout.createParallelGroup().addComponent(current));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(bbackground).addComponent(lcbcolor));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(bfront).addComponent(lcfcolor));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(bapply).addComponent(bClose));

		layout.setVerticalGroup(vGroup);

		p.setLayout(layout);

		setColor();
	}

	/**
	 * This function will set up the label color.
	 */
	private void setColor() {
		lcbcolor.setOpaque(true);
		lcfcolor.setOpaque(true);
		lcfcolor.setSize(10, 10);
		lcbcolor.setSize(10, 10);
		lcbcolor.setBackground(qr.getBackGroundColor());
		lcfcolor.setBackground(qr.getPenColor());
		lcfcolor.setForeground(qr.getBackGroundColor());
		lcfcolor.setForeground(qr.getPenColor());

	}

	/**
	 * This function will set up all the button for the preference window.
	 */
	private void setButton() {
		bbackground.setActionCommand("BackgroundColor");
		bfront.setActionCommand("FrontColor");
		bapply.setActionCommand("ApplyAndClose");

		ButtonListener l = new ButtonListener();
		bbackground.addActionListener(l);
		bfront.addActionListener(l);
		bClose.addActionListener(l);
	}

	/**
	 * ButtonListener for all the button in Preference panel.
	 *
	 * @author maichongju
	 */
	private class ButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent key) {
			String name = key.getActionCommand();
			System.out.println(name);
			try {
				switch (name) {
				case "BackgroundColor": {
					Color temp = JColorChooser.showDialog(null, "Choose the background color", qr.getPenColor());
					if (temp != null) {
						temp_qr.setBackgroundColor(temp);
						lcbcolor.setBackground(temp_qr.getBackGroundColor());

					}
					break;
				}
				case "FrontColor": {
					Color temp = JColorChooser.showDialog(null, "Choose the background color", qr.getPenColor());
					if (temp != null) {
						temp_qr.setPenColor(temp);
						lcfcolor.setBackground(temp_qr.getPenColor());
					}
					break;
				}
				case "Close": {
					frame.dispose();
					frame = null;

					break;
				}
				//Color Bug
				case "ApplyAndClose": {
					qr.ApplyColor(temp_qr);
					frame.dispose();
					frame = null;
					break;
				}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * The Class windowslistener.
	 *
	 * @author CHONGJUMAI
	 */
	private class windowslistener implements WindowListener {

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			frame = null;

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
