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
	public String[] zugDurchfuehren(String ID);
	public int rollTheDice();
	public boolean ausgabeSpielerAmZug();
	public String[] zugDurchfuehrenKI();
	public boolean getStatus();
	public ArrayList<String> updateGUIFigures();
	public Boolean[] updateGUIInfo();
	public Boolean getBeendet();
	public int augenzahl();
	

}
