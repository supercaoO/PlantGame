package com.frame;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class GameFrame extends Frame {
	protected int width = FrameConstants.WIDTH;
	protected int height = FrameConstants.HEIGHT;
	protected String title = FrameConstants.TITLE;
	
	public void launchFrame() {
		setLocation(FrameConstants.X_LOCATION, FrameConstants.Y_LOCATION);
		setSize(width, height);
		setTitle(title);
		setVisible(true);
		
		new PaintThread().start();
		
		addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		});
	}

	class PaintThread extends Thread {

		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Ë«»º³å
	 */
	private Image offscreenImage = null;
	public void update(Graphics g) {
		if(offscreenImage == null)
			offscreenImage = this.createImage(width, height);
		Graphics gOff = offscreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offscreenImage, 0, 0, null);
	}
	
}
