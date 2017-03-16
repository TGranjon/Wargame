package wargame;

import java.awt.Graphics;

public interface ICarte {

	/**
	 * Retorune l'élément de la case avec les coordonées de pos
	 * @param pos Position : Position qui nous intéresse
	 * @return Element Elément à cette position
	 */
	Element getElement(Position pos);
	
	/**
	 * Trouve aléatoirement une position vide sur la carte
	 * ATTENTION METHODE UTILISEE UNIQUEMENT POUR LA GENERATION DE LA CARTE AU DEBUT DU JEU !!!!
	 * @return Position Position vide aléatoire
	 */
	Position trouvePositionVide(); // Trouve aléatoirement une position vide sur la carte
	
	/**
	 * Trouve une position vide choisie aléatoirement parmi les positions adjacentes de pos qui se trouvent a gauche (pour les déplacements des monstres)
	 * @param pos Position : Position autour de laquelle on regarde
	 * @return Position Position vide aléatoire autour de pos
	 */
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie aléatoirement parmi les 8 positions adjacentes de pos
	
	/**
	 * Trouve un heros a la portee du monstre
	 * Quand c'est le tour des monstres, cherche un héros à sa portée pour l'attaquer à distance
	 * @param mstr Monstre : Monstre actuellement joué
	 * @return Heros Héros aléatoire autour de mstr ou null
	 */
	Heros trouveHeros(Monstre mstr);
	
	/**
	 * Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos
	 * Utilisé apres un deplacement d'un monstre pour voir s'il y a combat
	 * @param pos Position : Position autour de laquelle on cherhche un héros
	 * @return Heros Héros aléatoire autour de pos ou null
	 */
	Heros trouveHeros(Position pos);
	
	/**
	 * Retourne vrai si le soldat a été déplacé en mettant a jour la carte
	 * Retourne faux si la position n'est pas valide ou n'est pas adjacente
	 * @param pos Position : Case où se déplacer
	 * @param soldat Soldat : Soldat qui se déplace
	 * @return boolean Se déplace=true, ne se déplace pas=false
	 * @throws Exception
	 */
	boolean deplaceSoldat(Position pos, Soldat soldat)throws Exception;
	
	/**
	 * Supprime le soldat
	 * @param perso Soldat : Soldat à supprimer
	 */
	void mort(Soldat perso);
	
	/**
	 * Le joueur donne l’ordre au héros situé à la position pos d’agir à la position pos2.
	 * Le héros s’y déplace si la position est vide en mettant a jour les inconnus ou combat le monstre qui s’y trouve éventuellement.
	 * La méthode retourne false s’il n’y a pas de héros à la position pos, si pos2 n’est pas une position adjacente, ou s’il y a déjà un héros à la position pos2, ou si le héros a déjà joué son tour.
	 * @param pos Position : Position de départ
	 * @param pos2 Position : Position d'arrivée
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
	 * Dessine la carte et ses éléments
	 * @param g Graphics
	 */
	void toutDessiner(Graphics g);

}