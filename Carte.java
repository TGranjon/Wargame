package wargame;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Carte extends JPanel implements ICarte, ActionListener {
	
	private static final long serialVersionUID = -886888453873608666L;
	/** Monstres. */
	private ArrayList<Monstre> monstre;
	/** Héros. */
	private ArrayList<Heros> heros;
	/***** tous les obstacles de la carte****/
	private ArrayList<Obstacle> obstacles;
	/*** tous les elements de la carte***/
	public Element []elements;
	/** Table de jeu de la carte. */
	private Soldat []soldat;
	private boolean generee=false;
	private int tour;
	private int nbHerosRestant;
	private int nbMonstresRestant;
	private int nbSoldatAJouer; 
	/** Est-ce au tour de joueur ? */
	private boolean tourJoueur;
	/** Timer. */
	private Timer timer;
	/** Indique la case courante sélectionnée. Correspond également au soldat selectionné dans le combat. */
	private int caseCliquee = -1;
	private JLabel imageDebutJeu;
	
	public Carte() throws IOException {
	
		generer();
	 	//this.setPreferredSize(new Dimension (IConfig.LARGEUR_CARTE * IConfig.NB_PIX_CASE, 
		//		 IConfig.HAUTEUR_CARTE * IConfig.NB_PIX_CASE));
	 //	imageDebutJeu = new JLabel(new ImageIcon( this.getClass().getResource( "src/image_debut_jeu.png")));
		//this.add(imageDebutJeu);

		   addMouseListener(new MouseAdapter() {
		    	// Capture du clic sur la carte 
				public void mouseClicked(MouseEvent e) { 
					//On crée une position aux coordonnées du clic de la souris pour connaitre la coordonnée de la case (et non pas du pixel) 
					Position pos = new Position(e.getX() / IConfig.NB_PIX_CASE , e.getY() / IConfig.NB_PIX_CASE);
					int case_encours = pos.getNumCase();
					System.out.println("la case cliqueeeeee: "+case_encours);
					
					// On change de héros si la case n'est pas vide, qu'il s'agit bien d'un soldat et que le soldat est affiché. 
					if(elements[case_encours] != null 
						&& elements[case_encours] instanceof Heros 
						//&& elements[case_encours].estVisible() 
					) {
						caseCliquee = case_encours;
					}
					// Sinon on va faire une action en rapport avec le soldat sélectionné. /
					else {
						if(caseCliquee != -1) {
							//Distance entre la case cliquée et la case actionnée 
							int distance = new Position(caseCliquee).distance(new Position(case_encours));
							 try {
									
							//boolean dep = deplaceSoldat( new Position(case_encours),(Soldat)elements[caseCliquee]);
							boolean dep = actionHeros(new Position(caseCliquee),new Position(case_encours));
							System.out.println("deplacement  "+dep);
							System.out.println("a nouvelle case   "+elements[(new Position(case_encours)).getNumCase()].getCouleur());
							System.out.println("a nouvelle case   "+elements[(new Position(case_encours)).getNumCase()].getClass().getSimpleName());
							
							repaint();
							
							 } catch (Exception e1) {
									e1.printStackTrace();
								}
							 }
						
						caseCliquee=-1;
				}
					/*for (int i=0; i<elements.length;i++){
						System.out.println("lelement n°  "+i+"  "+elements[i].getClass().getSimpleName()+"de couleur  "+elements[i].getCouleur());
					}*/
					//repaint();
				}
				
			
		    });  
	 
	}
	/**
	 *  Méthode générant tous la carte 
	 */
	public void generer()
	{
		 generee = true;
		 System.out.println("la carte est généré");
		tourJoueur = true;
		nbMonstresRestant = IConfig.NB_MONSTRES;
		nbHerosRestant = IConfig.NB_HEROS;
		nbSoldatAJouer = nbHerosRestant;
		tour = 0;
		
		elements = new Element[IConfig.LARGEUR_CARTE*IConfig.HAUTEUR_CARTE];
		/* Mise à 0 de la carte. */
		for(int i = 0; i < IConfig.LARGEUR_CARTE * IConfig.HAUTEUR_CARTE; i++)
			{
		    
			elements[i] = null;
			
			
			}

		/* Mise à 0 de la carte. */
	/*	for(int i = 0; i < IConfig.LARGEUR_CARTE * IConfig.HAUTEUR_CARTE; i++){
			soldat[i] = null;
		}*/

		/* Tableaux de heros monstres et obstacles */
		heros = new ArrayList<Heros>(); 
		monstre = new ArrayList<Monstre>(); 
		obstacles  = new ArrayList<Obstacle>();

		     /********************* Chargement des heors de base. ***********/
		System.out.println("je suis dans les heors");
			for(int i = 0; i < IConfig.NB_HEROS; i++)
			{
				/* On trouve une position vide, on créé un héros et on le place */
	
		Position pos =new Position((int)Math.round((Math.random()*10)),(int)Math.round((Math.random()*IConfig.HAUTEUR_CARTE)));
	
		System.out.println(" les coordonnés de POS x= "+pos.getX()+"  y= "+pos.getY());
			System.out.println("le numéro de la case = " + pos.getNumCase());
		if ((pos.estValide()) ){
			if (elements[pos.getNumCase()]==null ) {
				//System.out.println("praticable  "+elements[pos.getNumCase()].estPraticable());
				Heros h = new Heros(this,ISoldat.TypesH.getTypeHAlea(),(char)('A'+i),pos);
				
				
			    heros.add(h);
			  
				elements[pos.getNumCase()] = (Heros)h;
				elements[pos.getNumCase()].setPraticable(false);
				elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_HEROS);
				
			
			}
			}
			}
			/**************Idem  pour monstres*************************** */
			System.out.println("je suis dans les monstres\n\n");
			for(int i = 0; i < IConfig.NB_MONSTRES; i++)
			{			
			Position pos =new Position((int)Math.round((Math.random()*10+15)),(int)Math.round((Math.random()*IConfig.HAUTEUR_CARTE)));
			if ((pos.estValide()) && (elements[pos.getNumCase()]==null ) ){
				System.out.println(" les coordonnés de POS x= "+pos.getX()+"  y= "+pos.getY());
				System.out.println("le numéro de la case = " + pos.getNumCase());
						
				Monstre m = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),(i+1),pos);
			
				monstre.add(m);
				elements[pos.getNumCase()] =(Monstre) m;
				
				elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_MONSTRES);
				elements[pos.getNumCase()].setPraticable(false);
				//System.out.println("la couleur de la case= " + elements[pos.getNumCase()].getCouleur());
				
				
				}	
			}
			/**************Idem  pour obstacles*************************** */
			System.out.println("je suis dans les obstacles\n\n");
			for (int i=0; i<IConfig.NB_OBSTACLES;i++){
				Position pos = this.trouvePositionVide(); 
				System.out.println(" les coordonnés de POS x= "+pos.getX()+"  y= "+pos.getY());
				System.out.println("le numéro de la case = " + pos.getNumCase());
				Obstacle o = new Obstacle (Obstacle.TypeObstacle.getObstacleAlea(),pos);
				

				obstacles.add(o);
			    elements[pos.getNumCase()]= (Obstacle)o;
				switch(o.getTypeObstacle()){
				case ROCHER : elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_ROCHER);  break;				           
				case FORET :  elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_FORET);;break;
				case EAU : elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_EAU);;break;
		
				}
			
			    elements[pos.getNumCase()].setPraticable(false);
			    System.out.println("la couleur de la case= " + elements[pos.getNumCase()].getCouleur());
				
			  
			    
				
			}
			
		/***************** LE RESTE CEST VIDE************/
			/****?????????????????????????***/
			for (int i=0; i <elements.length;i++){
				if (elements[i]==null){
					elements[i]= new Vide();
					Position pos = new Position();
							pos.setNumCase(i);
					elements[i].setPosition(pos);;
					elements[i].setCouleur(IConfig.COULEUR_VIDE);
				
				}
			}
			genererInconnu();
		
			caseCliquee= -1;
			if(imageDebutJeu != null){
				imageDebutJeu.getParent().remove(imageDebutJeu);
				imageDebutJeu = null;
			}
			
}
	
	
/************ généère les cases qui sont inconuus au début du jeu*********/
public void genererInconnu(){
		
			for(int j = 0; j < heros.size(); j++) {
				Heros h = heros.get(j);
				changeVisibilite(h.getPosition(),h.getPortee(),true);
			}
		
}
/*** methode qui change la visiblité d'une case du jeu autour d'un soldat dans une position pos
 
 */
