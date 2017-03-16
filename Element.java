package wargame;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.ImageIcon;

public abstract class Element implements IConfig, Serializable{

	private static final long serialVersionUID = 1L;
	/** Numéro de la case où se situe le soldat**/
	protected Position position;
	/**** La couleur de l'élément ****/
	protected Color couleur;
	protected Carte carte;
	protected boolean visible;
	/**** L'image de l'élément ****/
	protected ImageIcon image;
	
	/**
	 * Cosntructeur vide d'élément
	 */
	Element (){

	this.visible=false;
		
		
	}
	/**
	 * Renvoie la position de l'élément
	 * @return Position Position de l'élément
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * Change l'attribut position de l'élément
	 * @param position Position : Nouvelle position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Change l'attribut couleur de l'élément
	 * @param couleur Color : Nouvelle couleur
	 */
	public void setCouleur(Color couleur){
		this.couleur=couleur;
	}
	/**
	 * Renvoie la couleur de l'élément
	 * @return Color Couleur de l'élément
	 */
	public Color getCouleur(){
		return this.couleur;
	}
	/**
	 * Renvoie la visibilité de l'élément
	 * @return boolean Visible=true, invisible=false
	 */
	public boolean getVisible(){return this.visible;}
	/**
	 * Change l'attribut visibilité de l'élément
	 * @param v boolean : Nouvelle visibilité
	 */
	public void setVisible(boolean v){this.visible=v;}
	/**
	 * Change l'attribut image de l'élément
	 * @param image ImageIcon : Nouvelle image
	 */
	public void setImage(ImageIcon image)
	{
		this.image=image;
	}
	/**
	 * Renvoie l'image de l'élément
	 * @return ImageIcon Image de l'élément
	 */
	public ImageIcon getImage()
	{
		return this.image;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String info = "";
		if (visible == false)
		{
			return "Case cachée";
		}
		info += "" + this.getClass().getSimpleName();
		return info; 
	}
}
