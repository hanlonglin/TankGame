package com.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import com.model.bullet.Bullet;
import com.model.bullet.FirstBullet;
import com.model.bullet.SecondBullet;
import com.model.entities.Direction;
import com.model.entities.Point;
import com.model.entities.Window;
import com.model.factory.TankFactory;
import com.model.tank.LittleTank;
import com.model.tank.Tank;

public class MainPanel extends JPanel implements KeyListener {
	int bulletR = 6;

	public MainPanel() {
		init();
		addKeyListener(this);

		// 开启定时器，不停重绘
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateUI();
				// System.out.println("正在执行定时器......");
			}

		}, 0, 100);

		// 每隔几秒中生出一个Tank
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					if (TankFactory.tankList.size() < 3) {
						Tank tank = TankFactory.createTank("little");
						tank.setDirection(Direction.WEST);
						tank.setBullet(new FirstBullet());
						tank.setPoint(
								new Point((int) (Math.random() * Window.WIDTH), (int) (Math.random() * Window.HEIGHT)));
						System.out.println("生产：" + tank.getPoint().toString());
						if(tank instanceof LittleTank) {
							((LittleTank)tank).autoAttack();
						}
					}

					try {
						Thread.sleep((long) (Math.random() * 5000 + 3000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void init() {
		TankFactory.mTank = TankFactory.createTank("my");
		TankFactory.mTank.setDirection(Direction.EAST);
		TankFactory.mTank.setPoint(new Point(50, 50));
		TankFactory.mTank.setBullet(new SecondBullet());

		/*
		 * Tank tank1 = TankFactory.createTank("little");
		 * tank1.setDirection(Direction.SOUTH); tank1.setPoint(new Point(50, 100));
		 */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("键盘事件，code:" + e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			System.out.println("向左");
			TankFactory.mTank.setDirection(Direction.WEST);
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("向右");
			TankFactory.mTank.setDirection(Direction.EAST);
			break;
		case KeyEvent.VK_UP:
			System.out.println("向上");
			TankFactory.mTank.setDirection(Direction.NORTH);
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("向下");
			TankFactory.mTank.setDirection(Direction.SOUTH);
			break;
		default:
			break;
		}
		TankFactory.mTank.move();
		// 重绘
		// updateUI();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			// 发射子弹
			System.out.println("发射子弹..");
			TankFactory.mTank.shoot();
			break;

		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		// System.out.println("绘制..");

		doDraw(g);
		
		if(TankFactory.mTank.getLife()<=0)
			System.out.println("你死了 游戏结束");
	}

	private void doDraw(Graphics g) {
		// System.out.println("tank数量："+TankFactory.tankList.size());
		for (Tank tank : TankFactory.tankList) {
			drawTank(g, tank);
		}

	}

	private void drawTank(Graphics g, Tank tank) {
		Point p = tank.getPoint();
		int w = tank.getWidth();
		int h = tank.getHeight();
		g.setColor(Color.red);
		g.fillRect(p.getX() - w / 2, p.getY() - h / 2, w, h);
		switch (tank.getDirection()) {
		case Direction.EAST:
			g.fillRect(p.getX() + w / 2, p.getY() - h / 2 / 2, w / 2, h / 2);
			break;
		case Direction.WEST:
			g.fillRect(p.getX() - w / 2 - tank.getWidth() / 2, p.getY() - h / 2 / 2, w / 2, h / 2);
			break;
		case Direction.NORTH:
			g.fillRect(p.getX() - w / 2 / 2, p.getY() - h / 2 - h / 2, w / 2, h / 2);
			break;
		case Direction.SOUTH:
			g.fillRect(p.getX() - w / 2 / 2, p.getY() + h / 2, w / 2, h / 2);
			break;
		}
		// 画子弹
		Bullet bullet = tank.getBullet();
		if (bullet != null && bullet.isRunning()) {
			g.setColor(Color.BLUE);
			g.fillOval(bullet.getPoint().getX(), bullet.getPoint().getY(), bulletR, bulletR);
		}

	}
}
