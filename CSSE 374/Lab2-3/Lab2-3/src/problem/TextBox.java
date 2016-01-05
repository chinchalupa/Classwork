package problem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * This class provides the support for the text box widget.
 *  
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class TextBox extends AbstractComponent {

	private static final int H_SPACE = 5;

	private String text;
	
	public TextBox() {
		this(null);
	}

	public TextBox(String text) {
		this(text, null);
	}

	public TextBox(String text, Rectangle bound) {
		this(null, text, bound);
	}

	public TextBox(IComponent parent, String text, Rectangle bound) {
		super(parent, bound);
		
		this.text = text;
		
		if(this.text == null)
			this.text = "";
	}
	
	/**
	 * Gets the text in the label.
	 */
	@Override
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets the text in the label. 
	 * Calling this method results in the call to {@link #fireUpdate()}, 
	 * which informs the component hierarchy to re-draw itself.
	 */
	@Override
	public void setText(String text) {
		this.text = text;
		this.fireUpdate();
	}

	@Override
	public void drawForMSWindow(Graphics2D g) {
		Rectangle bound = this.getBounds();
		
		
		// Draw the boarder after setting the thickness
		g.setColor(new Color(14,29,110));
		Rectangle2D border = new Rectangle2D.Float(bound.x, bound.y, bound.width, bound.height);
		g.setStroke(new BasicStroke(5));
		g.draw(border);
		
		// Draw the white fill
		g.setColor(Color.white);
		Rectangle2D fill = new Rectangle2D.Float(bound.x+2, bound.y+2, bound.width-2, bound.height-2);
		g.fill(fill);;
		
		// Draw the Fill
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 		
		g.setColor(Color.black);
		g.drawString(this.text, bound.x + H_SPACE, bound.y + 16);
	}

	@Override
	public void drawForUbuntu(Graphics2D g) {
		Rectangle bound = this.getBounds();
		
		
		// Draw the boarder after setting the thickness
		g.setColor(new Color(48,0,0));
		Rectangle2D border = new Rectangle2D.Float(bound.x, bound.y, bound.width, bound.height);
		g.setStroke(new BasicStroke(5));
		g.draw(border);
		
		// Draw the white fill
		g.setColor(Color.white);
		Rectangle2D fill = new Rectangle2D.Float(bound.x+2, bound.y+2, bound.width-2, bound.height-2);
		g.fill(fill);;
		
		// Draw the Fill
		g.setFont(new Font("Arial", Font.PLAIN, 12)); 		
		g.setColor(Color.black);
		g.drawString(this.text, bound.x + H_SPACE, bound.y + 16);
	}
}
