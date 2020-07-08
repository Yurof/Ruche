public class Ouvriere extends Abeille {
	
	// ATTRIBUTS
	private Point p;
	private int qtt_pollen;
	
	// CONSTRUCTEURS
	
	public Ouvriere(){ // Constructeur de la classe ouvrière
		super(1,(int) (Math.random()*((ESPERANCE_OUVRIERE*(1+DISPERTION/2)-ESPERANCE_OUVRIERE*(1-DISPERTION/2)))+ESPERANCE_OUVRIERE*(1-DISPERTION/2)),"Ouvriere");
		// Vie entre 7 et 10 jours.
		p= new Point(Map.DIMENSION/2,Map.DIMENSION/2);
		qtt_pollen=0;
	}
	
	public Ouvriere(int age, int esperance, String nom){ // Constructeur de la classe ouvrière
		super(age,esperance,nom);
		// Un oeuf éclos au bout de 3 jours, arrive à maturité en 1 jour et vie entre 7 et 10 jours.
		p= new Point(Map.DIMENSION/2,Map.DIMENSION/2);
		qtt_pollen=0;
	}
	// ACCESSEURS
	
	public Point getP() {
		return p;
	}
	
	public int getPollen() {
		return qtt_pollen;
	}
	
	// MODIFICATEURS
	
	public void setPollen(int collecte) {
		qtt_pollen+=collecte;
	}
	
	// METHODES ABSTRAITES A IMPLEMENTER 
	public void manger(Saison s, Miel m) {
        if(s.getSaison().equals("Printemps"))
            m.enlever_miel(CONSOMMATION_PRINTEMPS);
        if(s.getSaison().equals("Ete"))
            m.enlever_miel(CONSOMMATION_ETE);
        if(s.getSaison().equals("Automne"))
            m.enlever_miel(CONSOMMATION_AUTOMNE);
        if(s.getSaison().equals("Hiver"))
            m.enlever_miel(CONSOMMATION_HIVER);
    }
	public void vieillir() {
		age++;
	}
	
	// EL CLASSICO
	public String toString(){
		return String.format("ouvriere a %3d jour(s) en position (%2d,%2d) avec %2d pollen(s) et %3d jour(s) d'esperance",getAge(),p.getX(),p.getY(),qtt_pollen,getEsperance());
	}
	
	// METHODES
	
	public void bouger(Point cible) {
		if(getEsperance()-getAge()>1 && qtt_pollen != Abeille.MAX_POLLEN_PORTABLE) { // Si l'abeille n'est pas remplis au maximum et qu'elle est encore jeune
			p.setX(cible.getX());
			p.setY(cible.getY());
		}
		else {// Si elle va mourrir ou elle est remplis
			p.setX(Map.DIMENSION/2);
			p.setY(Map.DIMENSION/2);
		}
	}
	
	public void deposer_pollen(Miel mi) {
		if(p.x == Map.DIMENSION/2 && p.y == Map.DIMENSION/2) {
			mi.deposer_pollen(qtt_pollen);
			qtt_pollen=0;
		}
	}
}