package wargame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseMotionListener;

/**
 * @author Thomas GRANJON
 * @author Axel SOFONEA
 * @author Sarah RIGHI
 *
 */

public class Carte extends JPanel implements ICarte, ActionListener, Serializable {
	
	private static final long serialVersionUID = -886888453873608666L;
	/** Monstres. */
	private ArrayList<Monstre> monstre;
	/** Héros. */
	private ArrayList<Heros> heros;
	/***** tous les obstacles de la carte****/
	private ArrayList<Obstacle> obstacles;
	/*** tous les elements de la carte***/
	public Element []elements;
	public boolean generee=false;
	public int nbHerosRestant;
	public int nbMonstresRestant;
	private int nbSoldatAJouer; 
	/** Est-ce au tour de joueur ? */
	public boolean tourJoueur=false;
	/** Indique la case courante sélectionnée. Correspond également au soldat selectionné dans le combat. */
	private int caseCliquee = -1;
	private JLabel imageDebutJeu;
	JOptionPane jop1, jop2, jop3;

	/**
	 * Constructeur de la carte
	 * @throws IOException
	 */
public Carte() throws IOException {	
	imageDebutJeu = new JLabel(new ImageIcon( this.getClass().getResource(IConfig.CHEMIN+"Menu.png")));
	imageDebutJeu.setPreferredSize(new Dimension (IConfig.LARGEUR_CARTE * IConfig.NB_PIX_CASE, IConfig.HAUTEUR_CARTE * IConfig.NB_PIX_CASE));
	this.add(imageDebutJeu);
	jouerSoldats();
	
}

	
/**
 * Méthode générant toute la carte
 */
public void generer()
{
	System.out.println(getClass().getResource("/."));
	 generee = true;
	tourJoueur = true;
	nbMonstresRestant = IConfig.NB_MONSTRES;
	nbHerosRestant = IConfig.NB_HEROS;		
	nbSoldatAJouer = nbHerosRestant;		
	elements = new Element[IConfig.LARGEUR_CARTE*IConfig.HAUTEUR_CARTE];
	/* Mise à 0 de la carte. */
	for(int i = 0; i < IConfig.LARGEUR_CARTE * IConfig.HAUTEUR_CARTE; i++)
		{		    
		elements[i] = null;
		}
	/* Tableaux de héros monstres et obstacles */
	heros = new ArrayList<Heros>(); 
	monstre = new ArrayList<Monstre>(); 
	obstacles  = new ArrayList<Obstacle>();

    /********************* Chargement des héros de base. ***********/
		for(int i = 0; i < IConfig.NB_HEROS; i++){/* On trouve une position vide, on créé un héros et on le place */
				Position pos = new Position(-1,-1);
				while(pos.estValide()==false || elements[pos.getNumCase()]!=null ){
					pos =new Position((int)Math.round((Math.random()*10)),(int)Math.round((Math.random()*IConfig.HAUTEUR_CARTE)));
				}	if (elements[pos.getNumCase()]==null ) {
						Heros h = new Heros(this,ISoldat.TypesH.getTypeHAlea(),(char)('A'+i),pos);		
					    heros.add(h);						  
						elements[pos.getNumCase()] = (Heros)h;
						elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_HEROS);	
						if(h.getTypeHero()==ISoldat.TypesH.HUMAIN){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Humain.png")));}
						else if(h.getTypeHero()==ISoldat.TypesH.ELF){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Elfe.png")));}
						else if(h.getTypeHero()==ISoldat.TypesH.NAIN){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Nain.png")));}
						else if(h.getTypeHero()==ISoldat.TypesH.HOBBIT){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Hobbit.png")));}
					}
		}
		/**************Idem  pour les monstres*************************** */
		for(int i = 0; i < IConfig.NB_MONSTRES; i++){	
			Position pos = new Position(-1,-1);
			while(pos.estValide()==false || elements[pos.getNumCase()]!=null ){
				pos =new Position((int)Math.round((Math.random()*10+15)),(int)Math.round((Math.random()*IConfig.HAUTEUR_CARTE)));
			}
						Monstre m = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),(i+1),pos);
						monstre.add(m);
						elements[pos.getNumCase()] =(Monstre) m;							
						elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_MONSTRES);
						if(m.getTypeMonstre()==ISoldat.TypesM.TROLL){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Troll.png")));}
						else if(m.getTypeMonstre()==ISoldat.TypesM.ORC){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Orc.png")));}
						else if(m.getTypeMonstre()==ISoldat.TypesM.GOBELIN){elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Gobelin.png")));}	
		}
		/**************Idem  pour les obstacles*************************** */
		for (int i=0; i<IConfig.NB_OBSTACLES;i++){
			Position pos = this.trouvePositionVide();
			Obstacle o = new Obstacle (Obstacle.TypeObstacle.getObstacleAlea(),pos);
			obstacles.add(o);
		    elements[pos.getNumCase()]= (Obstacle)o;
			switch(o.getTypeObstacle()){
			case ROCHER : elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_ROCHER);elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"ROCHER.jpg")));break;				           
			case FORET :  elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_FORET);elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Foret.jpg")));break;
			case EAU : elements[pos.getNumCase()].setCouleur(IConfig.COULEUR_EAU);elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Eau.png")));break;
	
			}			
		  }			
	/***************** Le reste est vide ************/
		for (int i=0; i <elements.length;i++){
			if (elements[i]==null){
				elements[i]= new Vide();
				Position pos = new Position();
				pos.setNumCase(i);
				elements[i].setPosition(pos);;
				elements[i].setCouleur(IConfig.COULEUR_VIDE);
				elements[i].setImage(new ImageIcon(getClass().getResource(IConfig.CHEMIN+"Herbe.png")));
			}
		}
		genererInconnu();		
		caseCliquee= -1;
		if(imageDebutJeu != null){
			imageDebutJeu.getParent().remove(imageDebutJeu);
			imageDebutJeu = null;
		}
		FenetreJeu.information2.setText(nbHerosRestant+" heros restants et "+nbMonstresRestant+" monstres restants ");
		
		
}

