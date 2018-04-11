package com.plantgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.frame.GameFrame;
import com.frame.ImageUtil;

@SuppressWarnings("serial")
public class PlantGame extends GameFrame {
	private Plant plant;
	private Obstacles obs;
	private BufferedImage bg;
	private boolean enter;
	private Date begTime;
	private int playTime;
	private boolean again;
	
	public PlantGame() throws FileNotFoundException, IOException {
		this.plant = new Plant("E:\\JAVA-PROJECTS\\PlantGame\\src\\Images\\飞机.png");
		this.obs = new Obstacles("E:\\JAVA-PROJECTS\\PlantGame\\src\\Images\\障碍物.png");
		this.bg = ImageUtil.creatImage("E:\\JAVA-PROJECTS\\PlantGame\\src\\Images\\background.jpg");
		this.width = PGConstants.WIDTH;
		this.height = PGConstants.HEIGHT;
		this.title = PGConstants.TITLE;
	}

	@Override
	public void launchFrame() {
		super.launchFrame();
		addKeyListener(new KeyMonitor());
	}
	
	private static void printInfo(Graphics g, String mes, String font, int x, int y, int pixelSize, Color color) {
		Color c = g.getColor();
		g.setColor(color);
		g.setFont(new Font(font, Font.BOLD, pixelSize));
		g.drawString(mes, x, y);
		g.setColor(c);
	}

	@Override
	public void paint(Graphics g) {
		try {
			g.drawImage(bg, 0, 0, null);
			if(!enter) {
				printInfo( g, "PLANE GAME", "Berlin Sans FB Demi",
							PGConstants.WIDTH_MID * 3 / 7, PGConstants.HEIGHT_DOWN * 9 / 20, 110, Color.DARK_GRAY);
				printInfo( g, "Press Enter to Begin", "High Tower Text",
							(int)(PGConstants.WIDTH_MID * 6.8 / 10), PGConstants.HEIGHT * 3 / 5, 40, Color.DARK_GRAY);
				printInfo( g, "PRESS SPACE TO SHOOT", "Berlin Sans FB",
							PGConstants.LEFT_RIGHT_BOTTOM * 2, PGConstants.HEIGHT_DOWN - PGConstants.LEFT_RIGHT_BOTTOM, 20, Color.DARK_GRAY);
			}
			else if(plant.getAlive())
			{
				plant.draw(g);
				obs.draw(g);
				for(int i = 0; i < PGConstants.OBS_NUM; ++i) {
					boolean col = plant.getRect().intersects(obs.getRect(i));
					if(col) {
						plant.setAlive(false);
						playTime = (int)(new Date().getTime() - begTime.getTime()) / 1000;
						break;
					}
				}
				playTime = (int)(new Date().getTime() - begTime.getTime()) / 1000;
				printInfo( g, "Time:" + playTime + "s", "Candara",
							PGConstants.WIDTH - 130, PGConstants.LEFT_RIGHT_BOTTOM * 8, 30, Color.DARK_GRAY);
			}
			else {
				printInfo( g, "GAME OVER", "Berlin Sans FB Demi",
							(int)(PGConstants.WIDTH_MID * 0.525), PGConstants.HEIGHT / 2, 100, Color.DARK_GRAY);
				printInfo( g, "GOAL : " + playTime + "s", "Candara",
						(int)(PGConstants.WIDTH_MID * 0.85), PGConstants.HEIGHT / 2 + 100, 40, Color.DARK_GRAY);
				printInfo( g, "Press B to Play Again", "Berlin Sans FB",
							PGConstants.LEFT_RIGHT_BOTTOM * 2, PGConstants.HEIGHT_DOWN - PGConstants.LEFT_RIGHT_BOTTOM, 20, Color.DARK_GRAY);
				if(again) {
					again = false;
					this.plant = new Plant("E:\\JAVA-PROJECTS\\PlantGame\\src\\Images\\飞机.png");
					this.obs = new Obstacles("E:\\JAVA-PROJECTS\\PlantGame\\src\\Images\\障碍物.png");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				enter = true;
				begTime = new Date();
			}
			if(e.getKeyCode() == KeyEvent.VK_B) {
				again = true;
				begTime = new Date();
			}
			plant.pressDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plant.releaseDirection(e);
		}
		
	}
}
