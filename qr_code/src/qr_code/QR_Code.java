package qr_code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 
 * @author maichongju
 * @version 0.1
 */
public class QR_Code {
	private Color pen_color; // Default color is black
	private Color background_color;
	private int width;
	private int height;

	/**
	 * Default width and height will be 140 the default color for the QrCode will be
	 * black
	 */
	public QR_Code() {
		width = height = 140;
		this.pen_color = Color.BLACK;
		this.background_color = Color.WHITE;

	}

	/**
	 * The dimension will be set to the closest integer value
	 * 
	 * @param d
	 *            Dimension need to be set for the QrCode
	 */
	public QR_Code(Dimension d) {
		this();
		this.width = (int) d.getWidth();
		this.height = (int) d.getHeight();
	}

	/**
	 * Constor with assigned width and height
	 * 
	 * @param width
	 *            QrCode Width
	 * @param height
	 *            QrCode Height
	 */
	public QR_Code(final int width, final int height) {
		this();
		this.width = width;
		this.height = height;
	}

	/**
	 * Constor with assigned Background Color and Front color
	 * 
	 * @param Background
	 * @param front
	 */
	public QR_Code(final Color Background, final Color front) {
		this();
		this.pen_color = front;
		this.background_color = Background;
	}

	/**
	 * This function will set the pen color for the QeCode
	 * 
	 * @param PenColor
	 */
	public void setPenColor(final Color PenColor) {
		this.pen_color = PenColor;
	}

	/**
	 * This function will return the current pen color for the QrCode
	 * 
	 * @return current pen Color for the the QrCode
	 */
	public Color getPenColor() {
		return this.pen_color;
	}

	/**
	 * Set the QrCode background color to given color
	 * 
	 * @param background
	 *            Background color need to be set
	 */
	public void setBackgroundColor(final Color background) {
		this.background_color = background;
	}

	/**
	 * Return the current background color for the QrCode
	 * 
	 * @return Color Current color for the QrCode
	 */
	public Color getBackGroundColor() {
		return this.background_color;
	}

	/**
	 * This function will reset all the value to the default
	 */
	public void Reset() {
		width = height = 140;
		this.pen_color = Color.BLACK;
		this.background_color = Color.WHITE;
	}

	/**
	 * Generate the QrCode with giving content and save it to the given imgpath. All
	 * the setting are preset
	 * 
	 * @param content
	 *            Information need to be convert to QRCode
	 * @param imgpath
	 *            Image path that the be save
	 * @throws Exception
	 *             All Exception
	 */
	public void getQrcodeImageByContents(String content, String imgpath) throws Exception {

		try {
			Qrcode qrCode = new Qrcode();
			// Set the QrCode Error Correct
			qrCode.setQrcodeErrorCorrect('L');
			qrCode.setQrcodeEncodeMode('B');

			// Set the size of QrCode
			qrCode.setQrcodeVersion(7);
			// Set the size of image
			BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

			// Create the QrCode image
			Graphics2D gs = bufImg.createGraphics();

			// Set the image background color
			gs.setBackground(background_color);

			// Set a rectangle of the Image
			gs.clearRect(0, 0, width, height);

			// Set the QrCode color
			gs.setColor(pen_color);

			// Save the information in to an array
			byte[] contentarray = content.getBytes("gb2312");

			// Offset
			int pixoff = 2;

			// Output the QrCode
			if (contentarray.length > 0 && contentarray.length < 120) {
				boolean[][] codearray = qrCode.calQrcode(contentarray);
				for (int i = 0; i < codearray.length; i++) {
					for (int j = 0; j < codearray.length; j++) {
						if (codearray[i][j]) {
							gs.fillRect(i * 3 + pixoff, j * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("Error");
			}

			gs.dispose();
			bufImg.flush();

			// Generate QrCode
			File file = new File(imgpath);
			ImageIO.write(bufImg, "png", file);
			System.out.println("Done");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
