package qr_code;

import java.awt.Color;

public class qrcolor {
	
	public static final Color DEFAULT_BACKGOUND_COLOR = Color.WHITE;
	public static final Color DEFAULT_FRONT_COLOR = Color.BLACK;
	private Color backcolor;
	private Color frontcolor;
	private final QR_Code qr;
	
	
	public qrcolor(QR_Code qrcode) {
		this.qr = qrcode;
		this.backcolor = qr.getBackGroundColor();
		this.frontcolor = qr.getPenColor();
	}
	// Need to setter for front and background color
	
	
	/**
	 * This function will set the background color to qrcolor class
	 * @param c The color need to be store (Color)
	 */
	public void setBackgound(Color c) {
		backcolor = c;
	}
	
	/**
	 * This function will set the front color to qrclor class
	 * @param c The Front color need to be store (Color)
	 */
	public void setFront(Color c) {
		frontcolor = c;
	}
	/**
	 * This function will apply user setting to QR_Code 
	 */
	public void ApplyColor() {
		qr.setBackgroundColor(backcolor);
		qr.setPenColor(frontcolor);
	}
	
	/**
	 * This function will restore the color back to default
	 */
	public void RestoreColor() {
		backcolor = DEFAULT_BACKGOUND_COLOR;
		frontcolor = DEFAULT_FRONT_COLOR;
	}
}
