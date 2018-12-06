package com.model.bullet;

import com.model.entities.Point;
import com.model.entities.Window;
import com.model.factory.TankFactory;
import com.model.interfaces.Moveable;
import com.model.tank.Tank;

public abstract class Bullet implements Moveable {
	// ����
	Point point = new Point(0, 0);

	// ɱ����
	protected int life = 1;

	// �ٶ�
	protected int speed = 1;

	// ����
	int direction = -1;

	// �Ƿ����ƶ�
	boolean isRunning = false;

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}

	// �ƶ�
	public void move() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				setRunning(true);
				switch (direction) {
				case com.model.entities.Direction.EAST:
					while (getPoint().getX() < Window.WIDTH) {
						Point p = new Point(getPoint().getX() + 1, getPoint().getY());
						setPoint(p);
						System.out.println("�ӵ����꣺" + getPoint().toString());
						try {
							Thread.sleep((long) (500 / speed));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (checkAttack())
							break;
					}
					break;
				case com.model.entities.Direction.WEST:
					while (getPoint().getX() > 0) {
						Point p = new Point(getPoint().getX() - 1, getPoint().getY());
						setPoint(p);
						System.out.println("�ӵ����꣺" + getPoint().toString());
						try {
							Thread.sleep((long) (500 / speed));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (checkAttack())
							break;
					}
					break;
				case com.model.entities.Direction.NORTH:
					while (getPoint().getY() > 0) {
						Point p = new Point(getPoint().getX(), getPoint().getY() - 1);
						setPoint(p);
						System.out.println("�ӵ����꣺" + getPoint().toString());
						try {
							Thread.sleep((long) (500 / speed));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (checkAttack())
							break;
					}
					break;
				case com.model.entities.Direction.SOUTH:
					while (getPoint().getY() < Window.HEIGHT) {
						Point p = new Point(getPoint().getX(), getPoint().getY() + 1);
						setPoint(p);
						System.out.println("�ӵ����꣺" + getPoint().toString());
						try {
							Thread.sleep((long) (500 / speed));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (checkAttack())
							break;
					}
					break;
				}
				setRunning(false);
			}
		}).start();
	}

	/**
	 * ����ӵ���û�д����κ���
	 */
	private boolean checkAttack() {
		synchronized (this) {
			for (Tank tank : TankFactory.tankList) {
				if (getPoint().getX() > (tank.getPoint().getX() - tank.getWidth() / 2)
						&& getPoint().getX() < (tank.getPoint().getX() + tank.getWidth() / 2)
						&& getPoint().getY() > (tank.getPoint().getY() - tank.getHeight() / 2)
						&& getPoint().getY() < (tank.getPoint().getY() + tank.getHeight() / 2)) {
					//�Լ������ӵ�������Լ�
					if(tank.getBullet()==this)
						return false;
					
					tank.setLife(tank.getLife() - getLife());
					if (tank.getLife() <= 0)
						TankFactory.removeTank(tank);
					return true;
				}
			}
		}
		return false;
	}
}
