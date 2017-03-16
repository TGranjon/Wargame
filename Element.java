package wargame;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.ImageIcon;

public abstract class Element implements IConfig, Serializable{

	private static final long serialVersionUID = 1L;
	/** Num�ro de la case o� se situe le soldat**/
	protected Position position;
	/**** La couleur de l'�l�ment ****/
	protected Color couleur;
	protected Carte carte;
	protected boolean visible;
	/**** L'image de l'�l�ment ****/
	protected ImageIcon image;
	
	/**
	 * Cosntructeur vide d'�l�ment
	 */
	Element (){

	this.visible=false;
		
		
	}
	/**
	 * Renvoie la position de l'�l�ment
	 * @return Position Position de l'�l�ment
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * Change l'attribut position de l'�l�ment
	 * @param position Position : Nouvelle position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Change l'attribut couleur de l'�l�ment
	 * @param couleur Color : Nouvelle couleur
	 */
	public void setCouleur(Color couleur){
		this.couleur=couleur;
	}
	/**
	 * Renvoie la couleur de l'�l�ment
	 * @return Color Couleur de l'�l�ment
	 */
	public Color getCouleur(){
		return this.couleur;
	}
	/**
	 * Renvoie la visibilit� de l'�l�ment
	 * @return boolean Visible=true, invisible=false
	 */
	public boolean getVisible(){return this.visible;}
	/**
	 * Change l'attribut visibilit� de l'�l�ment
	 * @param v boolean : Nouvelle visibilit�
	 */
	public void setVisible(boolean v){this.visible=v;}
	/**
	 * Change l'attribut image de l'�l�ment
	 * @param image ImageIcon : Nouvelle image
	 */
	public void setImage(ImageIcon image)
	{
		this.image=image;
	}
	/**
	 * Renvoie l'image de l'�l�ment
	 * @return ImageIcon Image de l'�l�ment
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
			return "Case cach�e";
		}
		info += "" + this.getClass().getSimpleName();
		return info; 
	}
}
