package wargame;

import java.awt.Graphics;

public interface ICarte {

	/**
	 * Retorune l'�l�ment de la case avec les coordon�es de pos
	 * @param pos Position : Position qui nous int�resse
	 * @return Element El�ment � cette position
	 */
	Element getElement(Position pos);
	
	/**
	 * Trouve al�atoirement une position vide sur la carte
	 * ATTENTION METHODE UTILISEE UNIQUEMENT POUR LA GENERATION DE LA CARTE AU DEBUT DU JEU !!!!
	 * @return Position Position vide al�atoire
	 */
	Position trouvePositionVide(); // Trouve al�atoirement une position vide sur la carte
	
	/**
	 * Trouve une position vide choisie al�atoirement parmi les positions adjacentes de pos qui se trouvent a gauche (pour les d�placements des monstres)
	 * @param pos Position : Position autour de laquelle on regarde
	 * @return Position Position vide al�atoire autour de pos
	 */
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie al�atoirement parmi les 8 positions adjacentes de pos
	
	/**
	 * Trouve un heros a la portee du monstre
	 * Quand c'est le tour des monstres, cherche un h�ros � sa port�e pour l'attaquer � distance
	 * @param mstr Monstre : Monstre actuellement jou�
	 * @return Heros H�ros al�atoire autour de mstr ou null
	 */
	Heros trouveHeros(Monstre mstr);
	
	/**
	 * Trouve un h�ros choisi al�atoirement parmi les 8 positions adjacentes de pos
	 * Utilis� apres un deplacement d'un monstre pour voir s'il y a combat
	 * @param pos Position : Position autour de laquelle on cherhche un h�ros
	 * @return Heros H�ros al�atoire autour de pos ou null
	 */
	Heros trouveHeros(Position pos);
	
	/**
	 * Retourne vrai si le soldat a �t� d�plac� en mettant a jour la carte
	 * Retourne faux si la position n'est pas valide ou n'est pas adjacente
	 * @param pos Position : Case o� se d�placer
	 * @param soldat Soldat : Soldat qui se d�place
	 * @return boolean Se d�place=true, ne se d�place pas=false
	 * @throws Exception
	 */
	boolean deplaceSoldat(Position pos, Soldat soldat)throws Exception;
	
	/**
	 * Supprime le soldat
	 * @param perso Soldat : Soldat � supprimer
	 */
	void mort(Soldat perso);
	
	/**
	 * Le joueur donne l�ordre au h�ros situ� � la position pos d�agir � la position pos2.
	 * Le h�ros s�y d�place si la position est vide en mettant a jour les inconnus ou combat le monstre qui s�y trouve �ventuellement.
	 * La m�thode retourne false s�il n�y a pas de h�ros � la position pos, si pos2 n�est pas une position adjacente, ou s�il y a d�j� un h�ros � la position pos2, ou si le h�ros a d�j� jou� son tour.
	 * @param pos Position : Position de d�part
	 * @param pos2 Position : Position d'arriv�e
	 */
	void actionHeros(Position pos, Position pos2);
	/**
	 * Joue le tour des monstres
	 */
	void actionMonstres(); // fait jouer les monstres quand c'est leur tour !!
	/**
	 * Permet de jouer les soldats (tour du joueur)
	 */
	void jouerSoldats();
	/**
	 * Dessine la carte et ses �l�ments
	 * @param g Graphics
	 */
	void toutDessiner(Graphics g);

}