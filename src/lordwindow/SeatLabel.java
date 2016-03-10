/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author zzl
 */
public class SeatLabel extends JLabel implements MouseListener, Constant {

	private RoomFace roomFace;
	private boolean ifSit = true;
	private ImageIcon headProtiait;

	private int position;

	public SeatLabel(RoomFace roomFace, ImageIcon icon) {
		this.roomFace = roomFace;
		this.setIcon(icon);

		//this.headProtiait = headProtiait;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		this.setBorder(new LineBorder(Color.WHITE));
		if (ifSit) {
			this.setIcon(headPortiait2);
			ifSit = false;
			user.setPosition(Integer.toString(position));
			GameFace gameFace = new GameFace(roomFace);
			gameFace.setVisible(true);
			gameFace.setPosition(position);
			roomFace.setIfSit(position);
			roomFace.setGameface(gameFace);
			roomFace.getRecThread().setGameFace(gameFace);
			if (0 == position) {
				user.getOut().println(position+":"+gameFace.isIfReady1());
			}
			if (1 == position) {
				user.getOut().println(position+":"+gameFace.isIfReady2());
			}
			if (2 == position) {
				user.getOut().println(position+":"+gameFace.isIfReady3());
			}
			user.getOut().flush();
		} else {
			return;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.setBorder(new LineBorder(Color.BLACK));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isIfSit() {
		return ifSit;
	}

	public void setIfSit(boolean ifSit) {
		this.ifSit = ifSit;
	}

}
