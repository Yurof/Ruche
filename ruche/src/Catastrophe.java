import java.util.ArrayList;

public class Catastrophe {
	
	private static final int NB_SAISONS = 4;
	private static final int NB_CATASTROPHE_SAISON = 3;
	private String[][] Evenement;
	
	// CONSTRUCTEURS
	
	public Catastrophe(){
		Evenement = new String[NB_SAISONS][NB_CATASTROPHE_SAISON];
		Evenement[0][0]="Printemps";
		Evenement[0][1]="Pesticides";// touche abeilles
		Evenement[0][2]="Agriculture intensive"; // touche fleurs
		
		Evenement[1][0]="Ete";
		Evenement[1][1]="Frelons asiatiques"; // touche abeilles
		Evenement[1][2]="Recoltes";// touche miel
		
		Evenement[2][0]="Automne";
		Evenement[2][1]="Parasites"; // touche abeilles
		Evenement[2][2]="Maladies"; // touche abeilles
		
		Evenement[3][0]="Hiver";
		Evenement[3][1]="Grand froid"; // touche fleurs
		Evenement[3][2]="Pic-vert"; //touche abeilles et miel
		
	}
	
	// ACCESSEURS
	
	public String[][] getEvenement() {
		return Evenement;
	}
	
	// METHODES
	
	public String estCatastrophe(Saison s) {
		if(Math.random()<0.05) { // 5% de chance qu'une catastrophe apparait
			String saison = s.getSaison();
			int j=(int) (Math.random()*2+1);
		
			for(int x = 0; x < NB_SAISONS; x++) {
				if(Evenement[x][0].equals(saison)) {
					return Evenement[x][j];
				}
			}
		}
		return "";
	}
	
	public String ActiveCatastrophe(ArrayList<Abeille> la, ArrayList<Fleur> lf, Miel m,Saison s) {
		String marabout=estCatastrophe(s);
		System.out.println(marabout);
		
		if(marabout.equals("Pesticides")) { // Retire un quart de l'espérance de vie des abeilles 
			for(int i = 0 ; i < la.size() ; i ++) {
				la.get(i).setEsperance(la.get(i).getEsperance()*3/4);
			}
		}
		
		if(marabout.equals("Agriculture intensive")) {// Retire aléatoirement des fleurs 
			for(int i = 0 ; i < lf.size() ; i ++) {
				if(Math.random()<0.5) {
					lf.get(i).setQttPollen(0);
				}
			}
		}
		
		if(marabout.equals("Frelons asiatiques")) { // Tue aléatoirement des abeilles (sauf la reine)
			for(int i = 0 ; i < la.size() ; i ++) {
				if(la.get(i) instanceof Reine) {
					continue;
				}else{
					if(Math.random()<0.25)
						la.get(i).setEsperance(la.get(i).getAge());
				}
			}
		}
		
		if(marabout.equals("Recoltes")) { // Diminution de la quantité de miel dans la ruche
			m.enlever_miel((int)(m.getQttMiel()/2));
		}
		
		if(marabout.equals("Parasites")) { // Tue aléatoirement des abeilles (sauf la reine)
			for(int i = 0 ; i < la.size() ; i ++) {
				if(la.get(i) instanceof Reine) {
					continue;
				}else{
					if(Math.random()<0.25)
						la.get(i).setEsperance(la.get(i).getAge());
				}
			}
		}
		
		if(marabout.equals("Maladies")) {// Divise par 2 l'espérance de vie des abeilles
			for(int i = 0 ; i < la.size() ; i ++) {
				if(Math.random()<0.25) {
					la.get(i).setEsperance(la.get(i).getEsperance()/2);
				}
			}
		}
		
		if(marabout.equals("Grand froid")) { // Tue toutes les fleurs
			for(int i = 0 ; i < lf.size() ; i ++) {
					lf.get(i).setQttPollen(0);
			}
		}
		
		if(marabout.equals("Pic-vert")) { // Vol le miel, tue des abeilles ( sauf reine)
			for(int i = 0 ; i < la.size() ; i ++) {
				if(la.get(i) instanceof Reine) {
					continue;
				}else{
					if(Math.random()<0.25)
						la.get(i).setEsperance(la.get(i).getAge());
				}
			}
			m.enlever_miel((int)(m.getQttMiel()/4));
		}
		
		return marabout;
	}
	
}