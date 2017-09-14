package qr_code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class SpawnView extends JPanel {
	public static final int WIDTH = 350;
	public static final int HIGHT = 250;
	public static final int MAX_LENGTH = 120;
	final String Defmsg = "Enter the content here!";
	final String sucsta = "Success";
	final String falsta = "Please Try Again";
	final QR_Code qr;
	final JPanel LeftPanel = new JPanel();
	final JPanel RightPanel = new JPanel();
	final JButton BEnter = new JButton("Enter");
	final JButton BCalcel = new JButton("Reset");
	final JButton BSaveAs = new JButton("Save As");
	final JTextArea textarea = new JTextArea(Defmsg);
	final JLabel status = new JLabel("Welcome", JLabel.CENTER);
	final JLabel qrcode = new JLabel();
	final JPanel wordcount = new JPanel();
	final JLabel wcurrent = new JLabel("", JLabel.RIGHT);
	final JLabel wmax = new JLabel("", JLabel.LEFT);
	final Menu menu;

	/**
	 * Setting the layout for the main view Contain two main layout, Left Panel and
	 * Right Panel
	 */
	public SpawnView(final Menu menu,final QR_Code qr) {
		this.menu = menu;
		this.qr = qr;
		this.setSize(WIDTH, HIGHT);
		this.setLeftLayout();
		this.setRightLayout();

		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(LeftPanel);
		this.add(RightPanel);
	}

	/**
	 * This function will set all the layout for the left hand side for the program
	 */
	private void setLeftLayout() {
		LeftPanel.setLayout(new BorderLayout());
		final JLabel LEnter = new JLabel("QrCode Text:");

		// Set up Text Area
		setTextArea();

		// Add the listener to all the button
		BEnter.addActionListener(new ButtonListener());
		BCalcel.addActionListener(new ButtonListener());

		// This is the Down of the left panel
		final JPanel Down = new JPanel();
		Down.setLayout(new GridLayout(1, 2, 10, 5));
		Down.add(BEnter);
		Down.add(BCalcel);

		// Create a Scroll Panel and put Text Area in it, Scroll Panel can allow the
		// Text Area Scroll along the page
		final JScrollPane j = new JScrollPane(textarea);

		LeftPanel.add(LEnter, BorderLayout.NORTH);
		LeftPanel.add(j, BorderLayout.CENTER); // Add the Text Area in to Left Panel
		LeftPanel.add(Down, BorderLayout.SOUTH);
	}

	/**
	 * This function will set all the layout for the right hand side for the program
	 */
	private void setRightLayout() {
		// Set up the beginning image
		try {
			String path = getCurrentPath() + "/Image/Welcome.png";
			setQrCode(path);

		} catch (IOException e) {
			setStatusText(falsta);
		}

		setWordCount();
		final JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(qrcode, BorderLayout.CENTER);
		p.add(wordcount, BorderLayout.SOUTH);
		BSaveAs.addActionListener(new ButtonListener());
		BSaveAs.setEnabled(false);
		RightPanel.setLayout(new BorderLayout());
		RightPanel.add(status, BorderLayout.NORTH);
		RightPanel.add(p, BorderLayout.CENTER);
		RightPanel.add(BSaveAs, BorderLayout.SOUTH);
	}

	/**
	 * This function will set up all the components for word count panel
	 */
	private void setWordCount() {
		wordcount.setLayout(new GridLayout(1, 2));

		updateCurrentWordCount();
		wmax.setText("/ " + Integer.toString(MAX_LENGTH));
		wordcount.add(wcurrent);
		wordcount.add(wmax);
	}

	/**
	 * The function will set up the text area
	 */
	private void setTextArea() {

		textarea.setLineWrap(true);
		Font f = textarea.getFont();
		textarea.setFont(new Font(f.getFontName(), f.getStyle(), f.getSize() + 3));
		textarea.addKeyListener(new TextAreaListener());
	}

	private void updateCurrentWordCount() {
		wcurrent.setText(Integer.toString(textarea.getText().length()));
	}

	/**
	 * Get the text showing on the Text Area
	 * 
	 * @return String that showing on the Text Area
	 */
	private String getTextAreaText() {
		return textarea.getText();
	}

	/**
	 * Set the text on the text area
	 * 
	 * @param text
	 *            The text need to be set on the text area (String)
	 */
	private void setTextAreaText(String text) {
		textarea.setText(text);
	}

	/**
	 * Set the text on the status label
	 * 
	 * @param text
	 *            The text need to be set on the status label (String)
	 */
	private void setStatusText(String text) {
		status.setText(text);
	}

	/**
	 * This function will return the Current path of the project
	 * 
	 * @return String. The path of the current project
	 * @throws IOException
	 *             IF the file have error then it will throw the IOException
	 */
	public static String getCurrentPath() throws IOException {
		File file = new File("");
		return file.getCanonicalPath();

	}

	/**
	 * This function will using the image the provide from the image path to the
	 * QrCode
	 * 
	 * @param imgpath
	 *            The path of the image need to be set
	 * @throws IOException
	 *             If there is no such file it will throw an exception out
	 */
	private void setQrCode(String imgpath) throws IOException {
		qrcode.setIcon(new ImageIcon(ImageIO.read(new File(imgpath))));
	}
	


	/**
	 * Inner class for all the Action listener for all the button
	 * 
	 * @author maichongju
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = e.getActionCommand(); // Get the String that show on the button
			System.out.println(name);
			switch (name) {
			case "Enter": {
				System.out.println(qr.getBackGroundColor());
				try {
					String content = getTextAreaText();
					if (content.length() < 120 && content.length() > 0) {
						final String imgpath = getCurrentPath() + "\\Image\\qrcode.png";
						qr.getQrcodeImageByContents(getTextAreaText(), imgpath);
						setQrCode(imgpath);
						Utility.sethidden(imgpath);
						setStatusText(sucsta);
						BSaveAs.setEnabled(true);
					} else {
						throw new Exception("Message words need to between 1 - 120");
					}

				} catch (Exception E) {
					setStatusText(falsta);
					JOptionPane.showMessageDialog(null, E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			}
			case "Reset": {
				setTextAreaText(Defmsg);
				setStatusText("Ready");
				updateCurrentWordCount();
				qr.Reset();
				break;
			}
			case "Save As": {
				try {
					final String imgpath = getCurrentPath() + "\\Image\\qrcode.png";
					SaveAs(imgpath);
				} catch (Exception E) {
					System.out.println(E.getMessage());
				}
				break;
			}
			}
		}

		/**
		 * This function will take a file path and save the file to user define location
		 * By default the type of file will be save as .png
		 * 
		 * @param ifilepath
		 *            The file path need to be save as
		 */
		private void SaveAs(String ifilepath) throws Exception {
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File(".png"));
			chooser.setFileFilter(new FileNameExtensionFilter(".png", "png"));
			chooser.showSaveDialog(null);
			File fs = chooser.getSelectedFile();

			String ofilepath = fs.getAbsolutePath();
			// Make sure user have correct enter the extend name of the file
			if (!ofilepath.endsWith(".png")) {
				ofilepath += ".png";
			}

			FileInputStream fi = new FileInputStream(ifilepath);
			FileOutputStream fo = new FileOutputStream(ofilepath);
			FileChannel in = fi.getChannel();
			FileChannel out = fo.getChannel();
			in.transferTo(0, in.size(), out);
			fi.close();
			fo.close();
			in.close();
			out.close();
		}
	}

	/**
	 * This class is the subclass for TextAreaListener. Will count the current
	 * number of words in TextArea and show to user.
	 * 
	 * @author maichongju
	 *
	 */
	private class TextAreaListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent key) {

		}

		@Override
		public void keyReleased(KeyEvent key) {
			updateCurrentWordCount();
			final int current = textarea.getText().length();
			Font f = wcurrent.getFont();
			if (current > 120) {
				wcurrent.setForeground(Color.red);
				wcurrent.setFont(new Font(f.getName(), Font.BOLD, f.getSize()));
			} else {
				wcurrent.setForeground(Color.BLACK);
				wcurrent.setFont(new Font(f.getName(), Font.PLAIN, f.getSize()));
			}
		}

		@Override
		public void keyTyped(KeyEvent key) {

		}

	}
}
