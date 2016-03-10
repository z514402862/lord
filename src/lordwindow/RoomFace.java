/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author zzl
 */
public class RoomFace extends MainFrm implements Constant {

	private JLabel mainLabel;

	private SeatLabel noPerson[] = new SeatLabel[3];

	private ReceiveServerMessageThread recThread;

	private int ifSit = -1;

	private GameFace gameface;

	public RoomFace() {
		mainFrm();
		mainLabel();
		seatLbl();
		recThread = new ReceiveServerMessageThread();
		user.setRecThread(recThread);
		recThread.start();
	}

	public void mainFrm() {
		this.setSize(816, 680);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout(null);
		this.setLocationRelativeTo(this);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				//System.out.println(ifSit);
				if (ifSit > -1) {
					JOptionPane.showMessageDialog(e.getComponent(), "房间被隐藏");
					gameface.roomSetVisible();
					RoomFace.this.setVisible(false);
				} else {
					int exi = JOptionPane.showConfirmDialog(null, "是否要退出游戏？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (exi == JOptionPane.YES_NO_OPTION) {

						if (user.getSocket() != null) {
							try {

								user.getOut().println("close");
								user.getOut().flush();
								user.getOut().println(user.getUserName());
								user.getOut().flush();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

						System.exit(0);
					} else {
						return;
					}
				}

			}
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void mainLabel() {
		mainLabel = new JLabel();
		mainLabel.setIcon(gmWtMainLbl);
		mainLabel.setLayout(null);

		this.add(mainLabel);
	}

	public void seatLbl() {

		for (int i = 0; i < 3; i++) {
			noPerson[i] = new SeatLabel(this, noPersonI);
			noPerson[i].setPosition(i);
			noPerson[i].setBorder(new LineBorder(Color.BLACK));
		}
		noPerson[0].setBounds(150, 200, 90, 112);
		mainLabel.add(noPerson[0]);

		noPerson[1].setBounds(570, 200, 90, 112);
		mainLabel.add(noPerson[1]);

		noPerson[2].setBounds(360, 450, 90, 112);
		mainLabel.add(noPerson[2]);
	}

	public void setIfSit(int ifSit) {
		this.ifSit = ifSit;
	}

	public int getIfSit() {
		return this.ifSit;
	}

	public SeatLabel getNoperson(int ifSit) {
		return this.noPerson[ifSit];
	}

	public GameFace getGameface() {
		return gameface;
	}

	public void setGameface(GameFace gameface) {
		this.gameface = gameface;
	}

	public ReceiveServerMessageThread getRecThread() {
		return recThread;
	}

	public void setRecThread(ReceiveServerMessageThread recThread) {
		this.recThread = recThread;
	}
	
}
