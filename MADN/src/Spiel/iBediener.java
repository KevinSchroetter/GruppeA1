package Spiel;

import java.util.ArrayList;

import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
/**
 * Interface, ueber das das Spiel bedient wird
 * @author Alexander Brueckner
 * @version 3.0
 * @since v2.2
 *
 */
public interface iBediener {
	
	
	public void starteSpiel();
	public void werfen(int zahl);
	public boolean neuerSpieler(String name, int farbID, int verhaltenID);
	public void neuesSpielErstellen();
	public ArrayList<String> getZugInfo();
	public ArrayList<Spielfigur> ausgabeFiguren();
	public ArrayList<Spielfigur> ausgabeZugFiguren();
	public ArrayList<Spielfigur> ausgabeFigurenImZiel();
	public ArrayList<Spielfigur> ausgabeFigurenAufStartfeld();
	public void ausgabeSpielerListe();
	public boolean zugDurchfuehren(String ID);
	public int rollTheDice();
	public Spieler ausgabeSpielerAmZug();
	public void zugDurchfuehrenKI();
	

}