public void changeVisibilite (Position pos, int portee, boolean visible){
	int x,y;
	for (x=portee; x>= -portee; x--){
		for (y=portee; y>= -portee; y--){
			Position tmp = new Position (pos.getX()+x,pos.getY()+y);
			  if (tmp.estValide()){
				  getElement(tmp).setVisible(visible);
				  
			  }
		}
		
	}
}
	
public void affichage(){
	
	System.out.println("l'affichage des heros");
	for (int i=0; i <heros.size();i++){
		
		switch(heros.get(i).getTypeHero()){
		case HUMAIN : System.out.println("c'est un humain" );
		System.out.println("nombre de vies "+ heros.get(i).getTypeHero().getPoints() );
		System.out.println("portee visuelle "+ heros.get(i).getTypeHero().getPortee() );
		break;
		case NAIN : System.out.println("c'est un NAIN" );break;
		case ELF : System.out.println("c'est un ELF" );break;
		case HOBBIT : System.out.println("c'est un HOBBIT" );break;
		}
		
	}
}

	
/***** retorune l'element de la case avec les coordonés de pos***/
	public Element getElement(Position pos) {
		return elements[pos.getNumCase()];
	}
/******* retourne l'indice du hero portant le nom 'nom' dans la liste des heros****/ 
	public int getHeroList (char nom){
		int i;
		for ( i=0; i<heros.size();i++){
			if ((heros.get(i).getNom())==nom){
				break;
				
			}
		}
		return i ;
	}
	/******* retourne l'indice du monstre portant le numéro 'num' dans la liste des monstres****/ 