/**
 * Genère les cases qui sont inconnues
 */
public void genererInconnu(){		
			for(int j = 0; j < elements.length; j++) {
				if (elements[j] instanceof Heros){
				Heros h =(Heros) elements[j];
				changeVisibilite(h.getPosition(),h.getPortee(),true);
				}
			}		
}


/**
 * Méthode qui change la visiblité d'une case du jeu autour d'un soldat dans une position pos
 * @param pos Position : Positon du soldat
 * @param portee int : Portée du soldat
 * @param visible boolean : Visible=true, invisible=false
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
	
/* (non-Javadoc)
 * @see wargame.ICarte#getElement(wargame.Position)
 */
public Element getElement(Position pos) {
		return elements[pos.getNumCase()];
	}

/**
 * Retourne l'indice du héros portant le nom 'nom' dans la liste des héros.
 * @param nom char : Nom du héros
 * @return int Indice du héros
 */
public int getHeroList (char nom){
		int i;
		for ( i=0; i<heros.size();i++){
			if ((heros.get(i).getNom())==nom){
				break;				
			}
		}
		return i ;
}

/**
 * Retourne l'indice du monstre portant le numéro 'num' dans la liste des monstres
 * @param num int : Numéro du monstre
 * @return int Indice du monstre
 */
public int getMonstreList (int num){
	int i ; 
	for (i=0 ; i< monstre.size();i++){
		if (monstre.get(i).getNumero()==num){
			break;
		}
	}
	return i;
}

/* (non-Javadoc)
 * @see wargame.ICarte#trouvePositionVide()
 */
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

/* (non-Javadoc)
 * @see wargame.ICarte#trouvePositionVide(wargame.Position)
 */
