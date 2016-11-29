package wargame;

 

public class Position implements IConfig {

	private int x, y;
	Position (){
		
	}
	Position(int x, int y) { this.x = x; this.y = y; }
	Position (int num){ /***** besoin pour la gestion des evenements***/
		x = num % IConfig.LARGEUR_CARTE;
		y = num / IConfig.LARGEUR_CARTE;
	}
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) return false; else return true;
	}
	public String toString() { return "("+x+","+y+")"; }
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	public int getNumCase(){
		return x + IConfig.LARGEUR_CARTE * y;
	}
	
	/**
	 * M�thode permettant de sp�cifier le num�ro de case de la position. Met donc � jour x et y
	 * @param num Num�ro de la case dans la carte
	 */
	public void setNumCase(int num){
		x = num % IConfig.LARGEUR_CARTE;
		y = num / IConfig.LARGEUR_CARTE;
	}
	
	
	/***** m�thode pour calculer la distance entre deux positions (en nombres de cases
	 * on en aura besoin pour la port�e visuelle 
	 * CAS PARTICULIER: si la case est adjacente alors retourner 1
	 * ***/
	
	public int distance(Position pos){
		if (this.getNumCase() == -1 || pos.getNumCase() == -1) return 0;
		
		int dx = Math.abs(this.x - pos.x);
		int dy = Math.abs(this.y - pos.y);

		if(dx == 1 && dy == 1)
			return 1;

		return dx + dy;
	}
	
	/**
	 * M�thode v�rifiant si deux positions sont �gales
	 * @param pos Position � v�rifier
	 * @return Vrai si les deux positions sont �gales, faux sinon
	 */
	public boolean equals(Position pos){
		return ((pos.x == this.x) && (pos.y == this.y));  
	}
	
	
	
	
}