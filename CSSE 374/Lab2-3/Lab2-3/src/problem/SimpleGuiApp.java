package problem;

import java.awt.Rectangle;

public class SimpleGuiApp {

	public static void main(String[] args) throws Exception {
		Configuration.setTestMode(true);
		Configuration.setDefaultOS(Configuration.WINDOWS);
		
		// Also try this configuration and comment out previous two lines
		// Configuration.setTestMode(false);
		
		
		Window window = new Window("This is a Window", new Rectangle(200,200, 500, 500));
		AbstractComponent label = new Label(window, "This is a label", new Rectangle(5,30, 200, 25));
		AbstractComponent button = new Button(window, "BUTTON!", new Rectangle(5, 30, 200, 25));
		window.show();
		
		for(int i = 10; i <= 100; i+=10) {
			Thread.sleep(200);
			label.setBounds(new Rectangle(5 + i, 30 + i, 300, 25));
		}
		
		TextBox text = new TextBox("This is a text", new Rectangle(300,50, 100, 25));
		window.addChild(text);
	}
}
