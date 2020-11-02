package fr.mds.bruz.tp14.model.Navire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.mds.bruz.tp14.model.Case;

public class Corvette extends Navire {
	
	private static int IDENTIFIANT = 1;
	private static int TAILLE = 1;
	private static int NOMBRE;
	private static List<int[][]> MAPPING = null;
	
	public Corvette(int nombre) {
		super(IDENTIFIANT, TAILLE);
		this.NOMBRE = nombre;
		this.MAPPING = new ArrayList();
	}
	
	public int getNombre() {
		return this.NOMBRE;
	}

	@Override
	public int getIdentifiant() {
		// TODO Auto-generated method stub
		return IDENTIFIANT;
	}

	@Override
	public int getTaille() {
		// TODO Auto-generated method stub
		return TAILLE;
	}

	@Override
	public List getMapping() {
		return MAPPING;
	}

	@Override
	public void pushMapping(int x, int y) {
		MAPPING.add(new int[x][y]);
	}

	@Override
	public void getTest() {
		// TODO Auto-generated method stub
		
	}

}
