/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordwindow;

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Common implements Constant{
	public static void move(PokerLabel pokerLabel,Point from,Point to){
		if(to.x!=from.x){
			double k=(1.0)*(to.y-from.y)/(to.x-from.x);
			double b=to.y-to.x*k;
			int flag=0;//判断向左还是向右移动步幅
			if(from.x<to.x)
				flag=20;
			else {
				flag=-20;
			}
			for(int i=from.x;Math.abs(i-to.x)>20;i+=flag)
			{
				double y=k*i+b;//这里主要用的数学中的线性函数
			
				pokerLabel.setLocation(i,(int)y);
				try {
					Thread.sleep(5); //延迟，可自己设置
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//位置校准
		pokerLabel.setLocation(to);
	}
	public static void order(List<PokerLabel> list){
		Collections.sort(list,new Comparator<PokerLabel>() {
			@Override
			public int compare(PokerLabel o1, PokerLabel o2) {
				// TODO Auto-generated method stub
				int a1=Integer.parseInt(o1.getPokerName().substring(0, 1));//花色
				int a2=Integer.parseInt(o2.getPokerName().substring(0,1));
				int b1=Integer.parseInt(o1.getPokerName().substring(2,o1.getPokerName().length()));//数值
				int b2=Integer.parseInt(o2.getPokerName().substring(2,o2.getPokerName().length()));
				int flag=0;
				//如果是王的话
				if(a1==5) b1+=100;
				if(a1==5&&b1==1) b1+=50;
				if(a2==5) b2+=100;
				if(a2==5&&b2==1) b2+=50;
				//如果是A或者2
				if(b1==12) b1+=20;
				if(b2==12) b2+=20;
				if(b1==13) b1+=30;
				if(b2==13) b2+=30;
				flag=b2-b1;
				if(flag==0)
					return a2-a1;
				else {
					return flag;
				}
			}
		});
	}
	public static void rePosition(GameFace m,List<PokerLabel> list,int flag){
		Point p=new Point();
		if(flag==0)
		{
			p.x=50;
			p.y=(450/2)-(list.size()+1)*15/2;
		}
		if(flag==1)
		{
			p.x=700;
			p.y=(450/2)-(list.size()+1)*15/2;
		}
		if(flag==2)
		{
			//我的排序 _y=450 width=830
			p.x=((int) (computerWidth * 0.75) - 505) / 2;
			p.y=450;
			
		}
		int len=list.size();
		for(int i=0;i<len;i++){
			PokerLabel card=list.get(i);
			Common.move(card, card.getLocation(), p);
			m.getContainer().setComponentZOrder(card, 0);
			if(flag==1)p.x+=21;
			else p.y+=15;
			
		}
	}
}
