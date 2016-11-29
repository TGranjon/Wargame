package wargame;

 
import java.awt.Color;

public class Obstacle extends Element {

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
	public TypeObstacle getTypeObstacle (){ return this.TYPE;}
	
	Obstacle(TypeObstacle type, Position pos) { TYPE = type; this.position = pos; }
	public String toString() { return ""+TYPE; }
}