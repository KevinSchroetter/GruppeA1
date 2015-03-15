package Basisklassen;

/**
 * Dies ist die abstrakte Klasse Spielfeld für das MADN-Spiel. Die Klasse
 * beinhaltet die Elementarklassen Startfeld, Inventarfeld, Standardfeld und
 * Zielfeld. Die Klasse Spielfeld besitzt das Attribut figur, welches Sie an
 * ihre Elementarklassen vererbt.
 * 
 * @author Felix Rosa
 * @version 1.1
 * */

public abstract class Spielfeld {

	private Spielfigur figur;

	/**
	 * Konstruktor der es ermöglicht ein Objekt des Typs Spielfeld zu erstellen.
	 * Das Spielfeld besitzt ein Attribut des Typs figur.
	 * */
	public Spielfeld() {
		this.figur = null;
	}

	/**
	 * Methode welche die Figur zurückgibt, die sich auf dem Spielfeld befindet
	 * 
	 * @return figur
	 * */
	public Spielfigur getFigur() {
		return figur;
	}

	/**
	 * Methode über die ein Objekt des Typs figur auf das Feld gesetzt werden
	 * kann.
	 * 
	 * @param figur
	 * */
	protected void setFigur(Spielfigur figur) {
		this.figur = figur;

	}

	/**
	 * Dies ist die Elementarklasse Startfeld für das MADN-Spiel. Über diese
	 * Klasse werden die Objekte des Typs Startfeld für MADN erstellt, von
	 * welcher die Spielfiguren auf dem Spielbrett in das Spiel starten. Die
	 * Klasse hat die Attribute iD über die jedes Feld identifizierbar ist sowie
	 * eine Farbe.
	 * 
	 * */
	static class Startfeld extends Spielfeld {
		private static int iD;
		private FarbEnum farbe;

		/**
		 * Konstruktor der es ermöglicht ein Objekt des Typs Startfeld zu
		 * erstellen. Das Feld besitzt ein Attribut des Typs figur, welches es
		 * von der Superklasse Spielfeld erbt. Übergeben werden die Farbe des
		 * Feldes.
		 * 
		 * @param farbe
		 * 
		 * */
		public Startfeld(FarbEnum farbe) {
			super();
			this.iD = ++Startfeld.iD;
			this.farbe = farbe;

		}

		/**
		 * Methode vom Typ int welche die ID des Feldes zurückgibt.
		 * 
		 * @return iD
		 * */
		public int getID() {
			return this.iD;
		}

		/**
		 * Methode welche die Farbe des Feldes zurückgibt.
		 * 
		 * @return farbe
		 * */
		public FarbEnum getFarbe() {
			return this.farbe;
		}

		/**
		 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
		 * Objekt des Typs Startfeld castet und dieses mit dem bestehenden
		 * Objekt vergleicht. Der Vergleich erfolgt über die ID.
		 * 
		 * @param Object
		 *            obj
		 * @return f.getID() == this.getID()
		 * */
		@Override
		public boolean equals(Object obj) {
			Startfeld f = null;
			if (obj instanceof Startfeld)
				f = (Startfeld) obj;
			return f.getID() == this.getID();
		}

		/**
		 * Überschriebene toString angepasst für die Klasse Startfeld. Wandelt
		 * die Farbe, die ID und die Figur in einen String und gibt diesen aus.
		 * 
		 * @return String.valueOf(this.getFarbe()) + " " +
		 *         String.valueOf(this.getID()) + " " +
		 *         String.valueOf(this.getFigur())
		 * */
		@Override
		public String toString() {
			return String.valueOf(this.getFarbe()) + " "
					+ String.valueOf(this.getID()) + " "
					+ String.valueOf(this.getFigur());
		}

	}

	/**
	 * Dies ist die Elementarklasse Standardfeld für das MADN-Spiel. Über diese
	 * Klasse werden die Objekte des Typs Standardfeld für MADN erstellt, welche
	 * die regulären Felder des Spiels bilden Die Klasse hat die Attribute iD
	 * über die jedes Feld identifizierbar.
	 * 
	 * */
	static class Standardfeld extends Spielfeld {
		private static int iD;

		/**
		 * Konstruktor der es ermöglicht ein Objekt des Typs Standardfeld zu
		 * erstellen. Das Feld besitzt ein Attribut des Typs figur, welches es
		 * von der Superklasse Spielfeld erbt.
		 * 
		 * */
		public Standardfeld() {
			super();
			this.iD = ++Standardfeld.iD;
		}

		/**
		 * Methode vom Typ int welche die ID des Feldes zurückgibt.
		 * 
		 * @return iD
		 * */
		public int getID() {
			return this.iD;
		}

		/**
		 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
		 * Objekt des Typs Standardfeld castet und dieses mit bestehenden Objekt
		 * vergleicht. Der Vergleich erfolgt über die ID.
		 * 
		 * @param Object
		 *            obj
		 * @return f.getID() == this.getID()
		 * */
		@Override
		public boolean equals(Object obj) {
			Standardfeld f = null;
			if (obj instanceof Standardfeld)
				f = (Standardfeld) obj;
			return f.getID() == this.getID();
		}

		/**
		 * Überschriebene toString angepasst für die Klasse Standardfeld.
		 * Wandelt die ID und die Figur in einen String und gibt diesen aus.
		 * 
		 * @return String.valueOf(this.getID()) + " " +
		 *         String.valueOf(this.getFigur())
		 * */
		@Override
		public String toString() {
			return String.valueOf(this.getID()) + " "
					+ String.valueOf(this.getFigur());
		}

	}

