/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author zzl
 */
public interface Constant {

	UserInfor user = new UserInfor();

	ImageIcon loginMainPanel = new ImageIcon("Image\\enter.jpg");
	ImageIcon gmWtMainLbl = new ImageIcon("Image\\waitbackground.jpg");
	ImageIcon headPortiait1 = new ImageIcon("Image\\headportrait1.png");
	ImageIcon headPortiait2 = new ImageIcon("Image\\headportrait2.png");
	ImageIcon headPortiait3 = new ImageIcon("Image\\headportrait3.png");
	ImageIcon noPersonI = new ImageIcon("Image\\noperson.jpg");
	ImageIcon writtenLabel = new ImageIcon("Image\\gamefacecenter.png");

	Font font17 = new Font("楷体", Font.BOLD, 17);
	Font font26 = new Font("楷体", Font.BOLD, 26);

	int registerLabelW = 100;
	int registerLabelH = 40;

	int registertextW = 190;
	int registertextH = 30;

	int loginTextW = 230;
	int loginTextH = 30;

	int loginLabelW = 100;
	int loginLabelH = 40;

	Toolkit toolKit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolKit.getScreenSize();
	int computerWidth = (int) dimension.getWidth();
	int computerHeigth = (int) dimension.getHeight();

	
}
