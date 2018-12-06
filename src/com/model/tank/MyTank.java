package com.model.tank;

import com.model.entities.Point;

public class MyTank extends Tank{
	
	public MyTank(Point p) {
		super(p);
		setSpeed(5);
		setLife(10);
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
}
