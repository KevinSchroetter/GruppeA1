package Spiel;

import java.util.ArrayList;

import Basisklassen.Spielfigur;

public interface iBediener {
	
	
	public void starteSpiel();
	public void werfen(int zahl);
	public void neuerSpieler(String name, int farbID, int verhaltenID);
	
	public ArrayList<Spielfigur> ausgabeFiguren();
	public ArrayList<Spielfigur> ausgabeZugFiguren();
	public ArrayList<Spielfigur> ausgabeFigurenImZiel();
	public ArrayList<Spielfigur> ausgabeFigurenAufStartfeld();
	public void ausgabeSpielerListe();
	public boolean zugDurchführen(String ID, int würfelZahl);
	public int rollTheDice();
	

}
