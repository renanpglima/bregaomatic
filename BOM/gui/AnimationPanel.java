package gui;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AnimationPanel extends JPanel {
	private JLabel background = null;
	
	/**
	 * This is the default constructor
	 */
	public AnimationPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setBounds(new Rectangle(0, 0, 800, 220));
		background = new JLabel();
		background.setDoubleBuffered(true);
		background.setPreferredSize(new Dimension(800, 220));
        String imgName = "/gui/images/brega.gif";
        background.setIcon(new ImageIcon(getClass().getResource(imgName)));
        this.add(background);
	}
	
}
