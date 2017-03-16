package wargame;

import java.io.Serializable;

public class Heros extends Soldat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public TypesH typeHeros;
	private final char NOM;
	/**
	 * Constructeur de h�ros
	 * @param carte Carte : Carte dans laquelle on cr�e le h�ros
	 * @param type_heros TypesH : Type du h�ros
	 * @param nom char : Nom du h�ros
	 * @param pos Position : Position du h�ros
	 */
	Heros(Carte carte,TypesH type_heros,char nom,Position pos) 
	{
	super(carte,type_heros.getPoints(),type_heros.getPortee(),type_heros.getPuissance(),type_heros.getTir(),pos);
			
		typeHeros = type_heros;
		NOM= nom;
		
		
	}
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getPortee()
	 */
	public int getPortee(){
		return typeHeros.getPortee();
	}
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getPuissance()
	 */
	public int getPuissance(){
		return typeHeros.getPuissance();
	}
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getTir()
	 */
	public int getTir(){
		return typeHeros.getTir();
	}
	
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getVieMax()
	 */
	public int getVieMax(){
		return typeHeros.getPoints();
	}
	/**
	 * Renvoie le type du h�ros
	 * @return TypesH Type du h�ros
	 */
	public TypesH getTypeHero(){
		return typeHeros;
	}
	/**
	 * Renvoie le nom du h�ros
	 * @return char Nom du h�ros
	 */
	public char getNom(){return this.NOM;}
	
	/* (non-Javadoc)
	 * @see wargame.Element#toString()
	 */
	public String toString() 
	{
		if (visible == false)
		{
			return "Case cach�e";
		}
		return ""+getTypeHero() +" "+this.getNom()+ " vie: " + this.getVie() + " puissance: " + this.getPuissance() + " tir: " + this.getTir(); 
	}
}