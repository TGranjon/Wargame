package wargame;

import java.io.Serializable;

/**
 * @author Thomas GRANJON
 * @author Axel SOFONEA
 * @author Sarah RIGHI
 *
 */

public abstract class Soldat extends Element implements ISoldat , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Vie d'un soldat. */
	private int vie; // c'est les points de vie restants 	
	/** Est mort ? */
	private boolean mort = false;	
	/** Le tour est effectué */
	private boolean tourEffectue = false;
	private boolean repos=false;
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR ,PORTEE_VISUELLE;
	
	/**
     * Constructeur de soldat
     * @param carte Carte : Carte dans laquelle on joue
     * @param pts int : Points de vie maximaux du soldat
     * @param portee int : Portée du soldat
     * @param puiss int : Puissance au corps à corps du soldat
     * @param tir int : Puissance de tir du soldat
     * @param pos Position : Position du soldat
     */
    public Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos) {
    	super();
    	POINTS_DE_VIE_MAX=vie = pts;
    	PUISSANCE=puiss;
    	TIR= tir;
    	PORTEE_VISUELLE=portee;
    	this.carte=carte;
    	this.position=pos;
    	
    }
    
    /**
     * Permet aux soldats de se reposer
     */
 public void repos(){
	
				this.vie += (10*this.POINTS_DE_VIE_MAX)/100; /*Le repos rend 10% des PV max*/
				if(this.vie>this.POINTS_DE_VIE_MAX)
				{
					this.vie=this.POINTS_DE_VIE_MAX;
				}
			
 }

 /* (non-Javadoc)
  * @see wargame.ISoldat#combat(wargame.Soldat)
  */
	public	int  combat(Soldat soldat){
	
		int retour = 0; int degat =-1;
		int d = soldat.getPosition().distance(this.getPosition());
		if (d==1){
			/**** J'attaque le défenseur***/
			degat = (int)(Math.round((Math.random())*this.getPuissance()));
			int vie = soldat.getVie() - degat;
			soldat.setVie(vie);
			
			carte.elements[soldat.getPosition().getNumCase()]=soldat;
			this.setAJoue(true);
			carte.elements[this.getPosition().getNumCase()]=this;		
			
		  /****** Le défenseur n'est pas mort : RIPOSTE***/
			if (vie >0){
				
					Soldat s = (Soldat) carte.elements[this.getPosition().getNumCase()];
					s.setVie(this.getVie()-soldat.getPuissance());
					carte.elements[this.getPosition().getNumCase()]=s;
			}		
		}
		else {
			if (!carte.obstacleEntreCase(this.getPosition(),soldat.getPosition())) {
				if (this.getTir()>=d){
				degat=this.getTir();
				int vie = soldat.getVie() - degat;
				soldat.setVie(vie);
				carte.elements[soldat.getPosition().getNumCase()]=soldat;
				this.setAJoue(true);
				carte.elements[this.getPosition().getNumCase()]=this;	
				}else {
					if (this instanceof Heros){
					carte.messageSoldatLoin();
					}
				}
			}else {
				if (this instanceof Heros){
				carte.messageObstacle();
				}
			}
			
		}
		
		if (this.getVie()<=0){
			retour=-1;
			this.mort=true;
			carte.mort(this);
		}
		if (soldat.getVie()<=0){
			retour=1;
			soldat.mort=true;
			carte.mort(soldat);
		}
	
		return retour;
	}
			
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getVie()
	 */
	public int getVie() 
	{
		return vie;
	}
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#setVie(int)
	 */
	public void setVie(int vie) 
	{
		this.vie = ((vie > this.getVieMax()) ? this.getVieMax() : (vie < 0 ) ? 0 : vie);
	}
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#getPuissance()
	 */
	public int getPuissance() {
		return PUISSANCE;
	}

	/* (non-Javadoc)
	 * @see wargame.ISoldat#getTir()
	 */
	public int getTir() {
		return TIR;
	}

	/**
	 * Récupère la portée visuelle d'un soldat
	 * @return int Portée visuelle du soldat
	 */
	public int getPorteeVisuelle() {
		return PORTEE_VISUELLE;
	}

	/**
	 * Vérifie si le soldat est mort
	 * @return boolean Mort=true, vivant=false
	 */
	public boolean estMort() {
			return mort;
	}
	
	/**
	 * Vérifie si le soldat à déja joué
	 * @return boolean A déja joué=true, n'a pas encore joué=false
	 */
	public boolean getAJoue() {
		return tourEffectue;
			
	}	
		
	/* (non-Javadoc)
	 * @see wargame.ISoldat#setAJoue(boolean)
	 */
	public void setAJoue(boolean v) {
		tourEffectue = v;
	}
		
	
	/* (non-Javadoc)
	 * @see wargame.ISoldat#setMort(boolean)
	 */
	public void setMort(boolean mort) {
		this.mort = mort;		
	}
	
	/**
	 * Change l'attribut repos du soldat
	 * @param r boolean : Se repose=true, ne se repose pas=false
	 */
	public void setRepos(boolean r){
		this.repos =r;
	}
	
	/**
	 * Vérifie si le soldat se repose
	 * @return boolean Se repose=true, ne se repose pas=false
	 */
	public boolean getRepos(){
		return this.repos;
	}

}
