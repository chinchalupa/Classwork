package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class provides the support for the main window widget and also serves as
 * the top-level component in the component hierarchy.
 *  
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class Window extends AbstractComponent {
	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;
	
	private String title;
	private JFrame frame;
	private JPanel panel;

	public Window() {
		this(null);
	}

	public Window(String title) {
		this(title, null);
	}

	@SuppressWarnings("serial")
	public Window(String title, Rectangle bound) {
		super(null, bound);

		if(title == null)
			this.title = "";
		else
			this.title = title;

		this.frame = new JFrame(this.title);
		this.frame.setUndecorated(true);	
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw((Graphics2D) g);
			}
		};
		this.frame.setContentPane(this.panel);
	}

	@Override
	public final void fireUpdate() {
		this.panel.repaint();
	}
	
	public void show() {
		this.frame.setBounds(this.getBounds());
		this.frame.setVisible(true);
	}
	
	@Override
	public void drawComponent(Graphics2D g) {
		if(Configuration.getOS().contains("windows"))
			this.drawForMSWindow(g);
		else if(Configuration.getOS().contains("ubuntu"))
			this.drawForUbuntu(g);
		else
			throw new UnsupportedOperationException("The GUI framework  does not yet support your operating system!");
	}

	@Override
	public String getText() {
		return title;
	}

	@Override
	public void setText(String text) {
		this.title = text;
	}

	@Override
	public void drawForMSWindow(Graphics2D g) {
		// Draw the border
		g.setColor(Color.gray);
		g.draw3DRect(1, 1, this.getBounds().width - H_SPACE, (int)this.getBounds().height - V_SPACE, true);

		// Draw the title bar
		g.setColor(new Color(49, 68, 176));
		g.fill3DRect(1, 1, this.getBounds().width - H_SPACE , TITLE_HEIGHT - 5, true);
		
		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 		
		g.setColor(Color.white);
		g.drawString(this.title, 5, 17);
	}

	@Override
	public void drawForUbuntu(Graphics2D g) {
		// Draw the border
		g.setColor(new Color(48,0,0));
		g.draw3DRect(1, 1, this.getBounds().width - H_SPACE, this.getBounds().height - V_SPACE, true);

		// Draw the title bar
		g.setColor(new Color(96,0,0));
		g.fill3DRect(1, 1, (int)this.getBounds().width - H_SPACE , TITLE_HEIGHT, true);
		
		// Draw the title
		g.setFont(new Font("Arial", Font.PLAIN, 17)); 		
		g.setColor(Color.white);
		g.drawString(this.title, 5, 20);
	}
}