public int getMonstreList (int num){
	int i ; 
	for (i=0 ; i< monstre.size();i++){
		if (monstre.get(i).getNumero()==num){
			break;
		}
	}
	return i;
}
	
	/** Trouve aléatoirement une position vide sur la carte
	/****** ATTENTION METHODE UTILISEE QUE POUR LA GENERATION DE LA CARTE AU DEBUT DU JEU !!!!*****/	
	public Position trouvePositionVide() {
		int fin= 0; Position pos=null;
	while (fin!=1) {
		 pos =new Position((int)Math.round((Math.random()*IConfig.LARGEUR_CARTE)),(int)Math.round((Math.random()*IConfig.HAUTEUR_CARTE)));
	if ((pos.estValide()) && (elements[pos.getNumCase()]==null ) ){
		fin = 1;
		
		}
		}
	return pos;
}
	
	// Trouve une position vide choisie
	// aléatoirement parmi les 8 positions adjacentes de pos
	/*********** return NULL si aucune position trouvé autour (il reste immobile ************/
	public Position trouvePositionVide(Position pos) {
		Position positions[] = new Position[9];
		Position temp = new Position();
		
		int nb_position_trouve = 0;
		
		/* On parcourt toutes les positions possibles autour du soldat */
		for(int x = -1; x <= 1; x++)
			for(int y = -1; y <= 1; y++)							
			{					
				temp.setX(pos.getX() + x) ;
				temp.setY(pos.getY() + y)  ;
				
				/* Si la position est valide (dans la carte)
				 * Et si il n'y a pas déjà de soldat sur cette position
				 * Et si la case est praticable A AJOUTER PLUS TARD 
				 */
				if (temp.estValide()) /**** peut etre a modifier****/
				{
					if ( getElement(temp) instanceof Vide){
					positions[nb_position_trouve] = new Position(temp.getX(),temp.getY());
					nb_position_trouve++;
					}
				}
			}
		
		/* Si on a trouvé des positions valides, on en choisit une au hasard */
		if(nb_position_trouve > 0)
			temp = positions[(int)Math.round(Math.random()*(nb_position_trouve-1))];
		else
			temp = null;
		
		return temp;
		
	}

	
	public Heros trouveHeros(Monstre mstr) {
		Heros h=null;
		int portee = mstr.getTypeMonstre().getPortee();
		Position pos= mstr.getPosition();
		Position temp=new Position();
		for(int x = -portee; x <= portee; x++)
			for(int y = -portee; y <= portee; y++)							
			{					
				temp.setX(pos.getX() + x) ;
				temp.setY(pos.getY() + y)  ;
				
				/* Si la position est valide (dans la carte)
				 * Et s'il y a un hero a cette position
				 *
				 */
				if (temp.estValide()) /**** peut etre a modifier****/
				{
					if ( getElement(temp) instanceof Heros){
					h= (Heros)getElement(temp);
					}
				}
			}
		return h;
	}
	
	public Heros trouveHeros(Position pos) {
		Heros h[] = new Heros[9];
		Position temp = new Position();
		
		int nb_position_trouve = 0;
		for(int x = -1; x <= 1; x++)
			for(int y = -1; y <= 1; y++)							
			{					
				temp.setX(pos.getX() + x) ;
				temp.setY(pos.getY() + y)  ;
				
				/* Si la position est valide (dans la carte)
				 
				 */
				if (temp.estValide()) /**** peut etre a modifier****/
				{
					if ( getElement(temp) instanceof Heros){
					h[nb_position_trouve] = (Heros)getElement(temp);
					nb_position_trouve++;
					}
				}
			}
		if(nb_position_trouve > 0)
			return( h[(int)Math.round(Math.random()*(nb_position_trouve-1))]);
		else
			return null;
		
		
	}
	
	/*** retourne vrai si le soldat a été déplacé en mettant a jour la carte (elements[]) 
	 * mettre a jour arrayList des heros et monstres
	 * retourne faux si la position n'est pas valide ou n'est pas adjacente
	 
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
	
			if ((pos.estValide()) && (pos.distance(soldat.getPosition())==1)){ // pos adjacente et valide
				soldat.setPosition(pos); // mettre a jour la position du soldat
				soldat.setAJoue(true);
				// mettre a jour les arrayList 
				if (soldat instanceof Heros){ // si le soldat est un hero
					int i = getHeroList (((Heros) soldat).getNom());
					heros.add(i,(Heros)soldat); // je remplace l'ancien hero par le nouveau 
				}else { 
					if (soldat instanceof Monstre){
						int i = getMonstreList (((Monstre) soldat).getNumero());
						monstre.add(i,(Monstre)soldat); // je remplace l'ancien monstre par le nouveau 
					}
				}
				
				
				 elements[soldat.getPosition().getNumCase()]=new Vide(); // la case du soldat déplacé est remise a vide
				 elements[soldat.getPosition().getNumCase()].setPosition(soldat.getPosition());
				// System.out.println	("le type de la case" +elements[soldat.getPosition().getNumCase()].getClass().getTypeName());
				 elements[soldat.getPosition().getNumCase()].setCouleur(Color.WHITE);
				 System.out.println("la couleur dans la  case "+elements[soldat.getPosition().getNumCase()].getCouleur());
					elements[pos.getNumCase()]= soldat; // mettre a jour la nouvelle case ou se trouve le soldat
				elements[pos.getNumCase()].setPosition(pos);
					System.out.println("la couleur dans la nouvelle case "+elements[pos.getNumCase()].getCouleur());
				 
				return true;
			}else { // la position nest pas valide
				return false;
			}
   }
	
	/****le joueur donne l’ordre au héros situé à la position pos d’agir à la position pos2.
	 *  Le héros s’y déplace si la position est vide en mettant a jour les inconnus (portée visuelle)
	 *   ou combat le monstre qui s’y trouve éventuellement.
	 *    La méthode retourne false s’il n’y a pas de héros à la position pos,
	 *     si pos2 n’est pas une position adjacente, 
	 *     s’il y a déjà un héros à la position pos2, ou si le héros a déjà joué son tour.
	 */
