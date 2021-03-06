package SpielTestKlasse;

import Spiel.Spiel;
import Spiel.iBediener;
/**
 * Eine Testklasse fuer die KIs, die einen Spielverlauf mit einer defensiven und einen Spielverlauf mit einer 
 * aggressiven KI simuliert.
 * @author Anna Rosa
 * @since version 3.0
 *
 */
public class KITest {
	public static void main(String[] args) {

		// KI_Defensiv Test

		iBediener ib = new Spiel();

		ib.neuerSpieler("Oleg", 1, 2);
		ib.neuerSpieler("Foelix", 2, 0);
		ib.starteSpiel();
		
		//Vortest: Es wurde nicht gewuerfelt. Erwartet: Meldung, dass man wuerfeln soll.
		ib.zugDurchfuehrenKI();
		// Test 1: Wenn KI spawnen oder der weitesten Figur laufen kann, laeuft
		// sie mit der weitesten.
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		//Zwischentest: Es wurde nicht neu gewuerfelt. Laeuft KI mit alter Zahl? Erwartet: Wuerfel-Aufforderung.
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		// R1,R2,R3 koennnen spawnen, R4 kann laufen. Erwartet: R4 laeuft.
		ib.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 2: KI kann spawnen & killen mit einer Figur, laeuft dann aber mit
		// der Weitesten.
		ib.werfen(3);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehren("S1");
		ib.werfen(6);
		ib.zugDurchfuehren("11");
		ib.werfen(6);
		ib.zugDurchfuehren("17");
		ib.werfen(6);
		ib.zugDurchfuehren("23");
		ib.werfen(6);
		ib.zugDurchfuehren("29");
		ib.werfen(6);
		ib.zugDurchfuehren("35");
		ib.werfen(6);
		ib.zugDurchfuehren("S2");
		ib.werfen(4);
		ib.zugDurchfuehren("1");
		ib.werfen(6);
		// Blau steht auf Feld 1, Spawnfeld von Rot. Rot kann mit R1, R2 und R3
		// spawnen oder mit R4 laufen. Erwartet: KI laeuft mit R4.
		ib.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 3: KI kann mit Weitester schlagen oder mit anderere Figur
		// spawnen.

		ib.werfen(2);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehren("11");
		ib.werfen(6);
		;
		ib.zugDurchfuehren("17");
		ib.werfen(6);
		ib.zugDurchfuehren("23");
		ib.werfen(1);
		ib.zugDurchfuehren("29");
		ib.werfen(6);
		// KI kann mit R4 ziehen & schlagen oder mit R1, R2, R3 spawnen.
		// Erwartet: Kill mit R4
		ib.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 4: Figur ist in Endfeld, aber nicht auf Endposition, kann nicht
		// mehr laufen, neue Figur soll gespawnt werden.
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		// Rot 4 ist auf Feld E2, kann nicht mehr ziehen. Erwartet: Neu spawnen.
		ib.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 5: Endfeld-Figur R4 kann auf Endposition ziehen oder neu
		// gespawnte kann ziehen. Erwartet: R4 zieht auf Endposition E4.
		ib.werfen(2);
		ib.zugDurchfuehrenKI();

		// Test 6: Rote Spielfigur wird geschlagen. KI soll wieder spawnen und
		// schlagen.
		ib.werfen(6);
		ib.zugDurchfuehren("S1");
		ib.werfen(6);
		ib.zugDurchfuehren("11");
		ib.werfen(6);
		ib.zugDurchfuehren("17");
		ib.werfen(6);
		ib.zugDurchfuehren("23");
		ib.werfen(6);
		ib.zugDurchfuehren("29");
		ib.werfen(6);
		ib.zugDurchfuehren("35");
		ib.werfen(1);
		ib.zugDurchfuehren("1");
		ib.werfen(6);
		// erwartet: Spawnt & schlaegt.
		ib.zugDurchfuehrenKI();
		ib.werfen(1);
		ib.zugDurchfuehrenKI();

		// Test erfolgreich.

		// Test 7: Spiel beenden.
		ib.werfen(6);
		ib.zugDurchfuehren("5");
		ib.werfen(3);
		ib.zugDurchfuehren("E1");
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		// Zwei Figuren koennen Laufen, sind beide im Feld, die weniger weite
		// kann schlagen.
		ib.werfen(1);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehren("S1");
		ib.werfen(6);
		ib.zugDurchfuehren("11");
		ib.werfen(6);
		ib.zugDurchfuehren("17");
		ib.werfen(4);
		ib.zugDurchfuehren("23");
		// R1 auf Feld 25 kann killen, R3 kann in Endfeld laufen. Erwartet: R3
		// laeuft.
		ib.werfen(2);
		ib.zugDurchfuehrenKI();
		// Test erfolgreich.
		ib.werfen(2);
		ib.zugDurchfuehren("27");
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(6);
		ib.zugDurchfuehrenKI();
		ib.werfen(1);
		ib.zugDurchfuehrenKI();
		ib.werfen(1);
		// Test: Menschlicher Spieler ist am Zug, KI Methode wird gewaehlt.
		// Erwartet: Ausgabe ueber Fehler.
		ib.zugDurchfuehrenKI();
		// Test erfolgreich.
		ib.zugDurchfuehren("29");
		ib.werfen(4);
		ib.zugDurchfuehrenKI();

		// KI_Aggressiv-Test

		iBediener iB = new Spiel();
		iB.neuerSpieler("Anna", 1, 1);
		iB.neuerSpieler("Kevin", 2, 0);
		iB.starteSpiel();
		
		// Vortest: Noch nicht gewuerfelt, erwartet: Wuerfel-Aufforderung
		iB.zugDurchfuehrenKI();
		//Test erfolgreich
		
		// Test 1: Macht KI lieber Spawn-Feld frei, als mit Weitester zu laufen,
		// wenn schlagen nicht moeglich ist?

		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		
		//Zwischentest: Es wurde noch nicht gewuerfelt, aber schon vorher. Erwartet: Wuerfel-Aufforderung.
		iB.zugDurchfuehrenKI();
		//Test erfolgreich
		
		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		iB.werfen(1);
		iB.zugDurchfuehrenKI();
		iB.werfen(6);
		iB.zugDurchfuehren("S1");
		iB.werfen(1);
		iB.zugDurchfuehren("11");
		// Zwischentest: Spawnt KI lieber, statt mit weitester Figur zu laufen?
		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		// Test erfolgreich
		// Erwartet: Rot laeuft mit Figur Rot 2 von Feld 1 weg, anstat mit Rot 1
		// von Feld 8.
		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 2: ob KI lieber schlaegt, als mit Weitester zu Laufen.

		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		iB.werfen(4);
		iB.zugDurchfuehrenKI();
		// Figuren-Stand: R1: 20, R2: 7, R3:5
		iB.werfen(6);
		iB.zugDurchfuehren("12");
		iB.werfen(6);
		iB.zugDurchfuehren("18");
		iB.werfen(6);
		iB.zugDurchfuehren("24");
		iB.werfen(6);
		iB.zugDurchfuehren("30");
		iB.werfen(6);
		iB.zugDurchfuehren("36");
		iB.werfen(6);
		iB.zugDurchfuehren("2");
		iB.werfen(1);
		iB.zugDurchfuehren("8");
		iB.werfen(2);
		// Jetzt steht B1 auf Feld 9, ist also mit einer 2 schlagbar. Erwartet:
		// R2 schlaegt, anstatt das R1 laeuft.
		iB.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 3: Test, ob Schlagen & Spawnen eher durchgefuehrt wird, als dass
		// die weiteste Figur schlaegt.

		iB.werfen(6);
		iB.zugDurchfuehren("S1");
		iB.werfen(6);
		iB.zugDurchfuehren("11");
		iB.werfen(6);
		iB.zugDurchfuehren("17");
		iB.werfen(6);
		iB.zugDurchfuehren("23");
		iB.werfen(6);
		iB.zugDurchfuehren("29");
		iB.werfen(6);
		iB.zugDurchfuehren("35");
		// Blau 1 ist auf Feld 1
		iB.werfen(6);
		iB.zugDurchfuehren("S2");
		iB.werfen(6);
		iB.zugDurchfuehren("11");
		iB.werfen(6);
		iB.zugDurchfuehren("17");
		iB.werfen(3);
		iB.zugDurchfuehren("23");
		iB.werfen(6);
		// Rot 1 auf Feld 20 kann killen, Rot 3 auf S4 kann spawnen & killen.
		// Erwartet: R3 zieht.
		iB.zugDurchfuehrenKI();
		// Test erfolgreich

		// Test 4: Schlaegt KI lieber mit weitesten Figur als mit anderen?

		iB.werfen(3);
		iB.zugDurchfuehrenKI();
		iB.werfen(6);
		iB.zugDurchfuehren("S1");
		iB.werfen(4);
		iB.zugDurchfuehren("11");
		iB.werfen(6);
		// Rot kann mit R1 und R2 killen. Erwartet: Rot zieht mit R1 auf Feld
		// 26.
		iB.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 5: Falls weder schlagen, noch spawnen moeglich ist, laeuft
		// weiteste.

		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		iB.werfen(5);
		// R1: 26, R2: 15, R3: 5, R4: 4. Erwartet: R1 zieht auf 31.
		iB.zugDurchfuehrenKI();
		// Test erfolgreich.

		// Test 6: Eine Figur koennte in Endposition, eine schlagen. Erwartet:
		// Figur schlaegt.

		iB.werfen(6);
		iB.zugDurchfuehren("S1");
		iB.werfen(6);
		iB.zugDurchfuehren("11");
		iB.werfen(2);
		iB.zugDurchfuehren("17");
		iB.werfen(5);
		iB.zugDurchfuehrenKI();
		iB.werfen(2);
		iB.zugDurchfuehren("19");
		iB.werfen(6);
		// R1 kann ins Ziel, R2 kann killen. Erwartet: R2 killt, zieht auf 21.
		iB.zugDurchfuehrenKI();
		// Test erfolgreich

		// Test 6: Min Zwei Figuren koennen laufen, weiteste nicht

		iB.werfen(6);
		iB.zugDurchfuehrenKI();
		iB.werfen(6);
		// R1 ist auf E2 -> unfaehig zu laufen, R2 auf Feld 21, R3 auf Feld 5, R4
		// auf Feld 4 koennen laufen.
		// Erwartet: R2 laeuft auf Feld 27
		iB.zugDurchfuehrenKI();
		// Test erfolgreich.

		//Spiel fertig spielen.
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(2);
        iB.zugDurchfuehrenKI();
        iB.werfen(1);
        iB.werfen(1);
        iB.werfen(1);
        iB.werfen(6);
        iB.zugDurchfuehrenKI();
        iB.werfen(2);
        iB.zugDurchfuehrenKI();
        iB.werfen(1);
        iB.werfen(1);
        iB.werfen(1);
        iB.werfen(3);

        // Test, dass KI nicht mit Methode fuer menschlichen Spieler ziehen kann, also immer selbst entscheiden muss.
        // Erwartet: Meldung, dass eine andere Methode verwendet werden soll.
        iB.zugDurchfuehren("39");
        // Test erfolgreich.
        
        iB.zugDurchfuehrenKI();
        iB.werfen(1);
        iB.werfen(1);
        iB.werfen(1);
        iB.werfen(1);
        iB.zugDurchfuehrenKI();
        iB.werfen(1);
        
        
        //Endtest: Was passiert, wenn KI nochmal so ziehen soll, aber Spiel schon beendet ist?
        iB.zugDurchfuehrenKI();
        iB.werfen(6);
        //Test erfolgreich.
		
	}

}
