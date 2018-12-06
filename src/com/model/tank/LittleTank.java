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

	//����С�����еķ���
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
			System.out.println("�㱻������");
			return ;
		}		
		//��������һ������
		if(Math.abs(getPoint().getX()-TankFactory.mTank.getPoint().getX())<width/2) {
			
			if(getPoint().getY()<TankFactory.mTank.getPoint().getY()) {
				//С�����ҵ��ϱ�
				setDirection(Direction.SOUTH);
			}else {
				//С�����ҵı���
				setDirection(Direction.NORTH);
			}
			shoot();
		}
		//��������һ������
		else if(Math.abs(getPoint().getY()-TankFactory.mTank.getPoint().getY())<height/2) {
			if(getPoint().getX()<TankFactory.mTank.getPoint().getX()) {
				//С�����ҵ����
				setDirection(Direction.EAST);
			}else {
				//С�����ҵ��ұ�
				setDirection(Direction.WEST);
			}
			shoot();
		}
		
		//����һ�����ϣ�������ľ���ӽ���
		else {
			int lengthX=Math.abs(getPoint().getX()-TankFactory.mTank.getPoint().getX());
		    int lengthY=Math.abs(getPoint().getY()-TankFactory.mTank.getPoint().getY());
		    if(lengthX<lengthY) {
		    	//����ӽ�
		    	if(getPoint().getX()>TankFactory.mTank.getPoint().getX()) {
		    		//�����
		    		setDirection(Direction.WEST);
		    		move();
		    	}else {
		    		//���ұ�
		    		setDirection(Direction.EAST);
		    		move();
		    	}
		    }else {
		    	//����ӽ�
		    	if(getPoint().getY()>TankFactory.mTank.getPoint().getY()) {
		    		//���±�
		    		setDirection(Direction.NORTH);
		    		move();
		    	}else {
		    		//���ϱ�
		    		setDirection(Direction.SOUTH);
		    		move();
		    	}
		    }
		}
		
	}
}
