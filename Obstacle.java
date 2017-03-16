package wargame;

import java.awt.Color;
import java.io.Serializable;

/**
 * @author Thomas GRANJON
 * @author Axel SOFONEA
 * @author Sarah RIGHI
 *
 */

public class Obstacle extends Element implements Serializable  {

	private static final long serialVersionUID = 1L;
	public enum TypeObstacle {
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
		private final Color COULEUR;
		public Color getCouleur(){return this.COULEUR;}
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	private TypeObstacle TYPE;
	/**
	 * Renvoie le type de l'obstacle
	 * @return TypeObstacle Type de l'obstacle
	 */
	public TypeObstacle getTypeObstacle (){ return this.TYPE;}
	
	/**
	 * Constructeur d'obstacle
	 * @param type TypeObstacle : Type de l'obstacle
	 * @param pos Position : Position de l'obstacle
	 */
	Obstacle(TypeObstacle type, Position pos) { TYPE = type; this.position = pos; }
	/* (non-Javadoc)
	 * @see wargame.Element#toString()
	 */
	public String toString() { return ""+TYPE; }
}