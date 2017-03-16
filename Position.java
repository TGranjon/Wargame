package wargame;

import java.io.Serializable;

	/**
	 * @author Thomas GRANJON
	 * @author Axel SOFONEA
	 * @author Sarah RIGHI
	 *
	 */

public class Position implements IConfig , Serializable{


	private static final long serialVersionUID = 1L;
	private int x, y;
	
	/**
	 * Constructeur vide de position
	 */
	Position (){
		
	}
	
	/**
	 * Constructeur de position prenant les coordonnées x et y
	 * @param x int : Coordonnée x
	 * @param y int : Coordonnée y
	 */
	Position(int x, int y) { this.x = x; this.y = y; }
	
	/**
	 * Constructeur de position prenant le numéro de case
	 * @param num int : Numéro de la case
	 */
	Position (int num){
		x = num % IConfig.LARGEUR_CARTE;
		y = num / IConfig.LARGEUR_CARTE;
	}
	
	/**
	 * Renvoie la coordonnée x
	 * @return int Coordonnée x de la position
	 */
	public int getX() { return x; }
	
	/**
	 * Renvoie la coordonnée y
	 * @return int Coordonnée y de la position
	 */
	public int getY() { return y; }
	
	/**
	 * Affecte la coordonnée x
	 * @param x int : Nouvelle coordonnée x
	 */
	public void setX(int x) { this.x = x; }
	
	/**
	 * Affecte la coordonnée y
	 * @param y int : Nouvelle coordonnée y
	 */
	public void setY(int y) { this.y = y; }
	
	/**
	 * Verifie si une position est dans la carte : true si oui, false sinon
	 * @return boolean Valide=true, invalide=false
	 */
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) return false; else return true;
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return "A la position : ("+x+", "+y+")  "; 
	}
	
	/**
	 * Regarde si pos est voisine de la position courante
	 * @param pos Position : Positon à vérifier
	 * @return boolean Voisine=true, distante=false
	 */
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/**
	 * Renvoie le numéro de la case
	 * @return int Numéro de la case
	 */
	public int getNumCase(){
		return x + IConfig.LARGEUR_CARTE * y;
	}
	
	/**
	 * Méthode permettant de spécifier le numéro de case de la position. Met donc à jour x et y
	 * @param num int : Numéro de la case dans la carte
	 */
	public void setNumCase(int num){
		x = num % IConfig.LARGEUR_CARTE;
		y = num / IConfig.LARGEUR_CARTE;
	}
	
	
	/** 
	 * Méthode pour calculer la distance entre deux positions (en nombres de cases)
	 * CAS PARTICULIER: si la case est adjacente alors retourner 1
	 * @param pos Position : Position dont on veut connaître la distance à la position courante
	 * @return int Distance entre la position courante et pos ou si les positions sont adjacentes 
	 */
	
	public int distance(Position pos){
		if (this.getNumCase() == -1 || pos.getNumCase() == -1) return 0;
		
		int dx = Math.abs(this.x - pos.x);
		int dy = Math.abs(this.y - pos.y);

		if(dx == 1 && dy == 1)
			return 1;
        if (dx>dy){
        	return dx;
        }
        else { if (dy>dx){
        	return dy;
        	}
        else {
        	return dx ;
        }
        }
		
	}
	
	/**
	 * Méthode vérifiant si deux positions sont égales
	 * @param pos Position : Position à vérifier
	 * @return boolean Egales=true, inégales=false
	 */
	public boolean equals(Position pos){
		return ((pos.x == this.x) && (pos.y == this.y));  
	}
	
	
	
	
}