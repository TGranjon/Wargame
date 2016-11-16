 

public class Soldat {

    public Soldat() {
        // TODO Auto-generated constructor   stub
    }
    
    boolean enVie()
    {
        if (this.POINTS_DE_VIE > 0) return true;
        else {
            //si mort destruction personnage
            return false;
        }
    }
    /* 
     * int getPoints()
    {
        
    }
    int getTour(); 
    int getPortee();
	void joueTour(int tour);
	void combat(Soldat soldat);
	void seDeplace(Position newPos);*/

}
