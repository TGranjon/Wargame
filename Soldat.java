package wargame;

 

public abstract class Soldat extends Element implements ISoldat {
	/** Vie d'un soldat. */
	private int vie; // c'est les points de vie restants 	

	/** Est mort ? */
	private boolean mort = false;	
	/** En train de se deplacer. */
	private boolean seDeplace = false;	
	/** Le tour est effectué */
	private boolean tourEffectue = false;
	
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR ,PORTEE_VISUELLE;
	
    public Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos) {
    	POINTS_DE_VIE_MAX=vie = pts;
    	PUISSANCE=puiss;
    	TIR= tir;
    	PORTEE_VISUELLE=portee;
    	this.carte=carte;
    	this.position=pos;
    	
    }
    
	public void joueTour(int tour){}
	
	public void combat(Soldat soldat)
	{
		soldat.vie -= this.PUISSANCE;	
	}
	
	public void seDeplace(Position newPos){
		
	}
	
	public void repos()
	{
		if(this.vie<this.POINTS_DE_VIE_MAX)
		{
			this.vie += (10*this.POINTS_DE_VIE_MAX)/100; /*Le repos rend 10% des PV max*/
			if(this.vie>this.POINTS_DE_VIE_MAX)
			{
				this.vie=this.POINTS_DE_VIE_MAX;
			}
		}
	}

	
	
	public int getVie() 
	{
		return vie;
	}
	public void setVie(int vie) 
	{
		this.vie = ((vie > this.getVieMax()) ? this.getVieMax() : (vie < 0 ) ? 0 : vie);
	}
	/*********** a revoir ***********/
	public boolean enVie()

    {
        if (this.getVie() > 0) return true;
        else {
            //si mort destruction personnage
            return false;
        }
    }
	public void setSeDeplace(boolean b)
	{
		this.seDeplace = b;
	}
	public boolean getSeDeplace()
	{
		return this.seDeplace;
	}
	public boolean estMort() {
		return mort;
	}
	public boolean getAJoue() {
		return tourEffectue;
		
	}	
	
	public void setAJoue(boolean v) {
		tourEffectue = v;
	}
	

	public void setMort(boolean mort) {
		if(mort){
			
		}
		else{
			this.mort = false;
		}
	}




	
	
	

}
