package com.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.bullet.FirstBullet;
import com.model.entities.Direction;
import com.model.entities.Point;
import com.model.entities.Window;
import com.model.factory.TankFactory;
import com.model.tank.Tank;

public class MainGame extends JFrame {

	MainPanel jpanel;

	public MainGame() {

		initWindow();
		
		initSet();

	}

	private void initWindow() {
		setSize(600, 600);
		setTitle("Tank”Œœ∑");
		setVisible(true);

		jpanel = new MainPanel();
		setContentPane(jpanel);

		this.addKeyListener(jpanel);
		this.show();
	}
	
	private void initSet() {
		Window.WIDTH=this.getHeight();
		Window.HEIGHT=this.getWidth();
	}

}
