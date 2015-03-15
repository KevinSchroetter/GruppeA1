package Basisklassen;

public abstract class Spielfeld {

	private Spielfigur figur;

	public Spielfeld() {
		this.figur = null;
	}


	public Spielfigur getFigur() {
		return figur;
	}

	protected void setFigur(Spielfigur figur) {
		this.figur = figur;

	}

	static class Startfeld extends Spielfeld {
		private FarbEnum farbe;
		private static int id;

		public Startfeld(FarbEnum farbe) {
			super();
			this. id = Startfeld.id + 1;
			this.farbe = farbe;
			
		}

		public int getId() {
			return this.id;
		}

		public FarbEnum getFarbe() {
			return this.farbe;
		}

		@Override
		public boolean equals(Object obj){
			Startfeld f = null;
			if(obj instanceof Startfeld)
				f = (Startfeld) obj;
			return f.getId() == this.getId();
		}
		
		@Override
		public String toString(){
			return String.valueOf(this.getFarbe()) +" "+ String.valueOf(this.getId()) +" "+ String.valueOf(this.getFigur());
		}

	}

	static class Standardfeld extends Spielfeld {
		private static int id;

		public Standardfeld() {
			super();
			this. id = Standardfeld.id + 1;
		}

		public int getId() {
			return id;
		}
		
		@Override
		public boolean equals(Object obj){
			Standardfeld f = null;
			if(obj instanceof Standardfeld)
				f = (Standardfeld) obj;
			return f.getId() == this.getId();
		}
		
		@Override
		public String toString(){
			return String.valueOf(this.getId()) +" "+ String.valueOf(this.getFigur());
		}

	}

	static class Inventarfeld extends Spielfeld {
		private static int id;
		private FarbEnum farbe;

		public Inventarfeld(FarbEnum farbe) {
			super();
			this. id = Inventarfeld.id + 1;
		}

		public int getId() {
			return id;
		}

		public FarbEnum getFarbe() {
			return farbe;
		}
		
		@Override
		public boolean equals(Object obj){
			Inventarfeld f = null;
			if(obj instanceof Inventarfeld)
				f = (Inventarfeld) obj;
			return f.getId() == this.getId();
		}
		
		@Override
		public String toString(){
			return String.valueOf(this.getFarbe()) +" "+ String.valueOf(this.getId()) +" "+ String.valueOf(this.getFigur());
		}

	}

	static class Zielfeld extends Spielfeld {
		private static int id;
		private FarbEnum farbe;
		private boolean letztes;

		public Zielfeld(FarbEnum farbe, boolean letztes) {
			super();
			this. id = Zielfeld.id + 1;
			this.farbe = farbe;
			this.letztes = letztes;
		}

		public boolean isLetztes() {
			return letztes;
		}

		public int getId() {
			return id;
		}

		public FarbEnum getFarbe() {
			return farbe;
		}
		
		@Override
		public boolean equals(Object obj){
			Zielfeld f = null;
			if(obj instanceof Zielfeld)
				f = (Zielfeld) obj;
			return f.getId() == this.getId();
		}
		
		@Override
		public String toString(){
			return String.valueOf(this.getFarbe()) +" "+ String.valueOf(this.getId()) +" "+ String.valueOf(this.getFigur()) + " " + String.valueOf(this.isLetztes());
		}

	}
}
