package fr.mds.bruz.tp14.model;

import java.util.List;

import fr.mds.bruz.tp14.model.Navire.Navire;

public class Joueur {

	private static int ID;
	private static List<Navire> navire;
	
	public Joueur(int id) {
		this.ID = id;
	}
	
	public void setShip(List navire) {
		this.navire = navire;
	}

	public static int getId() {
		return ID;
	}
	
	
	public static boolean isAlive() {
		boolean r = true;
		if (navire.size() / 4 == 0) {
			r = false;
		}
		return r;
	}

	public boolean shipInThisPosition(int x, int y) {
			boolean r = false;
			for(Navire ship : navire) {
				for(int i = 0; i < ship.getMapping().size(); i++) {
					System.out.println(i);
					if (ship.getMapping().contains(new int[x][y])) {
						r = true;
					}
				}
			}
			return r;
	}
}
