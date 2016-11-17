public class Monstre extends Soldat {

    private static int num_monstre = 0;
    private Carte carte;
    private ISoldat.TypesM typeM;
    private int name =38;
    
	public Monstre(Carte carte,ISoldat.TypesM typeM,int name,Position pos) {
		num_monstre++;
		this.carte=carte;
		this.typeM=typeM;
		this.name=name;
		this.pos=pos;		
	}

}
