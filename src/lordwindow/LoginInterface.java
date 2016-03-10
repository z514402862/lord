/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author zzl
 */
public class LoginInterface extends MainFrm implements Constant, ActionListener {

	private JPanel mainPanel;

	private JLabel userLabel;
	private JTextField userText;

	private JLabel passWLabel;
	private JPasswordField passWText;

	private JButton loginBtn;
	private JButton registerBtn;

	private boolean regBtn;

	public LoginInterface() {
		mainFrm();
		mainPanel();
		userName();
		passWord();
		ThreeBtn();

	}

	public void mainFrm() {
		this.setSize(loginMainPanel.getIconWidth(), loginMainPanel.getIconHeight());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		this.setLocationRelativeTo(this);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int exi = JOptionPane.showConfirmDialog(null, "是否退出游戏？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (exi == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void mainPanel() {
		mainPanel = new MainPanel(loginMainPanel);
		mainPanel.setLayout(null);
		this.getContentPane().add(mainPanel);
	}

	public void userName() {
		userLabel = new JLabel("账  号");
		userLabel.setBounds(520, 115, loginLabelW, loginLabelH);
		userLabel.setForeground(Color.WHITE);
		userLabel.setFont(font26);
		mainPanel.add(userLabel);

		userText = new JTextField();
		userText.setBounds(620, 120, loginTextW, loginTextH);
		mainPanel.add(userText);

	}

	public void passWord() {
		passWLabel = new JLabel("密  码");
		passWLabel.setBounds(520, 195, loginLabelW, loginLabelH);
		passWLabel.setForeground(Color.WHITE);
		passWLabel.setFont(font26);
		mainPanel.add(passWLabel);

		passWText = new JPasswordField();
		passWText.setBounds(620, 200, loginTextW, loginTextH);
		mainPanel.add(passWText);
	}

	public void ThreeBtn() {
		loginBtn = new JButton("登    录");
		loginBtn.setBounds(590, 280, 200, 40);
		loginBtn.addActionListener(this);
		loginBtn.setFont(font26);
		mainPanel.add(loginBtn);

		registerBtn = new JButton("注    册");
		registerBtn.setBounds(590, 340, 200, 40);
		registerBtn.addActionListener(this);
		registerBtn.setFont(font26);
		mainPanel.add(registerBtn);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerBtn) {
			new RegisterInterface(LoginInterface.this).setVisible(true);
			LoginInterface.this.setVisible(false);
		}
		if (e.getSource() == loginBtn) {
			boolean bool = false;

			String usertxt = this.userText.getText();
			String passtxt = new String(this.passWText.getPassword());

			if (usertxt == null || "".equals(usertxt)) {
				JOptionPane.showMessageDialog((JButton) e.getSource(), "账号不能为空！");
			} else if (passtxt == null || "".equals(passtxt)) {
				JOptionPane.showMessageDialog((JButton) e.getSource(), "密码不能为空！");
			} else {

				if (user.getUserName() == null) {
					user.setUserName(usertxt);
				}
				if (user.getNickname() == null) {
					user.setNickname(usertxt);
				}
				if (user.getIp() == null) {

					try {
						user.setIp(InetAddress.getLocalHost().getHostAddress());
					} catch (UnknownHostException ex) {
						ex.printStackTrace();
					}

				}

				try {
					Socket socket = new Socket(InetAddress.getLocalHost(), 4000);

					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

					user.setOut(out);
					user.setIn(in);
					user.setSocket(socket);

					out.println(user.getUserName());
					out.flush();

//					String message = in.readLine();
//					
//					System.out.println(message);
					new RoomFace().setVisible(true);
				} catch (Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog((JButton) e.getSource(), "服务器还没有开启，请稍后。。。");
				}
			}
		}
	}
}
