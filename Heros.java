public class Heros extends Soldat {

    private static int num_heros = 0;
    private Carte carte;
    private ISoldat.TypesH typeH;
    private char name;
    
	public Heros(Carte carte,ISoldat.TypesH typeH,char name,Position pos) {
		num_heros++;
		this.carte=carte;
		this.typeH=typeH;
		this.name=name;
		this.pos=pos;		
	}
}