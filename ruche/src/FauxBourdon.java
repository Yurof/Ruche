public class FauxBourdon extends Abeille{
	
	// CONSTRUCTEURS
	
	public FauxBourdon(){ // Constructeur de la classe FauxBourdon
		super(1,(int) (Math.random()*((ESPERANCE_BOURDON*(1.0+DISPERTION/2.0)-ESPERANCE_BOURDON*(1.0-DISPERTION/2.0)))+ESPERANCE_BOURDON*(1.0-DISPERTION/2.0)),"FauxBourdon");
		//Vie entre 7 et 10 jours.
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
		return String.format("FauxBourdon a %3d jour(s) et %3d jour(s) d'esperance",getAge(),getEsperance());
	}
}