public Position trouvePositionVide(Position pos) {
		Position positions[] = new Position[9];
		Position temp = new Position();		
		int nb_position_trouve = 0;		
		for(int x = -1; x <= 1; x++)	/* On parcourt toutes les positions possibles autour du soldat */
			for(int y = -1; y <= 1; y++)							
			{					
				temp.setX(pos.getX() + x) ;
				temp.setY(pos.getY() + y)  ;
				if (temp.estValide()){ //Si la position est valide (dans la carte)	
					if ( getElement(temp) instanceof Vide){
							positions[nb_position_trouve] = new Position(temp.getX(),temp.getY());
							nb_position_trouve++;
					}
				}
			}
		if(nb_position_trouve > 0) 		/* Si on a trouvé des positions valides, on en choisit une au hasard */
			temp = positions[(int)Math.round(Math.random()*(nb_position_trouve-1))];
		else
			temp = null;
		
		return temp;		
}


/**
 * Trouve un monstre autour des positions adjacentes du heros s'il y en a
 * @param pos Position : Position du héros
 * @return Monstre Monstre : Monstre autour du héros (ou null)
 */
public Monstre trouveMonstres(Position pos){
	Monstre h[] = new Monstre[9];
	Position temp = new Position();	
	int nb_position_trouve = 0;
	for(int x = -1; x <= 1; x++)
		for(int y = -1; y <= 1; y++)							
		{					
			temp.setX(pos.getX() + x) ;
			temp.setY(pos.getY() + y)  ;
			if (temp.estValide()) 
			{
				if ( getElement(temp) instanceof Monstre){
					h[nb_position_trouve] = (Monstre)getElement(temp);
					nb_position_trouve++;
				}
			}
		}
	if(nb_position_trouve > 0)
		return( h[(int)Math.round(Math.random()*(nb_position_trouve-1))]);
	else
		return null;	
}

/* (non-Javadoc)
 * @see wargame.ICarte#trouveHeros(wargame.Monstre)
 */
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
				if (temp.estValide()) 
				{
					if ( getElement(temp) instanceof Heros){
					h= (Heros)getElement(temp);
					}
				}
			}
		return h;
}


/* (non-Javadoc)
 * @see wargame.ICarte#trouveHeros(wargame.Position)
 */
