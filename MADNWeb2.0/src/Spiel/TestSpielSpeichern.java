package Spiel;

import java.io.*;
import java.util.ArrayList;

import Basisklassen.KI_Aggressiv;
import Basisklassen.KI_Defensiv;
import Basisklassen.Spieler;
import Hilfsklassen.FigurenWrapper;
import Hilfsklassen.SpielXMLWrapper;
import Hilfsklassen.SpielerWrapper;

/**
 * TestSpielSpeichern Testet Serialisiertes/CSV-basiertes Speichern
 * 
 **/
public class TestSpielSpeichern {

	public static void main(String[] args) {

		// SpielBean anlegen und irgendwas machen
		SpielBean speicherMich = new SpielBean();
		speicherMich.neuerSpieler("Hans-Joachim", 1, 1);
		speicherMich.neuerSpieler("Detlef", 2, 2);

		speicherMich.starteSpiel();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();

		// CSV
		iDatenzugriff dCSV = new DatenzugriffCSV();
		PrintWriter bw = (PrintWriter) dCSV.openFile("meep.csv", 2);
		dCSV.spielSpeichern(speicherMich, bw);
		dCSV.closeFile(bw);
		// Serialsiert
		iDatenzugriff ds = new DatenzugriffSerialisiert();
		FileOutputStream closeMe = (FileOutputStream) ds.openFile(
				"savegame.ser", 2);
		ds.spielSpeichern(speicherMich, closeMe);
		ds.closeFile(closeMe);

		iDatenzugriff dXML = new DatenzugriffXML();

		FileWriter fW = (FileWriter) dXML.openFile("D:\\test.xml", 1);

		SpielXMLWrapper sXML = new SpielXMLWrapper();
		ArrayList<SpielerWrapper> wrapperListe = new ArrayList<SpielerWrapper>();
		ArrayList<FigurenWrapper> figurenListe = new ArrayList<FigurenWrapper>();

		

		for (int i = 0; i < 4; i++) {
			if (speicherMich.getSpieler()[i] != null) {
				SpielerWrapper sW = new SpielerWrapper();
				Spieler buf = speicherMich.getSpieler()[i];
				sW.setName(buf.getName());
				sW.setFarbe(buf.getFarbe());
				sW.setIstAmZug(buf.getAmZug());
				sW.setNummer(i+1);
				if (buf.getBedienung() == null) {
					sW.setBedienung(null);
				} else if (buf.getBedienung() instanceof KI_Aggressiv) {
					sW.setBedienung("aggressiv");
				} else if (buf.getBedienung() instanceof KI_Defensiv) {
					sW.setBedienung("defensiv");
				}
				wrapperListe.add(sW);
				sW = null;
			}
		}
		
		for(int i = 0; i<4; i++){
			if(speicherMich.getSpieler()[i] == null) continue;
			System.out.println(speicherMich.getSpieler()[i]);
			for(int j=0; j<4; j++){
				if(speicherMich.getSpieler()[i].alleFiguren()[j]!=null){
					FigurenWrapper fw = new FigurenWrapper();
					fw.setFarbe(speicherMich.getSpieler()[i].getFarbe());
					fw.setFeldID(speicherMich.getSpieler()[i].alleFiguren()[j].getMeinFeld().getID());
					fw.setSchritteGelaufen(speicherMich.getSpieler()[i].alleFiguren()[j].getFelderGelaufen());
					fw.setName(fw.getFarbe().toString()+" "+j);
					fw.setGespawnt(speicherMich.getSpieler()[i].alleFiguren()[j].getIstGespawnt());
					fw.setEndposition(speicherMich.getSpieler()[i].alleFiguren()[j].getBinIchAufEndpostion());
					figurenListe.add(fw);
				}
				
			}
		}
		
		for(int i = 0; i<figurenListe.size(); i++){
			System.out.println(figurenListe.get(i));
		}
		
		for(Object o : wrapperListe.toArray()){
			SpielerWrapper printme = (SpielerWrapper) o;
				System.out.println(printme);
		}

		sXML.setFiguren(figurenListe);
		sXML.setSpieler(wrapperListe);
		
		dXML.spielSpeichern(sXML, fW);
		dXML.closeFile(fW);

	}

}
