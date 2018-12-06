package com.model.tank;

import com.model.bullet.Bullet;
import com.model.bullet.FirstBullet;
import com.model.entities.Direction;
import com.model.entities.Point;
import com.model.entities.Window;
import com.model.interfaces.Moveable;

public abstract class Tank implements Moveable {

	int height = 20;
	int width = 20;
	// 坐标
	Point point;

	// 生命值
	int life = 10;

	// 当前方向
	int direction = -1;

	// 速度
	int speed = 1;

	// 子弹
	Bullet bullet;

	// settter and getter
	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	// constuctor
	public Tank(Point p) {
		this.point = p;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public Bullet getBullet() {
		return bullet;
	}

	// method

	// 移动
	public void move() {
		switch (direction) {
		case Direction.EAST:
			if (point.getX() < Window.WIDTH) {
				point.setX(point.getX() + speed);
			}
			break;
		case Direction.WEST:
			if (point.getX() > 0) {
				point.setX(point.getX() - speed);
			}
			break;
		case Direction.NORTH:
			if (point.getY() > 0) {
				point.setY(point.getY() - speed);
			}
			break;
		case Direction.SOUTH:
			if (point.getY() < Window.HEIGHT) {
				point.setY(point.getY() + speed);
			}
			break;
		}
		System.out.println("tank point:" + point.toString());
	}

	// 发射
	public void shoot() {
		if (bullet == null) {
			System.out.println("没有子弹了");
			return;
		}
		if (bullet.isRunning()) {
			System.out.println("等待子弹结束...");
			return ;
		}
		Point p = null;
		switch (direction) {
		case Direction.EAST:
			p = new Point(point.getX() + width / 2, point.getY());
			break;
		case Direction.WEST:
			p = new Point(point.getX() - width / 2, point.getY());
			break;
		case Direction.NORTH:
			p = new Point(point.getX(), point.getY() - height / 2);
			break;
		case Direction.SOUTH:
			p = new Point(point.getX(), point.getY() + height / 2);
			break;
		default:
			break;
		}

		bullet.setPoint(p);
		bullet.setDirection(direction);
		bullet.move();
	}
}
