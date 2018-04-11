package com.plantgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.frame.ImageUtil;

public class Obstacles {
	private final BufferedImage[] OBS;
	private Point[] obs_std = new Point[PGConstants.OBS_NUM];
	private double[] obs_dgr = new double[PGConstants.OBS_NUM];
	private final int OBS_WIDTH;
	private final int OBS_HEIGHT;
	
	public Obstacles(String imgPath) throws FileNotFoundException, IOException {
		BufferedImage temp = ImageUtil.creatImage(imgPath);
		this.OBS_WIDTH = temp.getWidth();
		this.OBS_HEIGHT = temp.getHeight();
		OBS = new BufferedImage[PGConstants.OBS_NUM];
		for(int i = 0; i < PGConstants.OBS_NUM; ++i) {
			OBS[i] = temp;
			obs_std[i] = new Point(
									(int)((PGConstants.X_RANGE - OBS_WIDTH) * Math.random() + PGConstants.LEFT_RIGHT_BOTTOM),
									(int)((PGConstants.Y_RANGE - OBS_HEIGHT) * Math.random() + PGConstants.TOP)
									);
			while(true) {
				if(	obs_std[i].getX() > PGConstants.WIDTH_MID / 2 &&
						obs_std[i].getX() < PGConstants.WIDTH_MID * 3 / 2 &&
						obs_std[i].getY() > PGConstants.HEIGHT * 2 / 3
					) {
					obs_std[i] = new Point(
							(int)((PGConstants.X_RANGE - OBS_WIDTH) * Math.random() + PGConstants.LEFT_RIGHT_BOTTOM),
							(int)((PGConstants.Y_RANGE - OBS_HEIGHT) * Math.random() + PGConstants.TOP)
							);
				}
				else
					break;
			}
			obs_dgr[i] = 2 * Math.PI * Math.random() - Math.PI;
		}
	}
	
	public Rectangle getRect(int index) {
		return new Rectangle((int)obs_std[index].getX(), (int)obs_std[index].getY(), OBS_HEIGHT, OBS_HEIGHT);
	}
	
	public void draw(Graphics g) {
		reachBounds();
		for(int i = 0; i < PGConstants.OBS_NUM; ++i) {
			obs_std[i].setLocation( obs_std[i].getX() + PGConstants.OBS_SPEED * Math.cos(obs_dgr[i]),
									obs_std[i].getY() + PGConstants.OBS_SPEED * Math.sin(obs_dgr[i]));
			g.drawImage(OBS[i], (int)obs_std[i].getX(), (int)obs_std[i].getY(), null);
		}
	}
	
	private void reachBounds() {
		for(int i = 0; i < PGConstants.OBS_NUM; ++i) {
			if( obs_std[i].getX() <= PGConstants.LEFT_RIGHT_BOTTOM + 1 ||
				obs_std[i].getX() >= PGConstants.WIDTH - 2 - PGConstants.LEFT_RIGHT_BOTTOM - OBS_WIDTH)
				obs_dgr[i] = 3.14 - obs_dgr[i];
			else if(obs_std[i].getY() <= PGConstants.TOP + 1 ||
					obs_std[i].getY() >= PGConstants.HEIGHT - 2 - PGConstants.LEFT_RIGHT_BOTTOM - OBS_HEIGHT)
				obs_dgr[i] *= (-1);
		}
	}
}
