/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author zzl
 */
public class GameFace extends MainFrm implements Constant,ActionListener{

	private Container container = null;

	private Image buffereimg;
	private Graphics buffereG;

	private int screenWidth;
	private int screenHeigth;

	private int position;

	private PokerLabel[] pokerlab = new PokerLabel[54];
	private List<PokerLabel> playerList[] = new ArrayList[3];
	private List<PokerLabel> currentList[] = new ArrayList[3];
	private List<PokerLabel> lordList;

	private boolean ifReady1 = false;
	private boolean ifReady2 = false;
	private boolean ifReady3 = false;
	private boolean ifReady = false;

	private boolean ifDealPoker1 = false;
	private boolean ifDealPoker2 = false;
	private boolean ifDealPoker3 = false;

	private JButton readyBtn;
	private JLabel writtenlab;
	private JButton sortBtn;

	private RoomFace roomface;

	private JButton roomSetVisible;

	private String message;

	private int heightLeft = 50;
	private int widthLeft = 70;
	private int leftheightSpace = 20;
	private int leftwidthSpace = 0;

	private int heightRight = 50;
	private int widthRight = 849;
	private int RightheightSpace = 20;
	private int RightwidthSpace = 0;

	private int mywidth = 259;
	private int myheight = 475;
	private int myheightSpace = 0;
	private int mywidthSpace = 25;

	private int lordwidth = 329;

	private JLabel readyLeft;
	private JLabel readyRight;

	private playerWaitThread waitThread;
	private JButton nolordBtn;
	private JButton knocklordBtn;
	private JButton outpokerBtn;
	private JButton nopokerBtn;

//	public GameFace() {
//
//		Window();
//		readyBtn();
//		sortBtn();
//		writtenlab();
//		pokerLabel();
//		readyLabel();
//
//		readyBtn.addMouseListener(new readyBtnLis());
//		this.setVisible(true);
//	}
	public GameFace(RoomFace roomface) {

		this.roomface = roomface;

		Window();
		readyBtn();
		sortBtn();
		writtenlab();
		//pokerLabel();

		
		waitThread = new playerWaitThread();
		waitThread.start();

		this.setVisible(true);
	}

