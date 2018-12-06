package com.model.tank;

import com.model.entities.Direction;
import com.model.entities.Point;
import com.model.factory.TankFactory;

public class LittleTank extends Tank {

	public LittleTank(Point p) {
		super(p);
		setSpeed(2);
		setLife(5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		super.shoot();
	}

	//这是小兵独有的方法
	public void autoAttack() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(getLife()>0) {
					attackMyTank();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	private void attackMyTank() {
		if(TankFactory.mTank==null) {
			System.out.println("你被干死了");
			return ;
		}		
		//纵坐标在一条线上
		if(Math.abs(getPoint().getX()-TankFactory.mTank.getPoint().getX())<width/2) {
			
			if(getPoint().getY()<TankFactory.mTank.getPoint().getY()) {
				//小兵在我的南边
				setDirection(Direction.SOUTH);
			}else {
				//小兵在我的北边
				setDirection(Direction.NORTH);
			}
			shoot();
		}
		//横坐标在一条线上
		else if(Math.abs(getPoint().getY()-TankFactory.mTank.getPoint().getY())<height/2) {
			if(getPoint().getX()<TankFactory.mTank.getPoint().getX()) {
				//小兵在我的左边
				setDirection(Direction.EAST);
			}else {
				//小兵在我的右边
				setDirection(Direction.WEST);
			}
			shoot();
		}
		
		//不在一条线上，找最近的距离接近我
		else {
			int lengthX=Math.abs(getPoint().getX()-TankFactory.mTank.getPoint().getX());
		    int lengthY=Math.abs(getPoint().getY()-TankFactory.mTank.getPoint().getY());
		    if(lengthX<lengthY) {
		    	//横向接近
		    	if(getPoint().getX()>TankFactory.mTank.getPoint().getX()) {
		    		//在左边
		    		setDirection(Direction.WEST);
		    		move();
		    	}else {
		    		//在右边
		    		setDirection(Direction.EAST);
		    		move();
		    	}
		    }else {
		    	//纵向接近
		    	if(getPoint().getY()>TankFactory.mTank.getPoint().getY()) {
		    		//在下边
		    		setDirection(Direction.NORTH);
		    		move();
		    	}else {
		    		//在上边
		    		setDirection(Direction.SOUTH);
		    		move();
		    	}
		    }
		}
		
	}
}
