import java.util.ArrayList;

public class GestionRuche {
	
	// ATTRIBUTS
	private static final int SPZ = 5;
	private ArrayList<Abeille> la;
	
	// CONSTRUCTEURS
	
	public GestionRuche() { // Commence le jeu avec une Reine et 5 mâles
		la= new ArrayList<Abeille>();
		la.add(new Reine());
		la.add(new FauxBourdon());
		la.add(new FauxBourdon());
		la.add(new FauxBourdon());
		la.add(new FauxBourdon());
		la.add(new FauxBourdon());
	}
	
	// ACCESSEURS
	
	public ArrayList<Abeille> getLA(){	
		return la;	
	}
	
	// CONSTRUCTEURS
	
public void setLA(ArrayList<Abeille> la){
		this.la=la;
	}
	
	// METHODES
	public int cpt_reine() {
		int cpt=0;
		for(int i=0;i<la.size();i++) {
			if(la.get(i) instanceof Reine) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int cpt_ouvriere() {
		int cpt=0;
		for(int i=0;i<la.size();i++) {
			if(la.get(i) instanceof Ouvriere) {
				cpt++;
			}
		}
		return cpt;
	}
	public int cpt_fauxbourdon() {
		int cpt=0;
		for(int i=0;i<la.size();i++) {
			if(la.get(i) instanceof FauxBourdon) {
				cpt++;
			}
		}
		return cpt;
	}
	
    public ArrayList<Abeille> THEQueen() {// L'unicité de la reine
        int cpt = cpt_reine();// Nombre de Reine dans la ruche
        int max_jour=0;
        boolean unique=false;
        ArrayList<Abeille> nvx = new ArrayList<Abeille>();
        if(cpt>1) { // Si il existe plusieur Reine           
            for(int i = 0; i<la.size();i++) {// Recherche de la Reine avec le plus d'esperance
                if(la.get(i) instanceof Reine) {
                    if(la.get(i).getEsperance()-la.get(i).getAge()>max_jour) {
                        max_jour=la.get(i).getEsperance()-la.get(i).getAge();
                    }
                }
            }
           
            for(int i = 0; i<la.size() ; i++){ // Copie de la nouvelle liste d'abeille avec The Queen
                if(la.get(i) instanceof Reine){
                    if(la.get(i).getEsperance()-la.get(i).getAge()==max_jour && unique==false) {
                        nvx.add(la.get(i));
                        unique=true;
                    }else {
                        continue;
                    }
                }else {
                    nvx.add(la.get(i));
                }
            }
            return nvx;
        }
        return la;   
    }

	public int JourRestantReine() {
		for(int i = 0; i<la.size();i++) {
			if(la.get(i) instanceof Reine) 
				return la.get(i).getEsperance()-la.get(i).getAge();
		}
		return 0;
	}
	
	public int NbrOeufReine() {
		for(int i = 0; i<la.size();i++) {
			if(la.get(i) instanceof Reine) 
				return ((Reine)la.get(i)).getNbMax();
		}
		return 0;
	}
	
	public ArrayList<Abeille> mourrir(){
		ArrayList<Abeille> nvx= new ArrayList<Abeille>();
		for(int i = 0; i<la.size() ; i++){
			if(la.get(i).getEsperance()-la.get(i).getAge()>0){
				nvx.add(la.get(i));
			}
		}
		return nvx;
	}
	
	
	
    public int est_reproductible(int nb_male) {
        int cpt=0; // Compteur du nombres de mâles à sacrifier pour avoir des oeufs
        for(int i = 0; i<la.size();i++) { // Parcours la liste des abeilles
            if(la.get(i) instanceof FauxBourdon){// Si c'est un faux-bourdon
                cpt++;
            }
            if(cpt==nb_male){ // Si il y a assez de mâle
                return 4;
            }           
        }// Si on sort de la boucle alors cela veut dire que l'on as pas assez d'abeilles mâles
        System.out.println("Le nombre de male est insuffisant...");
        return 6;
    }
   
    public int reproduire (int nb_reine, int nb_fauxbourdon, int nb_ouvriere, Miel m){
        int nb_oeufs = nb_reine+nb_fauxbourdon+nb_ouvriere; //  Nombre d'oeuf que l'on veut produire
        if(nb_oeufs>Abeille.QUOTA_PONTE_JOUR) { // Verifie que ca dépasse pas le quota max par jour
            System.out.println("Vous avez depassé le nombre de quota de ponte par jour... soit "+Abeille.QUOTA_PONTE_JOUR+" oeufs.");
            return 0;
        }
        for(int i =0;i<la.size();i++){// Parcours la liste des abeilles
            if(la.get(i) instanceof Reine) {
                if(nb_oeufs>((Reine)la.get(i)).getNbMax()) { // Verifie que ca dépasse pas le quota max de la Reine
                    System.out.println("Vous avez depassé le nombre de quota de ponte maximum restant de la Reine... soit "+((Reine)la.get(i)).getNbMax()+" oeufs.");
                    return 1;
                }
            }
        }
       
        int cpt=0; // Compteur du nombre de mâle à sacrifié
        while(nb_oeufs>SPZ){
            cpt++;
            nb_oeufs-=SPZ;
        }
       
        if(nb_oeufs>0) 
            cpt++;// Fin du comptage
        if(est_reproductible(cpt)==4) { // Verifie si la reproduction est possible
            int retrait =nb_reine*Abeille.MIEL_OEUF_REINE + nb_fauxbourdon*Abeille.MIEL_OEUF_FAUXBOURDON + nb_ouvriere*Abeille.MIEL_OEUF_OUVRIERE;
            // Nombre de miels consommés pour la création des oeufs
            for(int i =0;i<la.size();i++){ // Parcours la liste des abeilles
                if(la.get(i) instanceof Reine) { // Verifie que c'est la reine
                    ((Reine)la.get(i)).setOeufParJour(cpt*SPZ); // Modifie le nombre d'oeufs que la reine va pondre aujourd'hui
                    if(((Reine)la.get(i)).getOeufParJour()>=0) {//Si on a pas d'oeufs à pondre encore disponible
                        if(m.enlever_miel(retrait)){ // Si on a assez de miel
                            if(((Reine)la.get(i)).getOeufParJour()>0) {
                                ((Reine)la.get(i)).setNbs_Pontes_Max(((Reine)la.get(i)).getOeufParJour()); // MAJ du nombre max que la Reine peut encore pondre
                            }
                            for(int j = 0; j<la.size();j++) {// Parcours de la liste d'abeille
                                if(cpt>0){ // Si il reste un nombre de mâle à sacrifier
                                    if(la.get(j) instanceof FauxBourdon) { // Si c'est un mâle
                                        la.get(j).setEsperance(la.get(j).age); // Le mâle a épuisé toute sa vitalité
                                        cpt--;
                                    }
                                }else{
                                    break;
                                }
                            }
                            return 2;
                        }
                        return 3;
                    }
                    return 5;
                }
            }
        }
        return 6;
    }
   

    public void pondre(int nb,String nom){
        for(int i = 0 ; i < nb ; i ++) {
            if(nom.equals("Reine")) {
                if(Math.random()>0.01) {
                    la.add(new Reine());
                }else {
                    System.out.println("Héra a emporté ta nouvelle Reine...");
                }
            }else{
                if(nom.equals("Ouvriere")) {
                    if(Math.random()>0.01) {
                        if(Math.random()<0.001) {
                            la.add(new SuperOuvriere());
                        }else {
                            la.add(new Ouvriere());
                        }
                    }else {
                        System.out.println("Déméter a emporté ta nouvelle ouvrière...");
                    }
                }else{
                    if(nom.equals("FauxBourdon")) {
                        if(Math.random()>0.01) {
                            la.add(new FauxBourdon());
                        }else {
                            System.out.println("Apollon a emporté ton nouveau mâle...");
                        }
                    }
                }
            }
        }       
    }
	
	public void butiner2(Abeille A,Fleur F) {
		int butine = (int) (Math.random()*(Abeille.RANDOM_BUTINER*(1.0+Abeille.DISPERTION/2.0)-Abeille.RANDOM_BUTINER*(1.0-Abeille.DISPERTION/2.0))+Abeille.RANDOM_BUTINER*(1.0-Abeille.DISPERTION/2.0));
		if(A instanceof SuperOuvriere){
			butine*=5;
			if(((SuperOuvriere)A).getPollen()+butine>Abeille.MAX_POLLEN_PORTABLE) {// Si l'ouvrière collecte plus que ce qu'elle peut porter..
				butine = Abeille.MAX_POLLEN_PORTABLE -((Ouvriere)A).getPollen(); // l'ouvrière ne prend que ce qu'elle peut porter
				((SuperOuvriere)A).setPollen((F.enlever_pollen(butine))); // l'ouvrière récolte un nombre aléatoire de pollen entre un certains intervalles
			}else {
				((SuperOuvriere)A).setPollen((F.enlever_pollen(butine))); // l'ouvrière récolte un nombre aléatoire de pollen entre un certains intervalles
			}
		}else {
			if(((Ouvriere)A).getPollen()+butine>Abeille.MAX_POLLEN_PORTABLE) {// Si l'ouvrière collecte plus que ce qu'elle peut porter..
				butine = Abeille.MAX_POLLEN_PORTABLE -((Ouvriere)A).getPollen(); // l'ouvrière ne prend que ce qu'elle peut porter
				((Ouvriere)A).setPollen((F.enlever_pollen(butine))); // l'ouvrière récolte un nombre aléatoire de pollen entre un certains intervalles
			}else {
				((Ouvriere)A).setPollen((F.enlever_pollen(butine))); // l'ouvrière récolte un nombre aléatoire de pollen entre un certains intervalles
			}
		}
	}
	
	public void bougeTousOuvrieres(Map m){
		for(int a = 0; a < la.size(); a++) {// Parcours la liste des abeilles
			if(la.get(a) instanceof Ouvriere){ // verifie que c'est une ouvrière
				try {
					if(m.getListe().get(m.rangFleurProche(a,la)).getQttPollen()>0){ // Verifie que la fleur la plus proche à du pollen
						((Ouvriere)la.get(a)).bouger(m.getListe().get(m.rangFleurProche(a,la)).getP()); // bouge l'ouvrière vers la fleur la plus proche avec du pollen
						butiner2(la.get(a),m.getListe().get(m.rangFleurProche(a,la))); // butine la fleur
					}
				}catch(Exception e) {
					break;
				}
			}else{
				continue; // Si c'est pas une ouvrière on passe à l'abeille suivante
			}
		}
	}
	
	public void tousManger(Saison s, Miel m) {
		for(int i = 0; i < la.size(); i ++) {
			la.get(i).manger(s,m);
		}
	}
	
	public void tousDeposer(Miel mi) {
		for(int i=0;i<la.size();i++) {
			if(la.get(i) instanceof Ouvriere) {
				((Ouvriere)la.get(i)).deposer_pollen(mi);
			}
		}
	}

}