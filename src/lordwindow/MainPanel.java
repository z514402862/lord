/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class MainPanel extends JPanel implements Constant{

    private ImageIcon icon;
    private Graphics gs;
	
	private int width;
	private int height;

	public MainPanel(ImageIcon icon){
		this.icon = icon;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), this);
	}

}
