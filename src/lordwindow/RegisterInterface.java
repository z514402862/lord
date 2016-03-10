/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.*;
import java.net.InetAddress;


/**
 *
 * @author zzl
 */
public class RegisterInterface extends MainFrm implements Constant {

	private LoginInterface loginFace;
	
    private JLabel mainLabel;

    private JLabel userLabel;
    private JTextField userText;

    private JLabel nickLable;
    private JTextField nickText;

    private JLabel passWLable;
    private JPasswordField passWText;

    private JLabel againPLable;
    private JPasswordField againPText;

    private JButton registerBtn;
    private JButton cancelBtn;

    public RegisterInterface(LoginInterface loginFace) {
		this.loginFace = loginFace;
		
        mainFrm();
        mainPanel();
        userName();
        nickName();
        passWord();
        againPassW();
        twoBtn();

        cancelBtn.addMouseListener(new cancelBtnLis());
        registerBtn.addMouseListener(new registBtnLis());

    }

    public void mainFrm() {
        this.setBounds(computerWidth / 2 + 80, computerHeigth / 4, 355, 363);
        this.setFont(font17);
        this.setTitle("  账号注册");
        this.setLocationRelativeTo(this);
        this.setResizable(false);
    }

    public void mainPanel() {
        mainLabel = new JLabel();
        mainLabel.setBounds(0, 0, 355, 363);
        mainLabel.setLayout(null);
        mainLabel.setBackground(new Color(100, 149, 237));
        this.getContentPane().add(mainLabel);
    }

    public void userName() {
        userLabel = new JLabel("*用户名");
        userLabel.setBounds(40, 50, registerLabelW, registerLabelH);
        userLabel.setFont(font17);
        mainLabel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(110, 55, registertextW, registertextH);
        mainLabel.add(userText);
    }

    public void nickName() {
        nickLable = new JLabel();
        nickLable.setBounds(55, 100, registerLabelW, registerLabelH);
        nickLable.setFont(font17);
        nickLable.setText("*昵称");
        mainLabel.add(nickLable);

        nickText = new JTextField();
        nickText.setBounds(110, 105, registertextW, registertextH);
        mainLabel.add(nickText);
    }

    public void passWord() {
        passWLable = new JLabel("*密码");
        passWLable.setBounds(55, 150, registerLabelW, registerLabelH);
        passWLable.setFont(font17);
        mainLabel.add(passWLable);

        passWText = new JPasswordField();
        passWText.setBounds(110, 155, registertextW, registertextH);
        mainLabel.add(passWText);
    }

    public void againPassW() {
        againPLable = new JLabel("*确认密码");
        againPLable.setBounds(20, 200, registerLabelW, registerLabelH);
        againPLable.setFont(font17);
        mainLabel.add(againPLable);

        againPText = new JPasswordField();
        againPText.setBounds(110, 205, registertextW, registertextH);
        mainLabel.add(againPText);
    }

    public void twoBtn() {
        registerBtn = new JButton("提  交");
        registerBtn.setBounds(55, 255, 90, 30);
        registerBtn.setFont(font17);
        registerBtn.setBorder(null);
        mainLabel.add(registerBtn);

        cancelBtn = new JButton("取  消");
        cancelBtn.setBounds(210, 255, 90, 30);
        cancelBtn.setFont(font17);
        cancelBtn.setBorder(null);
        mainLabel.add(cancelBtn);
    }

    class cancelBtnLis extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            RegisterInterface.this.setVisible(false);
            loginFace.setVisible(true);
        }

    }

    class registBtnLis extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            String nick = RegisterInterface.this.nickText.getText();
			String name = RegisterInterface.this.userText.getText();

			try {

				InetAddress address = InetAddress.getLocalHost();
				user.setIp(address.getHostAddress());
				user.setUserName(name);
				user.setNickname(nick);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			RegisterInterface.this.setVisible(false);
			loginFace.setVisible(true);
        }

    }

}
