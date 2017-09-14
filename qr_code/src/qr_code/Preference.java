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

public class Preference {

	private final QR_Code qr;
	private JFrame frame = null;
	private final JPanel p = new JPanel();
	private final JButton bClose = new JButton("Close");
	private final JButton bbackground = new JButton("Change background color");
	private final JButton bfront = new JButton("Change font color");
	private final JLabel lcbcolor = new JLabel("Color");
	private final JLabel lcfcolor = new JLabel("Color");
	private final JLabel current = new JLabel("Current Color");

	/**
	 * 
	 */
	public Preference(QR_Code qr) {
		this.qr = qr;

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
	 * This function will set up all the window for the preference window
	 */
	private void setWindow() {
		frame.addWindowListener(new windowslistener());
		frame.setContentPane(p);
		frame.setSize(350, 180);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * Will show if the Preference is show
	 * 
	 * @return true Preference window is shown false otherwise
	 */
	public boolean isShow() {
		return frame.isVisible();
	}

	/**
	 * This function will set up all the panel for the preference window
	 */
	private void setPanel() {
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(bbackground).addComponent(bfront));
		hGroup.addGap(30);
		hGroup.addGroup(layout.createParallelGroup().addComponent(current).addComponent(lcbcolor).addComponent(lcfcolor)
				.addComponent(bClose));
		layout.setHorizontalGroup(hGroup);

		SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap(15);
		vGroup.addGroup(layout.createParallelGroup().addComponent(current));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(bbackground).addComponent(lcbcolor));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(bfront).addComponent(lcfcolor));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(bClose));

		layout.setVerticalGroup(vGroup);

		p.setLayout(layout);

		setColor();
	}

	/**
	 * This function will set up the label color
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
	 * This function will set up all the button for the preference window
	 */
	private void setButton() {
		bbackground.setActionCommand("BackgroundColor");
		bfront.setActionCommand("FrontColor");

		ButtonListener l = new ButtonListener();
		bbackground.addActionListener(l);
		bfront.addActionListener(l);
		bClose.addActionListener(l);
	}

	/**
	 * 
	 * @author maichongju
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent key) {
			String name = key.getActionCommand();
			System.out.println(name);
			try {
				switch (name) {
				case "BackgroundColor": {
					Color temp = JColorChooser.showDialog(null, "Choose the background color", qr.getPenColor());
					if (temp != null) {
						qr.setBackgroundColor(temp);
						lcbcolor.setBackground(qr.getBackGroundColor());

					}
					break;
				}
				case "FrontColor": {
					Color temp = JColorChooser.showDialog(null, "Choose the background color", qr.getPenColor());
					if (temp != null) {
						qr.setPenColor(temp);
						lcfcolor.setBackground(qr.getPenColor());
					}
					break;
				}
				case "Close": {
					frame.dispose();
					frame = null;

					break;
				}
				}
				System.out.println("Backgeound" + qr.getBackGroundColor());
				System.out.println("Pen" + qr.getPenColor().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @author CHONGJUMAI
	 *
	 */
	private class windowslistener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			frame = null;

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