	/**
	 * Dies ist die Elementarklasse Inventarfeld für das MADN-Spiel. Über diese
	 * Klasse werden die Objekte des Typs Inventarfeld für MADN erstellt, auf
	 * welchen die Spielfiguren stehen bevor Sie auf das Spielbrett geschickt
	 * werden Die Klasse hat die Attribute iD über die jedes Feld
	 * identifizierbar ist sowie eine Farbe.
	 * 
	 * */
	static class Inventarfeld extends Spielfeld {
		private static int iD;
		private FarbEnum farbe;

		/**
		 * Konstruktor der es ermöglicht ein Objekt des Typs Inventarfeld zu
		 * erstellen. Das Feld besitzt ein Attribut des Typs figur, welches es
		 * von der Superklasse Spielfeld erbt. Übergeben wird die Farbe des
		 * Feldes.
		 * 
		 * @param farbe
		 * */
		public Inventarfeld(FarbEnum farbe) {
			super();
			this.iD = ++Inventarfeld.iD;
		}

		/**
		 * Methode vom Typ int welche die ID des Feldes zurückgibt.
		 * 
		 * @return iD
		 * */
		public int getID() {
			return this.iD;
		}

		/**
		 * Methode welche die Farbe des Feldes zurückgibt.
		 * 
		 * @return farbe
		 * */
		public FarbEnum getFarbe() {
			return farbe;
		}

		/**
		 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
		 * Objekt des Typs Inventarfeld castet und dieses mit dem bestehenden
		 * Objekt vergleicht. Der Vergleich erfolgt über die ID.
		 * 
		 * @param Object
		 *            obj
		 * @return f.getID() == this.getID()
		 * */
		@Override
		public boolean equals(Object obj) {
			Inventarfeld f = null;
			if (obj instanceof Inventarfeld)
				f = (Inventarfeld) obj;
			return f.getID() == this.getID();
		}

		/**
		 * Überschriebene toString angepasst für die Klasse Inventarfeld.
		 * Wandelt die Farbe, die ID und die Figur in einen String und gibt
		 * diesen aus.
		 * 
		 * @return String.valueOf(this.getFarbe()) + " " +
		 *         String.valueOf(this.getID()) + " " +
		 *         String.valueOf(this.getFigur())
		 * */
		@Override
		public String toString() {
			return String.valueOf(this.getFarbe()) + " "
					+ String.valueOf(this.getID()) + " "
					+ String.valueOf(this.getFigur());
		}

	}

	/**
	 * Dies ist die Elementarklasse Zielfeld für das MADN-Spiel. Über diese
	 * Klasse werden die Objekte des Typs Zielfeld für MADN erstellt, in welchen
	 * sich die Spielfiguren einer Farbe sammeln um das Spielziel zu erreichen
	 * Die Klasse hat die Attribute iD über die jedes Feld identifizierbar ist
	 * sowie eine Farbe und die Boolean-Abfrage ob es sich um das letzte Feld
	 * der Zielreihe handelt.
	 * 
	 * */

	static class Zielfeld extends Spielfeld {
		private static int iD;
		private FarbEnum farbe;
		private boolean letztes;

		/**
		 * Konstruktor der es ermöglicht ein Objekt des Typs Zielfeld zu
		 * erstellen. Das Feld besitzt ein Attribut des Typs figur, welches es
		 * von der Superklasse Spielfeld erbt. Übergeben werden die farbe des
		 * feldes sowie der boolean ob es sich um das letzte Feld handelt.
		 * 
		 * @param farbe
		 *            , letzte
		 * */
		public Zielfeld(FarbEnum farbe, boolean letztes) {
			super();
			this.iD = ++Zielfeld.iD;
			this.farbe = farbe;
			this.letztes = letztes;
		}

		/**
		 * Methode vom Typ boolean welche zurückgibt ob es sich bei dem Zielfeld
		 * um das letzte in der Reihe handelt.
		 * 
		 * @return letztes
		 * */
		public boolean isLetztes() {
			return letztes;
		}

		/**
		 * Methode vom Typ int welche die ID des Feldes zurückgibt.
		 * 
		 * @return iD
		 * */
		public int getID() {
			return this.iD;
		}

		/**
		 * Methode welche die Farbe des Feldes zurückgibt.
		 * 
		 * @return farbe
		 * */
		public FarbEnum getFarbe() {
			return farbe;
		}

		/**
		 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
		 * Objekt des Typs Zielfeld castet und dieses mit dem bestehenden Objekt
		 * vergleicht. Der Vergleich erfolgt über die ID.
		 * 
		 * @param Object
		 *            obj
		 * @return f.getID() == this.getID()
		 * */
		@Override
		public boolean equals(Object obj) {
			Zielfeld f = null;
			if (obj instanceof Zielfeld)
				f = (Zielfeld) obj;
			return f.getID() == this.getID();
		}

		/**
		 * Überschriebene toString angepasst für die Klasse Zielfeld. Wandelt
		 * die Farbe, die ID, die Figur und Letztes in einen String und gibt
		 * diesen aus.
		 * 
		 * @return String.valueOf(this.getFarbe()) + " " +
		 *         String.valueOf(this.getID()) + " " +
		 *         String.valueOf(this.getFigur()) + " " +
		 *         String.valueOf(this.isLetztes())
		 * */
		@Override
		public String toString() {
			return String.valueOf(this.getFarbe()) + " "
					+ String.valueOf(this.getID()) + " "
					+ String.valueOf(this.getFigur()) + " "
					+ String.valueOf(this.isLetztes());
		}

	}
}
