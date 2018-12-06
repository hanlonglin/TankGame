package com.test;

import com.model.bullet.Bullet;
import com.model.bullet.FirstBullet;
import com.model.bullet.SecondBullet;
import com.model.entities.Direction;
import com.model.entities.Point;
import com.model.factory.TankFactory;
import com.model.tank.LittleTank;
import com.model.tank.MyTank;
import com.model.tank.Tank;

public class Test {

	public static void main(String[] args) {
		/*
		Bullet bullet=new FirstBullet(new Point(20,30),5);
		bullet.setSpeed(10);
		bullet.move(Direction.EAST);
		 */		
		Tank tank1=TankFactory.createTank("my");
		tank1.setPoint(new Point(0,0));
		
		Tank tank2=TankFactory.createTank("boss");
		tank2.setPoint(new Point(10,50));
		
		Tank tank3=TankFactory.createTank("little");
		tank3.setPoint(new Point(10,80));
		
		tank1.setDirection(Direction.EAST);
		tank1.setSpeed(5);
		tank1.move();
		tank1.move();
		tank1.setDirection(Direction.SOUTH);
		tank1.setBullet(new SecondBullet());
		tank1.shoot();
		tank1.shoot();
		tank1.shoot();
		tank1.shoot();
		tank1.shoot();
		tank1.shoot();
		tank1.shoot();
	}
}
