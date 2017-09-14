package qr_code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class Menu {
	final String AboutMsg = "Create By: Chongju Mai";
	final JMenuBar menubar = new JMenuBar();
	final JMenu menu_option = new JMenu("Option");
	final JMenuItem preference = new JMenuItem("Preference");
	final JMenuItem about = new JMenuItem("About");
	final Version ver = new Version();
	final Preference preferencec; 
	final QR_Code qr ;
	/**
	 * Main construe for Menu
	 */
	public Menu(QR_Code qr) {
		this.qr = qr;
		this.preferencec = new Preference(qr);
		addMenu();
		setShortCut();
		addListener();
	}

	/**
	 * This function will add all the components to the Menu
	 */
	private void addMenu() {

		// Add Item to Option Menu
		menu_option.add(preference);
		menu_option.add(new JSeparator());
		menu_option.add(about);

		// Add Option Menu to Menu Bar
		menubar.add(menu_option);

	}

	/**
	 * This function will set all the short cut for all the item and menu
	 */
	private void setShortCut() {
		menu_option.setMnemonic('O');
		preference.setMnemonic('P');
		about.setMnemonic('A');
	}

	/**
	 * This function will add the listener to all item
	 */
	private void addListener() {
		final MenuListener l = new MenuListener();
		preference.addActionListener(l);
		about.addActionListener(l);
	}

	/**
	 * Return the JMenuBar that create by this class
	 * 
	 * @return JMenuBar The JMenuBar create for this program
	 */
	public JMenuBar getMenuBar() {
		return menubar;
	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent key) {
			String KeyName = key.getActionCommand();
			System.out.println(KeyName);
			switch (KeyName) {
			case "Preference": {
				preferencec.Show();
				// Color color = JColorChooser.showDialog(null, "Choose your color",
				// Color.WHITE);
				break;
			}
			case "About": {
				JOptionPane.showMessageDialog(null, AboutMsg + "\nV " +ver.getVersion() , "About", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			}
		}

	}
}
