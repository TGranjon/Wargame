import java.awt.Graphics;

public class Carte implements ICarte, IConfig {

	public Carte() {
		int i;
		for(i=0;i<NB_HEROS;i++)
		{
			new Heros(this,ISoldat.TypesH.getTypeHAlea(),(char)('A'+i),trouvePositionVideSoldat());
			/*new Position((int)Math.round((Math.random()*5)),(int)Math.round((Math.random()*HAUTEUR_CARTE)))*/
		}
		for(i=0;i<NB_MONSTRES;i++)
		{
			new Monstre(this,ISoldat.TypesM.getTypeMAlea(),1+i,trouvePositionVideMonstre());
		}
		for(i=0;i<NB_OBSTACLES;i++)
		{
			new Obstacle(Obstacle.TypeObstacle.getObstacleAlea(),trouvePositionVide());
		}
	}
	public Element getElement(Position pos) {
		// TODO Auto-generated method stub
		return null;
	}
	public Position trouvePositionVideSoldat() {
		// TODO Auto-generated method stub
		return null;
	}
	public Position trouvePositionVideMonstre() {
		// TODO Auto-generated method stub
		return null;
	}
	public Position trouvePositionVide() {
		// TODO Auto-generated method stub
		return null;
	}
	public Position trouvePositionVide(Position pos) {
		// TODO Auto-generated method stub
		return null;
	}
	public Heros trouveHeros() {
		// TODO Auto-generated method stub
		return null;
	}
	public Heros trouveHeros(Position pos) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		// TODO Auto-generated method stub
		return false;
	}
	public void mort(Soldat perso) {
		// TODO Auto-generated method stub
		
	}
	public boolean actionHeros(Position pos, Position pos2) {
		// TODO Auto-generated method stub
		return false;
	}
	public void jouerSoldats(PanneauJeu pj) {
		// TODO Auto-generated method stub
		
	}
	public void toutDessiner(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
