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
	 * Constructeur de position prenant les coordonn�es x et y
	 * @param x int : Coordonn�e x
	 * @param y int : Coordonn�e y
	 */
	Position(int x, int y) { this.x = x; this.y = y; }
	
	/**
	 * Constructeur de position prenant le num�ro de case
	 * @param num int : Num�ro de la case
	 */
	Position (int num){
		x = num % IConfig.LARGEUR_CARTE;
		y = num / IConfig.LARGEUR_CARTE;
	}
	
	/**
	 * Renvoie la coordonn�e x
	 * @return int Coordonn�e x de la position
	 */
	public int getX() { return x; }
	
	/**
	 * Renvoie la coordonn�e y
	 * @return int Coordonn�e y de la position
	 */
	public int getY() { return y; }
	
	/**
	 * Affecte la coordonn�e x
	 * @param x int : Nouvelle coordonn�e x
	 */
	public void setX(int x) { this.x = x; }
	
	/**
	 * Affecte la coordonn�e y
	 * @param y int : Nouvelle coordonn�e y
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
	 * @param pos Position : Positon � v�rifier
	 * @return boolean Voisine=true, distante=false
	 */
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/**
	 * Renvoie le num�ro de la case
	 * @return int Num�ro de la case
	 */
	public int getNumCase(){
		return x + IConfig.LARGEUR_CARTE * y;
	}
	
	/**
	 * M�thode permettant de sp�cifier le num�ro de case de la position. Met donc � jour x et y
	 * @param num int : Num�ro de la case dans la carte
	 */
	public void setNumCase(int num){
		x = num % IConfig.LARGEUR_CARTE;
		y = num / IConfig.LARGEUR_CARTE;
	}
	
	
	/** 
	 * M�thode pour calculer la distance entre deux positions (en nombres de cases)
	 * CAS PARTICULIER: si la case est adjacente alors retourner 1
	 * @param pos Position : Position dont on veut conna�tre la distance � la position courante
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
	 * M�thode v�rifiant si deux positions sont �gales
	 * @param pos Position : Position � v�rifier
	 * @return boolean Egales=true, in�gales=false
	 */
	public boolean equals(Position pos){
		return ((pos.x == this.x) && (pos.y == this.y));  
	}
	
	
	
	
}