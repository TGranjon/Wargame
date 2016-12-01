package wargame;

import wargame.ISoldat.TypesH;

public class Monstre extends Soldat {
	private TypesM typeMonstre;
	private final int NUMERO;
	
   /* private static int num_monstre = 0;
    private Carte carte;
    private ISoldat.TypesM typeM;
    private int name =38;
    
	public Monstre(Carte carte,ISoldat.TypesM typeM,int name,Position pos) {
		num_monstre++;
		this.carte=carte;
		this.typeM=typeM;
		this.name=name;
		this.pos=pos;		
	}*/
	Monstre(Carte carte, TypesM type_monstre,int num, Position pos) 
	{
		super(carte,type_monstre.getPoints(),type_monstre.getPortee(),type_monstre.getPuissance(),type_monstre.getTir(),pos);
		
		
		this.typeMonstre = type_monstre;
		NUMERO = num;
		
		
	}
	public TypesM getTypeMonstre(){
		return typeMonstre;
	}

	public int getPortee(){
		return typeMonstre.getPortee();
	}
	
	public int getPuissance(){
		return typeMonstre.getPuissance();
	}
	
	public int getTir(){
		return typeMonstre.getTir();
	}
	
	
	
	public int getVieMax(){
		return typeMonstre.getPoints();
	}
	public int getNumero(){return this.NUMERO;}
	
	public String toString() 
	{
		return "il y a un "+getTypeMonstre() + ", vie : " + this.getVie() + ", puissance : " + this.getPuissance() + ", tir : " + this.getTir(); 
	}
}