	public void Window() {

		screenWidth = 1024;
		screenHeigth = 675;
		
		this.setSize(screenWidth, screenHeigth);
		this.setFont(font17);
		this.setTitle("   斗地主");
		container = this.getContentPane();
		container.setBackground(new Color(0, 102, 153));
		this.setLocationRelativeTo(this);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int exi = JOptionPane.showConfirmDialog(null, "是否要退出游戏界面？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (exi == JOptionPane.YES_NO_OPTION) {
					roomface.getNoperson(roomface.getIfSit()).setIcon(noPersonI);
					roomface.getNoperson(roomface.getIfSit()).setIfSit(true);
					roomface.setIfSit(-1);
					roomface.setVisible(true);
					waitThread.interrupt();
					user.getOut().println("-100:false");
					user.getOut().flush();

					GameFace.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	public void readyBtn() {
		readyBtn = new JButton("准    备");
		readyBtn.setFont(font17);
		readyBtn.setBounds(screenWidth - 450, screenHeigth - 240, 110, 30);
		container.add(readyBtn);

	}

	public void sortBtn() {
		sortBtn = new JButton("排   序");
		sortBtn.setFont(font17);
		sortBtn.setBounds(screenWidth - 120, screenHeigth - 120, 110, 30);
		sortBtn.setVisible(false);
		container.add(sortBtn);
	}

	public void writtenlab() {
		writtenlab = new JLabel();
		writtenlab.setBounds((screenWidth - writtenLabel.getIconWidth()) / 2, (screenHeigth - 270) / 2, writtenLabel.getIconWidth(), writtenLabel.getIconHeight());
		writtenlab.setIcon(writtenLabel);
		container.add(writtenlab);
		container.setComponentZOrder(writtenlab, 0);
	}

	public void roomSetVisible() {
		roomSetVisible = new JButton("显示房间");
		roomSetVisible.setFont(font17);
		roomSetVisible.setBounds(900, 20, 100, 30);
		container.add(roomSetVisible);

		roomSetVisible.addMouseListener(new roomSetVisibleBtnLis());
		GameFace.this.repaint();
	}

	public void readyLeftLabel() {
		readyLeft = new JLabel("准备");

		readyLeft.setBounds(198, 200, 56, 30);
		readyLeft.setFont(font26);
		container.add(readyLeft);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == readyBtn) {
			if (!ifReady) {
				readyBtn.setText("取消准备");
				readyBtn.setVisible(false);
				ifReady = true;
				ifReadytrue(position);
				pokerLabel();
				new dealThread().start();
			} else {
				readyBtn.setText("准    备");
				ifReady = false;
				ifReadyfalse(position);

			}
			this.repaint();
			user.getOut().println(position + ":" + ifReady);
			user.getOut().flush();
		}
		if (e.getSource() == roomSetVisible) {
			roomface.setVisible(true);
			roomSetVisible.setVisible(false);
		}
		if (e.getSource() == knocklordBtn) {
			outPoker();
			knocklordBtn.setVisible(false);
			nolordBtn.setVisible(false);
		}
		if (e.getSource() == nolordBtn) {
			outPoker();
			knocklordBtn.setVisible(false);
			nolordBtn.setVisible(false);
		}
	}

	public void outPoker() {
		outpokerBtn = new JButton("出  牌");
		outpokerBtn.setBounds(375, 435, 90, 30);
		outpokerBtn.setFont(font17);
		container.add(outpokerBtn);

		nopokerBtn = new JButton("不  出");
		nopokerBtn.setBounds(535, 435, 90, 30);
		nopokerBtn.setFont(font17);
		container.add(nopokerBtn);

		outpokerBtn.addActionListener(this);
		nopokerBtn.addActionListener(this);
		this.repaint();
	}

	public void knockLord() {
		knocklordBtn = new JButton("抢地主");
		knocklordBtn.setBounds(375, 435, 90, 30);
		knocklordBtn.setFont(font17);
		container.add(knocklordBtn);

		nolordBtn = new JButton("不  抢");
		nolordBtn.setBounds(535, 435, 90, 30);
		nolordBtn.setFont(font17);
		container.add(nolordBtn);

		knocklordBtn.addActionListener(this);
		nolordBtn.addActionListener(this);
		this.repaint();

	}

	public void readyRightLabel() {
		readyRight = new JLabel("准备");

		readyRight.setBounds(770, 200, 56, 30);
		readyRight.setFont(font26);
		container.add(readyRight);
	}

	public void pokerLabel() {

		int count = 0;
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2)) {
					break;
				} else {
					pokerlab[count] = new PokerLabel(i + "-" + j, false, false);
					pokerlab[count].setLocation(450, 100);
					container.add(pokerlab[count]);
					count++;
				}
			}
		}
		for (int i = 0; i < pokerlab.length; i++) {
			int index = (int) (Math.random() * 54);
			int index1 = pokerlab.length - i - 1;

			PokerLabel temp = pokerlab[index];
			pokerlab[index] = pokerlab[index1];
			pokerlab[index1] = temp;
		}

		for (int i = 0; i < 3; i++) {
			playerList[i] = new ArrayList<PokerLabel>();
		}

		lordList = new ArrayList<PokerLabel>();

	}



	public class dealThread extends Thread implements Constant {

		@Override
		public synchronized void run() {

			for (int i = 0; i < 54; i++) {
				container.add(pokerlab[i]);
				container.setComponentZOrder(pokerlab[i], 0);
			}

			int temp;

			switch (position) {
				case 1:
					temp = heightLeft;
					heightLeft = myheight;
					myheight = temp;

					temp = widthLeft;
					widthLeft = mywidth;
					mywidth = temp;

					myheightSpace = leftheightSpace;
					leftheightSpace = 0;

					leftwidthSpace = mywidthSpace;
					mywidthSpace = 0;

					break;
				case 0:
					temp = heightRight;
					heightRight = myheight;
					myheight = temp;

					temp = widthRight;
					widthRight = mywidth;
					mywidth = temp;

					myheightSpace = RightheightSpace;
					RightheightSpace = 0;

					RightwidthSpace = mywidthSpace;
					mywidthSpace = 0;

					break;
				case 2:
					break;

			}

			for (int i = 0; i < 54; i++) {

				if (i >= 51)//地主牌
				{
					pokerlab[i].setLocation(lordwidth, 10);
					lordList.add(pokerlab[i]);
					lordwidth += 130;
					continue;
				}

				switch (i % 3) {
					case 0:
						Common.move(pokerlab[i], pokerlab[i].getLocation(), new Point(widthRight, heightRight));
						playerList[0].add(pokerlab[i]);
						heightRight += RightheightSpace;
						widthRight += RightwidthSpace;
						if (position == 0) {
							pokerlab[i].setCanClick(true);
							pokerlab[i].ifUp(true);
						}
						break;
					case 1:
						Common.move(pokerlab[i], pokerlab[i].getLocation(), new Point(widthLeft, heightLeft));
						playerList[1].add(pokerlab[i]);
						heightLeft += leftheightSpace;
						widthLeft += leftwidthSpace;
						if (position == 1) {
							pokerlab[i].setCanClick(true);
							pokerlab[i].ifUp(true);
						}
						break;
					case 2:
						Common.move(pokerlab[i], pokerlab[i].getLocation(), new Point(mywidth, myheight));
						playerList[2].add(pokerlab[i]);
						mywidth += mywidthSpace;
						myheight += myheightSpace;
						if (position == 2) {
							pokerlab[i].setCanClick(true);
							pokerlab[i].ifUp(true);
						}
						break;
				}
				container.setComponentZOrder(pokerlab[i], 0);
				GameFace.this.repaint();
			}
//			for (int i = 0; i < 3; i++) {
//				Common.order(playerList[i]);
//				Common.rePosition(GameFace.this, playerList[i], i);

//				GameFace.this.repaint();
//			}
//			knockLord();
//			time();

//			}
			//GameFace.this.repaint();

		}
	}

	public void ifReadytrue(int position) {

		if (0 == position) {
			this.ifReady1 = true;
			ifDealPoker1 = true;
		}
		if (1 == position) {
			this.ifReady2 = true;
			ifDealPoker2 = true;
		}
		if (2 == position) {
			this.ifReady3 = true;
			ifDealPoker3 = true;
		}

	}

	public void ifReadyfalse(int position) {

		if (0 == position) {
			this.ifReady1 = false;
			this.ifDealPoker1 = false;
		}
		if (1 == position) {
			this.ifReady2 = false;
			this.ifDealPoker2 = false;
		}
		if (2 == position) {
			this.ifReady3 = false;
			this.ifDealPoker3 = false;
		}

	}

	public void sendIfReady(int position) {
		if (user.getOut() != null) {
			if (0 == position) {
				user.getOut().println(ifReady1);
			}
			if (1 == position) {
				user.getOut().println(ifReady2);
			}
			if (2 == position) {
				user.getOut().println(ifReady3);
			}
			user.getOut().flush();
		}
	}

	class readyBtnLis extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (!ifReady) {
				readyBtn.setText("取消准备");
				ifReady = true;
				ifReadytrue(position);

			} else {
				readyBtn.setText("准    备");
				ifReady = false;
				ifReadyfalse(position);

			}

			user.getOut().println(position + ":" + ifReady);
			user.getOut().flush();
		}

	}

	class roomSetVisibleBtnLis extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

			roomface.setVisible(true);
			roomSetVisible.setVisible(false);

		}

	}

	class playerWaitThread extends Thread {

		@Override
		public synchronized void run() {
			while (!interrupted()) {
				 if (0 == position) {
					if (ifReady2) {
						readyRightLabel();
						ifReady2 = false;
						System.out.println(2 + "号右边显示准备");
					}
					if (ifReady3) {
						readyLeftLabel();
						ifReady3 = false;
						System.out.println(3 + "号左边显示准备");
					}
				} else if (1 == position) {
					if (ifReady1) {
						readyLeftLabel();
						ifReady1 = false;
						System.out.println(1 + "号左边显示准备");
					}
					if (ifReady3) {
						readyRightLabel();
						ifReady3 = false;
						System.out.println(3 + "号右边显示准备");
					}
				} else if (2 == position) {
					if (ifReady1) {
						readyRightLabel();
						ifReady1 = false;
						System.out.println(1 + "号右边显示准备");
					}
					if (ifReady2) {
						readyLeftLabel();
						ifReady2 = false;
						System.out.println(2 + "号左边显示准备");
					}
				}else if (ifDealPoker1 && ifDealPoker2 && ifDealPoker3) {
					pokerLabel();
					readyLeft.setVisible(false);
					readyRight.setVisible(false);
					waitThread.interrupt();
					new dealThread().start();
					readyBtn.setVisible(false);
				} 

				GameFace.this.repaint();

			}
		}

	}

	public static void judgePosition() {

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public void update(Graphics g) {
		if (buffereimg == null) {
			buffereimg = this.createImage(this.getWidth(), this.getHeight());
		}
		buffereG = buffereimg.getGraphics();
		buffereG.drawImage(this.getIconImage(), this.getWidth(), this.getHeight(), container);

		super.paint(g);
		g.drawImage(buffereimg, this.getWidth(), this.getHeight(), container);

	}

	@Override
	public void paint(Graphics g) {
		update(g);
	}

	public boolean isIfReady1() {
		return ifReady1;
	}

	public void setIfReady1(boolean ifReady1) {
		this.ifReady1 = ifReady1;
	}

	public boolean isIfReady2() {
		return ifReady2;
	}

	public void setIfReady2(boolean ifReady2) {
		this.ifReady2 = ifReady2;
	}

	public boolean isIfReady3() {
		return ifReady3;
	}

	public void setIfReady3(boolean ifReady3) {
		this.ifReady3 = ifReady3;
	}

}
