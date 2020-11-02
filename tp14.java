package fr.mds.bruz.tp14;

import java.util.Random;

import fr.mds.bruz.tp14.model.Jeu;

public class tp14 {

//	Créer un programme permettant de jouer une bataille navale
//	La bataille naval sera joué IA contre IA aucune action humaine ne sera nécessaire
//	La taille de la carte, des bateaux, le nombre de bateau ainsi que le nombre de joueur peuvent être modifié avant l'exécution du programme

	// {id, taille, nombre}
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private final static int[][] NAVIRES = new int[][] { { 1, 1, 1 }, { 2, 3, 2 }, { 3, 4, 2 }, { 4, 6, 1 }, };

	private final static int IDENTIFIANT = 0;
	private final static int TAILLE = 1;
	private final static int NOMBRE = 2;
	private final static int maps_X = 18;
	private final static int maps_Y = 18;

	private final static int JOUEURS = 10;

	private static int[][][] maps;

	private static Random rand = new Random();

	private static String gagnant;

	public static void main(String[] args) {

		Jeu newGame = new Jeu(2, 20, 20);
		
		for(int i = 0; i < newGame.getPlayer(); i++) {
			newGame.placeToutBateau();
		}
		
		newGame.play();
	}

	public static boolean estVivant(int joueur) {
		boolean result = false;

		for (int i = 0; i < maps[joueur].length; i++) {
			for (int j = 0; j < maps[joueur][i].length; j++) {
				if (maps[joueur][i][j] != 0 && maps[joueur][i][j] != 8 && maps[joueur][i][j] != 9) {
					result = true;
				}
			}
		}

		return result;
	}

	public static void placeToutBateau() {
		for (int i = 0; i < maps.length; i++) {
			for (int[] navire : NAVIRES) {
				for (int j = 0; j < navire[NOMBRE]; j++) {
					placeBateau(i, navire);
				}
			}
			afficheCartePlacement(i);
			System.out.println();
		}
		System.out.println("Debut du combat");
	}

	/**
	 * Place un bateau pour un joueur.
	 * 
	 * @param joueur l'indice du joueur dans le tableau
	 * @param navire le tableai representant le type de bateau a placer
	 */
	public static void placeBateau(int joueur, int[] navire) {
		int x = rand.nextInt(maps_X) % maps_X;
		int y = rand.nextInt(maps_Y) % maps_Y;
		int direction = rand.nextInt(2) % 2;

		if (estPlacable(joueur, navire, x, y, direction)) {
			placeBateauDansCarte(joueur, navire, x, y, direction);
		} else {
			placeBateau(joueur, navire);
		}
	}

	/**
	 * Indique si le bateau peut etre placé
	 * 
	 * @param joueur
	 * @param navire
	 * @param x
	 * @param y
	 * @param direction
	 * @return
	 */
	public static boolean estPlacable(int joueur, int[] navire, int x, int y, int direction) {
		boolean result = true;
		switch (direction) {
		case 0:
			for (int i = 0; i < navire[TAILLE]; i++) {
				if (x + i >= maps_X) {
					result = false;
				} else if (maps[joueur][x + i][y] != 0) {
					result = false;
				}
			}
			break;
		case 1:
			for (int i = 0; i < navire[TAILLE]; i++) {
				if (y + i >= maps_Y) {
					result = false;
				} else if (maps[joueur][x][y + i] != 0) {
					result = false;
				}
			}
			break;
		}
		return result;
	}

	/**
	 * Place reellement le bateau sur la carte du joueur
	 * 
	 * @param joueur
	 * @param navire
	 * @param x
	 * @param y
	 * @param direction
	 */
	public static void placeBateauDansCarte(int joueur, int[] navire, int x, int y, int direction) {
		if (direction == 0) {
			for (int i = 0; i < navire[TAILLE]; i++) {
				maps[joueur][x + i][0] = navire[IDENTIFIANT];
			}
		} else {
			for (int j = 0; j < navire[TAILLE]; j++) {
				maps[joueur][x][y + j] = navire[IDENTIFIANT];
			}
		}
	}

	/**
	 * Affiche la carte d'un joueur
	 * 
	 * @param joueur
	 */
	public static void afficheCartePlacement(int joueur) {
		for (int i = 0; i < maps[joueur].length; i++) {
			for (int j = 0; j < maps[joueur][i].length; j++) {
				StringBuilder result = new StringBuilder();
				switch (maps[joueur][i][j]) {
				case 8:
					result.append(ANSI_YELLOW);
					break;
				case 9:
					result.append(ANSI_RED);
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					result.append(ANSI_PURPLE);
					break;
				default:
					result.append(ANSI_BLUE);
				}
				result.append(maps[joueur][i][j]);
				result.append(" ");
				result.append(ANSI_RESET);
				System.out.print(result.toString());
			}
			System.out.println("");
		}
	}


	/**
	 * Tire sur le jouer ciblé
	 * 
	 * @param joueurCible
	 */
	public static void tire(int joueurCible) {
		// Tirage aléatoire de x et y;
		int x = -1;
		int y = -1;

		do {
			x = rand.nextInt(maps_X) % maps_X;
			y = rand.nextInt(maps_Y) % maps_Y;

			if (maps[joueurCible][x][y] == 0) {
				maps[joueurCible][x][y] = 8;
			} else if (maps[joueurCible][x][y] == 1 || maps[joueurCible][x][y] == 2 || maps[joueurCible][x][y] == 3
					|| maps[joueurCible][x][y] == 4) {
				maps[joueurCible][x][y] = 9;
			}
		} while (maps[joueurCible][x][y] != 8 && maps[joueurCible][x][y] != 9);
	}
}
