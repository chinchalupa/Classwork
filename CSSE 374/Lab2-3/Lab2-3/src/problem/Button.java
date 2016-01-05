package problem;

import java.awt.*;

/**
 * Created by wrightjt on 12/13/2015.
 */
public class Button extends AbstractComponent {

    private static final int H_SPACE = 3;

    private String text;

    public Button(String text) {
        this(text, null);
    }

    public Button(String text, Rectangle bound) {
        this(null, text, bound);
    }

    public Button(IComponent parent, String text, Rectangle bound) {
        super(parent, bound);

        this.text = text;

        if(this.text == null)
            this.text = "";
    }

    @Override
    public void drawForMSWindow(Graphics2D g) {
        Rectangle bound = this.getBounds();

        // Draw the title
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.setColor(Color.black);
        g.drawString(this.text, bound.x + H_SPACE, bound.y + 16);
    }

    @Override
    public void drawForUbuntu(Graphics2D g) {
        Rectangle bound = this.getBounds();

        // Draw the title
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.black);
        g.drawString(this.text, bound.x + H_SPACE, bound.y + 16);
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
