package com.plantgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.frame.ImageUtil;

public class Plant {
	private final BufferedImage PLANT;
	private final int WIDTH;
	private final int HEIGHT;
	private int x_std;
	private int y_std;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private boolean fire;
	private boolean alive = true;
	
	public Plant(String imgPath) throws FileNotFoundException, IOException {
		PLANT = ImageUtil.creatImage(imgPath);
		WIDTH = PLANT.getWidth();
		HEIGHT = PLANT.getHeight();
		x_std = PGConstants.WIDTH_MID - WIDTH / 2;
		y_std = PGConstants.HEIGHT_DOWN - HEIGHT;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x_std, y_std, WIDTH, HEIGHT);
	}
	
	public void draw(Graphics g) throws FileNotFoundException, IOException {
		if(x_std < PGConstants.LEFT_RIGHT_BOTTOM)
			x_std = PGConstants.LEFT_RIGHT_BOTTOM;
		if(x_std > PGConstants.WIDTH - PGConstants.LEFT_RIGHT_BOTTOM - WIDTH)
			x_std = PGConstants.WIDTH - PGConstants.LEFT_RIGHT_BOTTOM - WIDTH;
		if(y_std < PGConstants.TOP)
			y_std = PGConstants.TOP;
		if(y_std > PGConstants.HEIGHT_DOWN - HEIGHT)
			y_std = PGConstants.HEIGHT_DOWN - HEIGHT;
		g.drawImage(PLANT, x_std , y_std, null);
		keyMove(g);
		for(Bullet b : bullets)
			b.draw(g);
	}
	
	public void keyMove(Graphics g) throws FileNotFoundException, IOException{
		if(left)
			x_std -= PGConstants.PLANE_SPEED;
		if(right)
			x_std += PGConstants.PLANE_SPEED;
		if(up)
			y_std -= PGConstants.PLANE_SPEED;
		if(down)
			y_std += PGConstants.PLANE_SPEED;
		if(fire)
			bullets.add(new Bullet(x_std, y_std, WIDTH, HEIGHT));
	}

	public void pressDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 37:
				left = true;
				break;
			case 38:
				up = true;
				break;
			case 39:
				right = true;
				break;
			case 40:
				down = true;
				break;
			case KeyEvent.VK_SPACE:
				fire = true;
				break;
			default:
				break;
		}
	}
	
	public void releaseDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 37:
				left = false;
				break;
			case 38:
				up = false;
				break;
			case 39:
				right = false;
				break;
			case 40:
				down = false;
				break;
			case KeyEvent.VK_SPACE:
				fire = false;
				break;
			default:
				break;
		}
	}
	
}
