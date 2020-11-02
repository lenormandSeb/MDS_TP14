package fr.mds.bruz.tp14.model.Navire;

import java.util.List;

import fr.mds.bruz.tp14.model.Case;

public abstract class Navire {
	private static int IDENTIFIANT;
	private static int TAILLE;

	public Navire(int identifiant, int taille) {
		this.IDENTIFIANT = identifiant;
		this.TAILLE = taille;
	}
	
	public abstract int getIdentifiant();
	
	public abstract int getTaille();

	public abstract int getNombre();
	
	public abstract void getTest();

	public abstract void pushMapping(int x, int y);

	public abstract List getMapping();
}
