package wargame;

import java.io.Serializable;

/**
 * @author Thomas GRANJON
 * @author Axel SOFONEA
 * @author Sarah RIGHI
 *
 */

public class Monstre extends Soldat implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TypesM typeMonstre;
	private final int NUMERO;
	
	/**
	 * Constructeur de monstre
	 * @param carte Carte : Carte dans laquelle on joue
	 * @param type_monstre TypesM : Type du monstre
	 * @param num int : Numéro du monstre
	 * @param pos Position : Position du monstre
	 */
	Monstre(Carte carte, TypesM type_monstre,int num, Position pos) 
	{
		super(carte,type_monstre.getPoints(),type_monstre.getPortee(),type_monstre.getPuissance(),type_monstre.getTir(),pos);
		
		
		this.typeMonstre = type_monstre;
		NUMERO = num;
		
		
	}
	/**
	 * Renvoie le type du monstre
	 * @return TypesM Type du monstre
	 */
	public TypesM getTypeMonstre(){
		return typeMonstre;
	}

	/* (non-Javadoc)
	 * @see wargame.ISoldat#getPortee()
	 */
	public int getPortee(){
		return typeMonstre.getPortee();
	}
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getPuissance()
	 */
	public int getPuissance(){
		return typeMonstre.getPuissance();
	}
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getTir()
	 */
	public int getTir(){
		return typeMonstre.getTir();
	}
	
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getVieMax()
	 */
	public int getVieMax(){
		return typeMonstre.getPoints();
	}
	/**
	 * Renvoie le numéro du monstre
	 * @return int Numéro du monstre
	 */
	public int getNumero(){return this.NUMERO;}
	/* (non-Javadoc)
	 * @see wargame.Element#toString()
	 */
	public String toString() 
	{
		if (visible == false)
		{
			return "Case cachée";
		}
		return ""+getTypeMonstre() +" "+this.getNumero()+ " vie: " + this.getVie() + " puissance: " + this.getPuissance() + " tir: " + this.getTir(); 
	}
}


