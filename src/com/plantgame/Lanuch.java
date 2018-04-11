package com.plantgame;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Lanuch {
	public static void main(String[] args) {
		try {
			new PlantGame().launchFrame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
