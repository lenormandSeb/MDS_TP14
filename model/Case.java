package fr.mds.bruz.tp14.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mds.bruz.tp14.model.Navire.Navire;

public class Case {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private static int[][] MAPS;
	private int MAP_X;
	private int MAP_Y;
	private static Random rand = new Random();
	private static List<Navire> shipPlayeur = null;

	public Case(int map_x, int map_y) {
		this.MAP_X = map_x;
		this.MAP_Y = map_y;
		this.MAPS = new int[map_x][map_y];
		this.shipPlayeur = new ArrayList();
	}

	private boolean isPossible(int x, int y, int sizeShip, int direction) {
		boolean r = true;
		switch (direction) {
		case 0:
			for (int i = 0; i < sizeShip; i++) {
				if (x + i >= MAP_X) {
					r = false;
				} else if (MAPS[x + i][y] != 0) {
					r = false;
				}
			}
			break;
		case 1:
			for (int i = 0; i < sizeShip; i++) {
				if (y + i >= MAP_Y) {
					r = false;
				} else if (MAPS[x][y + i] != 0) {
					r = false;
				}
			}
			break;
		}
		return r;
	}

	public void createPosition(Navire navire) {
			int posX = rand.nextInt(MAP_X) % MAP_X;
			int posY = rand.nextInt(MAP_Y) % MAP_Y;
			int direction = rand.nextInt(2) % 2;
			if (this.isPossible(posX, posY, navire.getTaille(), direction)) {
				System.out.println("place true for " + navire.getIdentifiant());
				this.placeNavireInMap(posX, posY, navire, direction);
				//push navire mapping in Navire children
				navire.pushMapping(posX, posY);
				//Push for laters
				this.shipPlayeur.add(navire);
			} else {
				this.createPosition(navire);
			}
	}
	
	public List getPlayeurShip() {
		return this.shipPlayeur;
	}

	private void placeNavireInMap(int posX, int posY, Navire navire, int direction) {
		if (direction == 0) {
			for (int i = 0; i < navire.getTaille(); i++) {
				MAPS[posX + i][0] = navire.getIdentifiant();
			}
		} else {
			for (int j = 0; j < navire.getTaille(); j++) {
				MAPS[posX][posY + j] = navire.getIdentifiant();
			}
		}
	}

	public void showMap() {
		String line = "";
		for (int i = 0; i < MAP_Y; i++) {
			for (int j = 0; j < MAP_X; j++) {
				System.out.print(MAPS[j][i]);;
			}
			System.out.println("");
		}
	}
}
