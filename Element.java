package wargame;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Element implements IConfig{

	/** Numéro de la case où se situe le soldat. */
	protected Position position;
	/** Praticabilité. Une personne peut-elle marcher dessus ? */
	public boolean praticable;
	/**** la couleur de l'element****/
	protected Color couleur;
	protected Carte carte;
	protected boolean visible;
	


	
	
	
	Element (){
	this.praticable=true;
	this.visible=false;
		
		
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public boolean estPraticable()
	{
		return praticable;
	}
	public void setPraticable(boolean praticable)
	{
		this.praticable = praticable;
	}
	public void setCouleur(Color couleur){
		this.couleur=couleur;
	}
	public Color getCouleur(){
		return this.couleur;
	}
	public boolean getVisible(){return this.visible;}
	public void setVisible(boolean v){this.visible=v;}


}
