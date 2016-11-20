package wargame;

import java.awt.Color;

public class Soldat extends Element{
	
	public enum TypeSoldat {
		HEROS (COULEUR_HEROS), MONSTRES (COULEUR_MONSTRES), HEROS_DEJA_JOUE (COULEUR_HEROS_DEJA_JOUE);
		private final Color COULEUR;
		TypeSoldat(Color couleur) { COULEUR = couleur; }
	}
	private TypeSoldat TYPE;
	public Soldat(){}
    public Soldat(TypeSoldat type, Position pos) { super(); TYPE = type; this.pos = pos; }    
	public void joueTour(int tour){}
	public void combat(Soldat soldat){}
	public void seDeplace(Position newPos){}
	public String toString() { return ""+TYPE; }

}