public boolean actionHeros(Position pos, Position pos2) {
	
		if (getElement(pos) instanceof Heros){
			System.out.println("cest un hero");
			Heros h = (Heros)getElement(pos);
			if (getElement(pos2) instanceof Vide){ // si la pos2 est vide le soldat s'y déplace
				System.out.println("la position deux est vide");
				boolean temp = deplaceSoldat(pos2,h);  // je déplace le soldat 
				System.out.println("le bool dans la methode "+temp);
				if (temp==true){ // le soldat a été déplacé ==> mettre a jour les inconnus 
				
					changeVisibilite(pos,h.getPortee(),false); //mettre a jour autour de l'ancienne position 
					 // mettre a jour autour des positions
					genererInconnu();
					return true;
					
				}
				else{ // la position pos2 n'est pas  adjacente/valide 
					return false;
				}
			}
			else { // pos2 Nest pas vide donc c'est soit un monstres (dnc combat) ou obstacle
				if (getElement(pos) instanceof Monstre){ // s'il ya un monstre alors combat
					Soldat s = (Heros)getElement(pos);
					s.combat((Soldat)getElement(pos2));
					return true;
				}
				else{ // c'est un obtacle aucune action possible
					return false;
				}
			}
		}
		else{// il n ya pas de heros a la postion 'pos'
		
		return false;
		}
	}
	
	public void mort(Soldat perso) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void jouerSoldats(PanneauJeu pj) {
		// TODO Auto-generated method stub
		
	}
	
	public void toutDessiner(Graphics g) {
	
		for (int i=0;i<elements.length;i++){
			if (elements[i].getVisible()==true){
			System.out.println("la case numero "+i +" contient un " +elements[i].getClass().getSimpleName());
			dessineRectangle(g,elements[i].getPosition().getX(),elements[i].getPosition().getY(),elements[i].getCouleur());
			
			}
			else {
				System.out.println("la case numero "+i +" contient un " +elements[i].getClass().getSimpleName());
				
				dessineRectangle(g,elements[i].getPosition().getX(),elements[i].getPosition().getY(),IConfig.COULEUR_INCONNU);
				
			}
		}
	
	}
	
	public void actionMonstres() {
		boolean repos; /********a utiliser eventuellement plus tard pr le repos d'un monstre***/
		for (int i=0; i<monstre.size();i++){
			Heros h = trouveHeros(monstre.get(i)); // chercher un hero a la portee du monstre
			if (h!=null){  // il y a un heros a sa portée dnc il l'attaque
				h.setVie(h.getVie()-monstre.get(i).getTypeMonstre().getTir()); // mettre a jour le nb de vies du hero attaqué
			/******* il faut tester s'il est mort pour le supprimer de la carte************/	
			}
			else { // aucun soldat a sa portée donc il se déplace
				Position pos= trouvePositionVide(monstre.get(i).getPosition());
				if (pos != null ){ // position adjacente trouvé dnc déplacement
					repos = deplaceSoldat (pos,monstre.get(i)); // on déplace le monstre
				}
				/************ rajouter le cas ou apres le déplacement, il ya un hero a coté DONC COMBAT***/
			}
			
		}
	}
	
	protected void dessineRectangle(Graphics g, int x, int y, Color c) 
	{
		g.setColor(c);
		g.fillRect(x * IConfig.NB_PIX_CASE, y * IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE);
		
	}
	protected void paintComponent(Graphics g) 
	{   
		
	super.paintComponent(g);
		if(!generee)
			return;
	//	toutDessiner( g);
		if (caseCliquee==-1){
		for (int i=0;i<elements.length;i++){
			if (elements[i].getVisible()==true){
			System.out.println("la case numero "+i +" contient un " +elements[i].getClass().getSimpleName());
			
				System.out.println("ok meerde !!! ");
				System.out.println("LA POSITION!!! "+elements[i].getPosition().toString());
			dessineRectangle(g,elements[i].getPosition().getX(),elements[i].getPosition().getY(),elements[i].getCouleur());
			
			
			}
			else {
				System.out.println("la case numero "+i +" contient un " +elements[i].getClass().getSimpleName());
				
				dessineRectangle(g,elements[i].getPosition().getX(),elements[i].getPosition().getY(),IConfig.COULEUR_INCONNU);
				
			}
			
		}
		}
		
		/*if (caseCliquee!=-1){
			System.out.println("casecliqueeeeeeeeeeeeee");
			Position coord = new Position(caseCliquee);
			int dx = coord.getX();
			int dy = coord.getY();
			if(elements[caseCliquee] instanceof Heros) {
				
					dessineRectangle(g, dx, dy, IConfig.COULEUR_HEROS_DEJA_JOUE);
			}else {
				for(int i = -1; i <= 1; i++) {
					for(int j = -1; j <= 1; j++) {
						int dxc = dx + i;
						int dyc = dy + j;
						int caseVoisine = dyc * IConfig.LARGEUR_CARTE + dxc;
	
						if(new Position(caseVoisine).estValide() && elements[caseVoisine] instanceof Vide)
							dessineRectangle(g, dxc, dyc, IConfig.COULEUR_HEROS);
						
					}
				}
	System.out.println("merde");
				dessineRectangle(g, dx, dy, IConfig.COULEUR_HEROS);
			
			}
			
		}*/
		 
		
	
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
	
		
	}
	

}
