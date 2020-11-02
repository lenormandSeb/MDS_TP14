package fr.mds.bruz.tp14.model.Navire;

import java.util.ArrayList;
import java.util.List;

import fr.mds.bruz.tp14.model.Case;

public class Destroyer extends Navire {
	private static int IDENTIFIANT = 2;
	private static int TAILLE = 3;
	private static int NOMBRE;
	private static List<int[][]> MAPPING = null;
	
	public Destroyer(int nombre) {
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
	public void getTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushMapping(int x, int y) {
		MAPPING.add(new int[x][y]);
	}

	@Override
	public List getMapping() {
		return MAPPING;
	}
}
