public class SuperOuvriere extends Ouvriere{
	
	// CONSTRUCTEURS
	
	public SuperOuvriere() {
		super(1,(int) (5*(Math.random()*((ESPERANCE_OUVRIERE*(1+DISPERTION/2)-ESPERANCE_OUVRIERE*(1-DISPERTION/2))+ESPERANCE_OUVRIERE*(1-DISPERTION/2)))),"Ouvriere");
	}
	
	// EL CLASSICO
	public String toString(){
		return String.format("super ouvriere a %3d jour(s) en position (%2d,%2d) avec %2d pollen(s) et et %3d jour(s) d'esperance",getAge(),this.getP().x,this.getP().y,this.getPollen(),this.getEsperance());
	}

}