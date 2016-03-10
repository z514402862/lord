/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.net.*;
import java.util.Scanner;

/**
 *
 * @author zzl
 */
public class UDPConnect extends MainFrm{

	private DatagramSocket socket;
	private DatagramPacket rPacket;
	private DatagramPacket sPacket;
	
	private String sendMessage;
	private String recMessage;

	byte[] buf = new byte[2048];

	public UDPConnect() {
		try {
			socket = new DatagramSocket(5000);
			rPacket = new DatagramPacket(buf, buf.length);
			sPacket = new DatagramPacket(buf, buf.length);
		} catch (Exception ex) {
		}
		
		UDPConnect test = new UDPConnect();

		recThread rt = test.new recThread();
		sendThread st = test.new sendThread();
		
		rt.start();
		st.start();
	}

	class recThread extends Thread {

		@Override
		public void run() {

			while (!interrupted()) {

				try {

					socket.receive(rPacket);

					byte[] by = rPacket.getData();
					String str = new String(by, 0, rPacket.getLength());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	class sendThread extends Thread {

		@Override
		public void run() {

			while (!interrupted()) {

				try {
					
					sPacket.setAddress(InetAddress.getByName("172.20.2.142"));
					sPacket.setPort(4000);
					sPacket.setData(sendMessage.getBytes());
					sPacket.setLength(sendMessage.getBytes().length);

					socket.send(sPacket);

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		}
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getRecMessage() {
		return recMessage;
	}

	public void setRecMessage(String recMessage) {
		this.recMessage = recMessage;
	}
	
}
