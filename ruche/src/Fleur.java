public class Fleur {
	
	// ATTRIBUTS
	private int x,y,qtt_pollen;
	private Point p;
	protected static int MAXPOLLEN = 100;
	protected static int MOYENNEPOLLEN = 10;
	
	// CONSTRUCTEURS
	
	public Fleur(int x, int y){
		this.x=x;
		this.y=y;
		qtt_pollen=(int) (Math.random()*(MAXPOLLEN/5.0)+MAXPOLLEN/10);
		p = new Point(x,y);
	}
	
	public Fleur(Fleur f) {
		this.x=f.getX();
		this.y=f.getY();
		this.qtt_pollen=f.getQttPollen();
	}
	
	// ACCESSEURS 
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Point getP() {
		return p;
	}
	
	public int getQttPollen(){
		return qtt_pollen;
	}
	
	// MODIFICATEURS
	
	public void setQttPollen(int qtt){
		qtt_pollen=qtt;
	}
	
	// METHODES
	
	public void production(){ // Production journalière de pollen dans la fleur
		if(qtt_pollen==0)	return; 
		int ajout = (int) (Math.random()*(MOYENNEPOLLEN*(1.0+ Abeille.DISPERTION/2.0)-MOYENNEPOLLEN*(1.0-Abeille.DISPERTION/2.0))+MOYENNEPOLLEN*(1.0-Abeille.DISPERTION/2.0));
		if(qtt_pollen+ajout>MAXPOLLEN)	qtt_pollen=MAXPOLLEN;
		qtt_pollen+=ajout;
	}
	
	public int enlever_pollen(int retrait) { // Retire le pollen que les ouvrières ont pris de la fleur
		if(qtt_pollen == 0) {
			return 0;
		}else {
			if(qtt_pollen-retrait<0) {
				qtt_pollen=0;
				return retrait-qtt_pollen;
			}else{
				qtt_pollen-=retrait;
				return retrait;
			}
		}
	}
	// EL CLASSICO
	
	public String toString(){
		return String.format("Fleur en position(%3d,%3d) avec %2d pollens",this.x,this.y,this.qtt_pollen);
	}
}
