package wargame;

/**
 * @author Thomas GRANJON
 * @author Axel SOFONEA
 * @author Sarah RIGHI
 *
 */

public interface ISoldat {

	public static enum TypesH {
    
		HUMAIN (40,3,10,2), NAIN (80,1,20,0), ELF (70,5,10,6), HOBBIT (20,3,5,2);
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
		
		TypesH(int points, int portee, int puissance, int tir) {
			POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
			PUISSANCE = puissance; TIR = tir;
		}
		
		/**
		 * Renvoie le nombre de points de vie du héros
		 * @return int Points de vie du héros
		 */
		public int getPoints() { return POINTS_DE_VIE; }
		/**
		 * Renvoie la portée du héros
		 * @return int Portée du héros
		 */
		public int getPortee() { return PORTEE_VISUELLE; }
		/**
		 * Renvoie la puissance au corps à corps du héros
		 * @return int Puissance au corps à corps du héros
		 */
		public int getPuissance() { return PUISSANCE; }
		/**
		 * Renvoie la puissance de tir du héros
		 * @return int Pusissance de tir du héros
		 */
		public int getTir() { return TIR; }
	
		/**
		 * Renvoie un type de héros aléatoire
		 * @return TypesH Type aléatoire de héros
		 */
		public static TypesH getTypeHAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	
	public static enum TypesM {
		TROLL (100,1,30,0), ORC (40,2,10,3), GOBELIN (20,2,5,2);
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
		TypesM(int points, int portee, int puissance, int tir) {
			POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
			PUISSANCE = puissance; TIR = tir;
		}
		
		/**
		 * Renvoie le nombre de points de vie du monstre
		 * @return int Points de vie du monstre
		 */
		public int getPoints() { return POINTS_DE_VIE; }
		/**
		 * Renvoie la portée du monstre
		 * @return int Portée du monstre
		 */
		public int getPortee() { return PORTEE_VISUELLE; }
		/**
		 * Renvoie la puissance au corps à corps du monstre
		 * @return int Puissance au corps à corps du monstre
		 */
		public int getPuissance() { return PUISSANCE; }
		/**
		 * Renvoie la puissance de tir du monstre
		 * @return int Puissance de tir du monstre
		 */
		public int getTir() { return TIR; }
	
		
		/**
		 * Renvoie un type de monstre aléatoire
		 * @return TypesM Type de monstre aléatoire
		 */
		public static TypesM getTypeMAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
		/**
		 * Renvoie la portée du soldat
		 * @return int Portée su soldat
		 */
		int getPortee();
		/**
		 * Renvoie la puissance au corps à corps du soldat
		 * @return int Puissance au corps à corps du soldat
		 */
		int getPuissance();
		/**
		 * Renvoie la puissance de tir du soldat
		 * @return int Puissance de tir su soldat
		 */
		int getTir();
		/**
		 * Renvoie les points de vie maximaux du soldat
		 * @return int Points de vie maximaux du soldat
		 */
		int getVieMax();
		/**
		 * Renvoie les points de vie actuel du soldat
		 * @return int Points de vie du soldat
		 */
		int getVie();
		/**
		 * Change l'attribut mort du soldat
		 * @param mort boolean : mort=true, vivant=false
		 */
		void setMort(boolean mort);
		/**
		 * Change l'attribut position du soldat
		 * @param position Position : Nouvelle position du soldat
		 */
		void setPosition(Position position);
		/**
		 * Change l'attribut aJoue du soldat
		 * @param value boolean : a déja joué=true, n'a pas encore joué=false
		 */
		void setAJoue(boolean value);
		/**
		 * Change l'attribut points de vie du soldat
		 * @param vie int : Nouveaux points de vie du soldat
		 */
		void setVie(int vie);
		/**
		 * Combat entre deux soldat tel que :
		 * Le soldat en cours est l'attaquant, et 'soldat" est le défenseur (l'attaqué) :
		 * on retourne -1 si l'attaquant est mort,
		 * on retourne 1 si le défenseur est mort
		 * et 0 sinon
		 * @param soldat Soldat : Soldat attaqué
		 * @return int Eventuelle victime
		 */
		int combat(Soldat soldat);

}