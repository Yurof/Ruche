public class Reine extends Abeille{
	
	// ATTRIBUTS
	
	private int nbs_Pontes_Max; // nbs d'oeufs maximum restant que la reine peut pondre
	private int oeufParJour;
		
	// CONSTRUCTEURS
	
	public Reine(){//Constructeur de la classe Reine
		super(1,(int) (Math.random()*((ESPERANCE_REINE*(1+DISPERTION/2)-ESPERANCE_REINE*(1-DISPERTION/2)))+ESPERANCE_REINE*(1-DISPERTION/2)),"Reine"); // Vie entre 210 et 270 jours.
		nbs_Pontes_Max= (int) (Math.random()*((MAX_OEUFS*(1+DISPERTION/2)-MAX_OEUFS*(1-DISPERTION/2)))+MAX_OEUFS*(1-DISPERTION/2));//Ce nombre varie entre 2450 et 3150 oeufs.
		oeufParJour=0;
	}
	
	// ACCESSEURS
	
	public int getNbMax() {
		return nbs_Pontes_Max;
	}
	
	public int getOeufParJour() {
		return oeufParJour;
	}
	// MODIFICATEURS
	
	public void setOeufParJour(int n) {
		oeufParJour=n;
	}
	
	public void setNbs_Pontes_Max(int n) {
		nbs_Pontes_Max-=n;
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
		return String.format("Reine a %3d jour(s) et %3d jour(s) d'esperance",getAge(),getEsperance());
	}
}