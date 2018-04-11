package com.plantgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.frame.ImageUtil;

public class Bullet {
	private final BufferedImage BULLET[] = new BufferedImage[2];
	private final int WIDTH;
	private int x_std0;
	private int y_std;
	private int x_std1;
	
	public Bullet(int x, int y, int w, int h) throws FileNotFoundException, IOException {
		BULLET[0] = BULLET[1] = ImageUtil.creatImage("E:\\JAVA-PROJECTS\\PlantGame\\src\\Images\\×Óµ¯.png");
		this.WIDTH = BULLET[0].getWidth();
		this.x_std0 = x + w / 10;
		this.y_std = y + h / 6;
		this.x_std1 = x + w * 9 / 10 - WIDTH;
	}
	
	public void draw(Graphics g) {
		this.setY_std(y_std - PGConstants.BULLET_SPEED);
		g.drawImage(BULLET[0], x_std0, y_std, null);
		g.drawImage(BULLET[1], x_std1, y_std, null);
	}

	public void setY_std(int y_std) {
		this.y_std = y_std;
	}
}