public Heros trouveHeros(Position pos) {
		Heros h[] = new Heros[9];
		Position temp = new Position();		
		int nb_position_trouve = 0;
		for(int x = -1; x <= 1; x++)
			for(int y = -1; y <= 1; y++)							
			{					
				temp.setX(pos.getX() + x) ;
				temp.setY(pos.getY() + y)  ;
				if (temp.estValide()) {				
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
	
/* (non-Javadoc)
 * @see wargame.ICarte#deplaceSoldat(wargame.Position, wargame.Soldat)
 */	
public boolean deplaceSoldat(Position pos, Soldat soldat) throws Exception {
		Position p = soldat.getPosition(); // Je récupere la pos du soldat 
		boolean retour = false;
			if (!pos.estValide()){
				throw new Exception("La nouvelle position est invalide");				
			}else {					
				if (pos.distance(p)<=2) { // pos adjacente et valide
						soldat.setAJoue(true);
						soldat.setPosition(pos); // Mettre a jour la position du soldat
						/* Mettre a jour les arrayList */
						if (soldat instanceof Heros){ // Si le soldat est un hero
									int i = getHeroList (((Heros) soldat).getNom());
									heros.add(i,(Heros)soldat); // On remplace l'ancien héros par le nouveau 
								}else { 
						if (soldat instanceof Monstre){
										int i = getMonstreList (((Monstre) soldat).getNumero());
										monstre.add(i,(Monstre)soldat); // On remplace l'ancien monstre par le nouveau 
										}
						}
						changeVisibilite(pos, soldat.getPortee(), false);
						elements[p.getNumCase()]=new Vide();
						elements[p.getNumCase()].setPosition(p);
						elements[p.getNumCase()].setVisible(false);
						elements[p.getNumCase()].setCouleur(IConfig.COULEUR_VIDE);
						elements[p.getNumCase()].setImage(new ImageIcon(getClass().getResource("../Images/Herbe.png")));
						elements[pos.getNumCase()]= soldat; // Mettre a jour la nouvelle case ou se trouve le soldat
						elements[pos.getNumCase()].setPosition(pos);
						retour= true;
				}
			}
		return retour;	
}

/* (non-Javadoc)
 * @see wargame.ICarte#actionHeros(wargame.Position, wargame.Position)
 */
public void actionHeros(Position pos, Position pos2) {
	
	int d = pos.distance(pos2);
		if (getElement(pos) instanceof Heros){		
			Heros h = (Heros)getElement(pos);
			if (getElement(pos2) instanceof Vide){				
				try{     // Si la pos2 est vide le soldat s'y déplace
					    if ((elements[pos2.getNumCase()].getVisible()==false) || (d>2)){
					    	messageCasetroploin();
					    }else {
					    	boolean temp = deplaceSoldat(pos2,h);  // Je déplace le soldat 	
							if (temp==true){ // Le soldat a été déplacé ==> mettre a jour les inconnus 						
								changeVisibilite(pos,h.getPortee(),false); // Mettre a jour autour de l'ancienne position 
								genererInconnu(); 			 // Mettre a jour autour de toutes les positions 
								Monstre m = trouveMonstres(h.getPosition());
									if (m!=null){
										actioncombat (h,m);
									}
															
						}
					    
					    }
					
				 // Sinon la position pos2 n'est pas  adjacente/valide 				
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else { // pos2 n'est pas vide donc c'est soit un monstres (donc combat) soit un obstacle			
				    if (getElement(pos2) instanceof Monstre){ // S'il ya un monstre alors combat
					Soldat s = (Heros)getElement(pos);
					actioncombat(s,(Soldat)getElement(pos2));
					
				}
			}
		}
	
}

/* (non-Javadoc)
 * @see wargame.ICarte#mort(wargame.Soldat)
 */
public void mort(Soldat perso) {
	if(perso.estMort()==true)
	{
		if(perso instanceof Heros)
		{
			
			changeVisibilite(perso.getPosition(),perso.getPortee(),false);
		
			heros.remove(perso);
		}
		else 
		{
			monstre.remove(perso);
		}
		Position pos = perso.getPosition();
		elements[perso.getPosition().getNumCase()]=new Vide();
		elements[pos.getNumCase()].setPosition(pos);
		elements[pos.getNumCase()].setImage(new ImageIcon(getClass().getResource("../Images/Herbe.png")));
	}
	genererInconnu();

	
}

/* (non-Javadoc)
 * @see wargame.ICarte#jouerSoldats()
 */
public void jouerSoldats() {
		 addMouseListener(new MouseAdapter() {
		    	// Capture du clic sur la carte 
				public void mouseClicked(MouseEvent e) { 
					// On crée une position aux coordonnées du clic de la souris pour connaitre la coordonnée de la case (et non pas du pixel) 
					Position pos = new Position(e.getX() / IConfig.NB_PIX_CASE , e.getY() / IConfig.NB_PIX_CASE);
					int case_encours = pos.getNumCase();
				 if (elements[case_encours] instanceof Monstre){					
					}
					if (elements[case_encours] instanceof Heros){
		
				}
					// On change de héros si la case n'est pas vide, qu'il s'agit bien d'un soldat et que le soldat est affiché. 
					if(elements[case_encours] != null 
						&& elements[case_encours] instanceof Heros 
						
					) {
						caseCliquee = case_encours;
					}
					// Sinon on va faire une action en rapport avec le soldat sélectionné. /
					else {
						if(caseCliquee != -1) {
						
							Heros h = (Heros)elements[caseCliquee];
							if (h.getAJoue()==false){
													if (obstacleEntreCase(new Position(caseCliquee),new Position(case_encours))==true){
													    messageObstacle();
													}else{										
															
													 actionHeros(new Position(caseCliquee),new Position(case_encours));
													nbSoldatAJouer--;
													repaint();
													}
									}else{
									 jop1 = new JOptionPane();
										JOptionPane.showMessageDialog(null, "Vous avez déjà joué ce soldat", "Erreur", JOptionPane.ERROR_MESSAGE);
									repaint();
								 }
						}
						
						caseCliquee=-1;
				}
					
				}
				
			
		    });  
		  addMouseMotionListener(new MouseMotionListener() {

				public void mouseDragged(MouseEvent e) {}

				public void mouseMoved(MouseEvent e) {
					Position pos = new Position(e.getX() / IConfig.NB_PIX_CASE , e.getY() / IConfig.NB_PIX_CASE);
					if (pos.estValide()&& (generee==true)){
					FenetreJeu.information.setText(pos.toString() +" "+ getElement(pos));
					}
				}
				   
			   });
		
	
}



/**
 * Cherche un obstacle entre position1 et position2.
 * Renvoie true si il y a un obstacle, false sinon.
 * @param position1 Position : Première position
 * @param position2 Position : Seconde position
 * @return boolean obstacle=true, rien=false
 */
public boolean obstacleEntreCase(Position position1, Position position2){

	int i;
	if(position1.distance(position2)==1)
	{
		return false;
	}
	else
	{
		if(position1.getNumCase()-position2.getNumCase() == IConfig.LARGEUR_CARTE*position1.distance(position2)+position1.distance(position2)) /*Case en haut à gauche*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!((elements[position1.getNumCase()-(i*IConfig.LARGEUR_CARTE)-(position1.distance(position2)-i)]) instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == IConfig.LARGEUR_CARTE*position1.distance(position2)) /*Case en haut*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()-(i*IConfig.LARGEUR_CARTE)] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == IConfig.LARGEUR_CARTE*position1.distance(position2)-position1.distance(position2)) /*Case en haut à droite*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()-(i*IConfig.LARGEUR_CARTE)+i] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == position1.distance(position2)) /*Case à droite*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()-(position1.distance(position2)-i)] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == -position1.distance(position2)) /*Case à gauche*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()+(position1.distance(position2)-i)] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == -IConfig.LARGEUR_CARTE*position1.distance(position2)-position1.distance(position2)) /*Case en bas à gauche*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()+(i*IConfig.LARGEUR_CARTE)-(position1.distance(position2)-i)] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == -IConfig.LARGEUR_CARTE*position1.distance(position2)) /*Case en bas*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()+(i*IConfig.LARGEUR_CARTE)] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
		if(position1.getNumCase()-position2.getNumCase() == -IConfig.LARGEUR_CARTE*position1.distance(position2)+position1.distance(position2)) /*Case en bas à droite*/
		{
			for(i=1;i<position1.distance(position2);i++)
			{
				if(!(elements[position1.getNumCase()+(i*IConfig.LARGEUR_CARTE)+(position1.distance(position2)-i)] instanceof Vide))
				{
					return true;
				}
			}
			return false;
		}
	}
	return false;
}	


	
/* (non-Javadoc)
 * @see wargame.ICarte#actionMonstres()
 */
public void actionMonstres() {
   boolean repos; 
	for (int i=0; i<elements.length;i++){			
			if (elements[i] instanceof Monstre){
					Monstre monstre = (Monstre)elements[i];				
					if (monstre.getAJoue()==false){							
							Heros h = trouveHeros(monstre); // Chercher un héros a la portée (visuelle) du monstre
							if ((h!=null) && (h.getRepos()==false)){  // Il y a un héros a sa portée donc il l'attaque "corps a corps ou combat a distance"	
								actioncombat(monstre,h);
								}
							else { // Aucun soldat a sa portée donc il se déplace
								Position pos= trouvePositionVide(monstre.getPosition());
								if (pos != null ){ // Position adjacente trouvée donc déplacement
										try {
												repos = deplaceSoldat (pos,monstre); // On déplace le monstre						
												/************ Le cas où, apres le déplacement, il ya un héros a coté donc combat***/
												if (repos == true){  
													
													Heros h1 = trouveHeros(monstre.getPosition());
													if (h1!=null) {
														actioncombat(monstre,h1);
													}													
												}
												else{
													monstre.setAJoue(true);
													elements[monstre.getPosition().getNumCase()]=monstre;
												}
										}catch(Exception e1){
											e1.printStackTrace();
										}
								}
						}
				}
			}
		}
	genererInconnu();
		tourJoueur=true;
}

/**
 * Déclenche un combat et fini le jeu si une armée est morte
 * @param attaquant Soldat : Soldat qui attaque
 * @param defenseur Soldat : Soldat victime
 */
public void actioncombat(Soldat attaquant, Soldat defenseur){

	int resultat= attaquant.combat(defenseur);
	if(attaquant instanceof Monstre){
		if(resultat == -1){
			nbMonstresRestant--;	
		}
		else if(resultat == 1) {
			nbHerosRestant--;
			changeVisibilite(defenseur.getPosition(), defenseur.getPortee() , false);
		}
	}
	else{
		if(resultat == -1) {
			nbHerosRestant--;
			changeVisibilite(attaquant.getPosition(), attaquant.getPortee() , false);
		}
		else if(resultat == 1){
			nbMonstresRestant--;
		}
	}
	
	if(nbMonstresRestant <= 0){
		joueurIsWinner();	
		
	}
	else if(nbHerosRestant <= 0){
		joueurIsLooser();
	
	}
	genererInconnu();
	FenetreJeu.information2.setText(nbHerosRestant+" heros restants et "+nbMonstresRestant+" monstres restants ");
	
}

/**
 * Affiche un message en cas de victoire joueur
 */
public void joueurIsWinner(){
	caseCliquee = -1;
	jop1 = new JOptionPane();
	JOptionPane.showMessageDialog(null, "Vous avez gagné la partie !", "BRAVO", JOptionPane.INFORMATION_MESSAGE);
    repaint();
}

/**
 * Affiche un message en cas de victoire ordinateur
 */
public void joueurIsLooser(){
	caseCliquee = -1;
	jop1 = new JOptionPane();
	JOptionPane.showMessageDialog(null, "GAME OVER ! Vous avez perdu !", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
    repaint();
}

/**
 * Affiche un message en cas d'obstacle sur le chemin
 */
public void messageObstacle(){
	jop1 = new JOptionPane();
	JOptionPane.showMessageDialog(null, "Un obstacle vous bloque le passage", "Erreur", JOptionPane.ERROR_MESSAGE);
    repaint();
}
/**
 * Affiche un message si on déplace le soldat trop loin
 */
public void messageCasetroploin(){
	jop1 = new JOptionPane();
	JOptionPane.showMessageDialog(null, "Case trop loin !", "Erreur", JOptionPane.ERROR_MESSAGE);
    repaint();
}
/**
 * Affiche un message si on attaque un monstre trop loin
 */
public void messageSoldatLoin(){
	jop1 = new JOptionPane();
	JOptionPane.showMessageDialog(null, "Monstre trop loin ! impossible de tirer", "Erreur", JOptionPane.ERROR_MESSAGE);
    repaint();
}

/**
 * Dessine une case rectangulaire dans la carte
 * @param g Graphics
 * @param x int : coordonnée x
 * @param y int : coordonnée y
 * @param c Color : couleur de la case
 * @param imageIcon ImageIcon : image correspondant à la case
 */
protected void dessineRectangle(Graphics g, int x, int y, Color c, ImageIcon imageIcon) {	
		g.setColor(c);
		g.fillRect(x * IConfig.NB_PIX_CASE, y * IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE);
		g.drawImage(imageIcon.getImage(),x * IConfig.NB_PIX_CASE,y * IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE, IConfig.NB_PIX_CASE,null);
		
}
/* (non-Javadoc)
 * @see wargame.ICarte#toutDessiner(java.awt.Graphics)
 */
public void toutDessiner(Graphics g) {
	
		for (int i=0;i<elements.length;i++){
			if (elements[i].getVisible()==true){
			dessineRectangle(g,elements[i].getPosition().getX(),elements[i].getPosition().getY(),elements[i].getCouleur(),elements[i].getImage());
			}
			else {
				dessineRectangle(g,elements[i].getPosition().getX(),elements[i].getPosition().getY(),IConfig.COULEUR_INCONNU,(new ImageIcon(getClass().getResource("../Images/Brouillard.png"))));
				
			}
		}
	
	}
/* (non-Javadoc)
 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
 */
protected void paintComponent(Graphics g) 
	{   		
	super.paintComponent(g);
		if(!generee)
			return;
		if (caseCliquee==-1){
		toutDessiner(g);
	
	}
}
	
/* (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 */
public void actionPerformed(ActionEvent e) {
	
	repaint();	
	}

/** 
 * Méthode permettant de reinitialiser le tour de tous les soldats
 */
public void reinitialiserJeu() {
	if(generee != true)
		return;
	if (nbSoldatAJouer!=0){
		for (int i=0; i<elements.length;i++){
			if (elements[i] instanceof Heros){
				Heros h = (Heros) elements[i];
				if (h.getAJoue()==false){
					h.setAJoue(true);
					h.setRepos(true);
					h.repos();
					elements[i] = h;
				}
			}
		}
	}
	if (tourJoueur==false){
	   actionMonstres();
	}
	caseCliquee = -1;

	nbSoldatAJouer = nbHerosRestant;
	for (int i=0;i<elements.length;i++){
				if (elements[i] instanceof Soldat){
						Soldat s =(Soldat)elements[i];
						s.setAJoue(false);
						s.setRepos(false);
						elements[i]=s;
		              }
	}
	
	
}
/*
 * Sauvegarde les données utiles pour le rechargement entier de la carte et des informations
 */
public void saveData(String chemin)
{
	
		try {
			FileOutputStream fichier = new FileOutputStream(chemin);
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			
			for (int i = 0; i < this.elements.length; i++)
			{
				oos.writeObject(this.elements[i]);
			}
			
			oos.writeObject(generee);
			oos.writeObject(nbHerosRestant);
			oos.writeObject(nbMonstresRestant);
			oos.writeObject(nbSoldatAJouer);
			oos.writeObject(tourJoueur);
			oos.writeObject(caseCliquee);
			oos.flush();
			oos.close();
			
			
		} catch (Exception ex) {

			ex.printStackTrace();

		}
}

/**
 * Chargement des données et remplacement des données actuelles afin de charger l'ancienne partie
 */
public void loadData(String chemin)
{
	if(imageDebutJeu != null){
		imageDebutJeu.getParent().remove(imageDebutJeu);
		imageDebutJeu = null;
	}
		try {
			FileInputStream fichier = new FileInputStream(chemin);
			ObjectInputStream ois = new ObjectInputStream(fichier);
	         for (int i = 0; i < elements.length; i++)
	         {
	        	 elements[i] = (Element) ois.readObject();
	        	
	        	 
	         }
	     
	         	generee = (boolean) ois.readObject();
				nbHerosRestant = (int) ois.readObject();
				nbMonstresRestant = (int) ois.readObject();
				nbSoldatAJouer= (int) ois.readObject();
				tourJoueur = (boolean) ois.readObject();
				caseCliquee = (int) ois.readObject();
		       
				ois.close();
		}catch(java.io.IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		repaint();
}
}
