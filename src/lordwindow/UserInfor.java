/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author zzl
 */
public class UserInfor {
	
	private String userName;
	private String nickname;
	private ImageIcon headPortiait;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String ip;
	private String position;
	private ReceiveServerMessageThread recThread;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ImageIcon getHeadProtiait() {
		return headPortiait;
	}

	public void setHeadProtiait(ImageIcon headPortiait) {
		this.headPortiait = headPortiait;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return this.userName+":"+this.nickname+":"+this.ip;
	}

	public ReceiveServerMessageThread getRecThread() {
		return recThread;
	}

	public void setRecThread(ReceiveServerMessageThread recThread) {
		this.recThread = recThread;
	}
	
}
