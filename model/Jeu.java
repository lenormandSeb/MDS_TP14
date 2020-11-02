package fr.mds.bruz.tp14.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mds.bruz.tp14.model.Navire.Corvette;
import fr.mds.bruz.tp14.model.Navire.Croiseur;
import fr.mds.bruz.tp14.model.Navire.Destroyer;
import fr.mds.bruz.tp14.model.Navire.Navire;
import fr.mds.bruz.tp14.model.Navire.PorteAvion;

public class Jeu {
	private static int NB_JOUEUR;
	private static Case Case = null;
	private static List<Joueur> Players = null;
	private int MAP_X;
	private int MAP_Y;

	public Jeu(int nbJoueur, int map_x, int map_y) {
		this.NB_JOUEUR = nbJoueur;
		this.MAP_X = map_x;
		this.MAP_Y = map_y;
		Case = new Case(map_x, map_y);
		Players = new ArrayList();
	}

	public int getPlayer() {
		return this.NB_JOUEUR;
	}

	public void play() {
		boolean fini = false;
		int cible = 0;
		for (int i = 0; i < getPlayer(); i++) {
			tire(i);
		}

//		while (!fini) {
//		for (int i = 0; !fini && i < getPlayer(); i++) {
//			cible = trouveCible(i);
//
//			if (cible != -1) {
//				tire(cible);
//
//				afficheCartePlacement(i);
//
//				System.out.println();
//			} else {
//				fini = true;
//			}
//		}
//	}

//	for (int i = 0; i < maps.length; i++) {
//		if (estVivant(i)) {
//			gagnant = "Joueur " + (i + 1);
//		}
//		afficheCartePlacement(i);
//		System.out.println();
//	}
//
//	System.out.println("Le gagnant est : " + gagnant);

	}

	public void placeToutBateau() {
		List<Navire> navire = new ArrayList();

		navire.add(new Corvette(3));
		navire.add(new Destroyer(4));
		navire.add(new Croiseur(2));
		navire.add(new PorteAvion(1));
		
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < navire.get(j).getNombre(); k++) {
				Case.createPosition(navire.get(j));
			}
		}

		for(int i = 0; i < getPlayer(); i++) {
			Joueur Joueur = new Joueur(i);
			Players.add(Joueur);
			Players.get(i).setShip(navire);
		}
		Case.showMap();
		System.out.println("");
	}

	/**
	 * Trouve la prochaine cible en vie
	 * 
	 * @param attaquant
	 * @return
	 */
	public static int trouveCible(int attaquant) {
		int adversaire = -1;
		boolean flag = true;
		int i = attaquant;
		do {
			if (i + 1 == Players.get(i).getId()) {
				i = 0;
			} else {
				i++;
			}

			if (Players.get(i).isAlive()) {
				adversaire = i;
				flag = false;
			}
		} while (flag && i != attaquant);

		if (i == attaquant) {
			adversaire = -1;
		}

		return adversaire;
	}

	/**
	 * Tire sur le jouer ciblé
	 * 
	 * @param joueurCible
	 */
	public void tire(int joueurCible) {
		Random rand = new Random();
		// Tirage aléatoire de x et y;
		int x = -1;
		int y = -1;

		boolean mescouille = true;
		
		x = rand.nextInt(MAP_X) % MAP_X;
		y = rand.nextInt(MAP_Y) % MAP_Y;
		
		System.out.println();
		
		
//		System.out.println(Players.get(joueurCible).shipInThisPosition(x, y));
//		while(mescouille) {
//			if (!Players.get(joueurCible).shipInThisPosition(x, y)) {
//				System.out.println("position x : " + x);
//				System.out.println("position y : " + y);
//			}
//			else {
//				mescouille = false;
//			}
//		}
//		do {
//			x = rand.nextInt(MAP_X) % MAP_X;
//			y = rand.nextInt(MAP_Y) % MAP_Y;
//
//			if (maps[joueurCible][x][y] == 0) {
//				maps[joueurCible][x][y] = 8;
//			} else if (maps[joueurCible][x][y] == 1 || maps[joueurCible][x][y] == 2 || maps[joueurCible][x][y] == 3
//					|| maps[joueurCible][x][y] == 4) {
//				maps[joueurCible][x][y] = 9;
//			}
//		} while (maps[joueurCible][x][y] != 8 && maps[joueurCible][x][y] != 9);
	}
}
