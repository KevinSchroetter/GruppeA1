package Spiel;

public class TestNeueCSV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		iDatenzugriff save = new DatenzugriffCSVNew();
		
		Spiel game = null;
//		game.neuerSpieler("Alex", 1, 1);
//		game.neuerSpieler("Fritz", 2, 2);
//		
//		game.starteSpiel();
//		game.wuerfeln(6);
//		game.zugDurchfuehrenKI();
//		game.wuerfeln(6);
//		game.zugDurchfuehrenKI();
//		game.wuerfeln(6);
//		game.zugDurchfuehrenKI();
//		game.wuerfeln(6);
//		game.zugDurchfuehrenKI();
//		game.wuerfeln(3);
//		game.zugDurchfuehrenKI();
//		System.out.println("Ende Test");
//		save.spielSpeichern(game, save.openFile("effizienteCSV.csv", 1));
//		
//		
		
//		game = null;
		game = (Spiel) save.spielLaden(save.openFile("effizienteCSV.csv",0));
		game.update();
		game.ausgabeSpielerListe();
		game.ausgabeFiguren();	
		while(!game.getZugMoeglich()){
			System.out.println(game.rollTheDice());
		}
		game.zugDurchfuehrenKI();

//		game.rollTheDice();
//		game.zugDurchfuehrenKI();
//		game.rollTheDice();
//		
//		game.zugDurchfuehrenKI();
//		game.rollTheDice();
//		game.zugDurchfuehrenKI();
//		game.rollTheDice();
	}

}
