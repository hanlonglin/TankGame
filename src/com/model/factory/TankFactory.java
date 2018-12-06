package com.model.factory;

import java.util.ArrayList;

import com.model.entities.Point;
import com.model.tank.BossTank;
import com.model.tank.LittleTank;
import com.model.tank.MyTank;
import com.model.tank.Tank;
import java.util.*;

public class TankFactory {

	//当前我的Tank
	public static Tank mTank;
	//当前游戏中的所有tank
	public static List<Tank> tankList=new ArrayList<>();
	
	public static Tank createTank(String type) {
		Tank tank=null;
		if(type.equals("my")) {			
			tank=new MyTank(new Point(0,0));
		}
		else if(type.equals("little")) {
			tank=new LittleTank(new Point(0,0));
		}
		else if(type.equals("boss")) {
			tank=new BossTank(new Point(0,0));
		}
		if(tank!=null)
			tankList.add(tank);
		return tank;
	}
	
	public static void removeTank(Tank tank) {
		tankList.remove(tank);
	}
}
