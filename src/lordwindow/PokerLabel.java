/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class PokerLabel extends JLabel implements MouseListener,MouseMotionListener{

	private String pokerName;
	private int playerNum;
	private boolean canClick;
	private boolean ifClicked = false;
	private boolean up;
	
    public PokerLabel(String pokerName,boolean up,boolean canClick){
		this.pokerName = pokerName;
		this.canClick = canClick;
		ifUp(up);
		this.setSize(105, 150);
		this.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	public void ifUp(boolean up){
		if(up){
			turnUp();
		}else{
			turnDown();
		}
	}
	
	public void turnUp(){
		ImageIcon icon = new ImageIcon("Image\\"+pokerName+".jpg");
		this.setIcon(icon);
	}
	
	public void turnDown(){
		ImageIcon icon = new ImageIcon("Image\\pokerback.jpg");
		this.setIcon(icon);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!ifClicked && canClick){
			this.setLocation(this.getX(),this.getY()-20);
			ifClicked = true;
		}else if(canClick){
			this.setLocation(this.getX(),this.getY()+20);
			ifClicked = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	public String getPokerName(){
		return pokerName;
	}
	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public boolean isCanClick() {
		return canClick;
	}

	public void setCanClick(boolean canClick) {
		this.canClick = canClick;
	}
	
	
}
