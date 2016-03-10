/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.io.IOException;

/**
 *
 * @author zzl
 */
public class ReceiveServerMessageThread extends Thread implements Constant {

	private String strMessage;
	private GameFace gameFace;

	@Override
	public void run() {
		while (!interrupted()) {
			try {
				System.out.println("等待中。。。。");
				strMessage = user.getIn().readLine();
				System.out.println(strMessage);
				disposeMessage();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void disposeMessage() {
		String[] messageAry = strMessage.split(":");
		
		if(strMessage.startsWith("Server:")){
			
		}
		else if (!"-100".equals(messageAry[messageAry.length - 1]) && !"-100".equals(messageAry[messageAry.length - 2])) {
			if (Boolean.valueOf(messageAry[messageAry.length - 1])) {
				System.out.println(messageAry[messageAry.length - 2]);
				if (Integer.parseInt(messageAry[messageAry.length - 2]) != gameFace.getPosition()) {
					gameFace.ifReadytrue(Integer.parseInt(messageAry[messageAry.length - 2]));
				}

			}
		}
	}

	public GameFace getGameFace() {
		return gameFace;
	}

	public void setGameFace(GameFace gameFace) {
		this.gameFace = gameFace;
	}

}
