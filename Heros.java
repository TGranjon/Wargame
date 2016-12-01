package wargame;

public class Heros extends Soldat {
	public TypesH typeHeros;
	private final char NOM; // a changer plus tard en String
	Heros(Carte carte,TypesH type_heros,char  nom,Position pos) 
	{
	super(carte,type_heros.getPoints(),type_heros.getPortee(),type_heros.getPuissance(),type_heros.getTir(),pos);
			
		typeHeros = type_heros;
		NOM= nom;
		
		
	}
	public int getPortee(){
		return typeHeros.getPortee();
	}
	
	public int getPuissance(){
		return typeHeros.getPuissance();
	}
	
	public int getTir(){
		return typeHeros.getTir();
	}
	
	
	public int getVieMax(){
		return typeHeros.getPoints();
	}
	public TypesH getTypeHero(){
		return typeHeros;
	}
	public char getNom(){return this.NOM;}
	
	public String toString() 
	{
		return "il y a un "+getTypeHero() + ", vie : " + this.getVie() + ", puissance : " + this.getPuissance() + ", tir : " + this.getTir(); 
	}
	
}