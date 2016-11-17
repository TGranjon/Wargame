package wargame;

import java.awt.Color;

public abstract class Element implements IConfig{

	protected Position pos;
	public enum TypeElement {
		VIDE(COULEUR_VIDE), INCONNU(COULEUR_INCONNU);
		private final Color COULEUR;
		TypeElement(Color couleur) { COULEUR = couleur; }
	}
	private TypeElement TYPE;
	public Element(){}
	public Element(TypeElement type, Position pos) 
	{ 
		TYPE = type;
		this.pos = pos;
	}
	public String toString() { return ""+TYPE; }
}
