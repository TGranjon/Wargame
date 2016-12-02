package wargame;

 

import java.awt.Graphics;

public interface ICarte {

	Element getElement(Position pos);
	Position trouvePositionVide(); // Trouve aléatoirement une position vide sur la carte
	Position trouvePositionVide(Position pos); // Trouve une position vide choisie
								// aléatoirement parmi les 8 positions adjacentes de pos
	Heros trouveHeros(Monstre mstr); // Trouve un heros a la portee 
	Heros trouveHeros(Position pos); // Trouve un héros choisi aléatoirement
									 // parmi les 8 positions adjacentes de pos
	boolean deplaceSoldat(Position pos, Soldat soldat);
	void mort(Soldat perso);
	boolean actionHeros(Position pos, Position pos2);
	void actionMonstres(); // fait jouer les monstres quand c'est leur tour !!
	void jouerSoldats();
	void toutDessiner(Graphics g);
	/** rajouter methode pour les actions des monstres 'deplacement' eventuellement action (combat)***/
}