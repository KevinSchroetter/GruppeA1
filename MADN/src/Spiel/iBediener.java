package Spiel;

import java.util.ArrayList;

import Basisklassen.Spielfigur;

public interface iBediener {
	
	
	
	public ArrayList<Spielfigur> ausgabeFiguren();
	public ArrayList<Spielfigur> ausgabeZugFiguren();
	public ArrayList<Spielfigur> ausgabeFigurenImZiel();
	public ArrayList<Spielfigur> ausgabeFigurenAufStartfeld();
	public void ausgabeSpielerListe();
	public boolean zugDurchführen(String ID, int würfelZahl);
	public int rollTheDice();
	

}
