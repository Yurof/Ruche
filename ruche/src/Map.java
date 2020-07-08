import java.util.ArrayList;

public class Map {
	
	// ATTRIBUTS
	
	protected static final int DIMENSION = 50;
	private double TX_APPARITION = 0.009;
	private ArrayList<Fleur> lf;
	
	// CONSTRUCTEURS
	
	public Map(){
		lf = new ArrayList<Fleur>();
	}
	
	// ACCESSEURS
	
	public ArrayList<Fleur> getListe(){
		return lf;
	}
		
	// MODIFICATEURS
	
	public void setListe(ArrayList<Fleur> lf){
		this.lf=lf;
	}
	public void setTauxApparition(double nvx) {
        TX_APPARITION = nvx;
    }
	// METHODES
	
	public boolean estPlanter(int x, int y){ // verifie qu'il y a une plante en position x,y
		for(int i = 0 ; i< lf.size() ; i++){
			if(lf.get(i).getX()==x && lf.get(i).getY()==y){
				return true;
			}
		}
		return false;
	}
	
	public double verifie_saison(Saison saison) {
        double taux;
        if(saison.getSaison().equals("Ete")){
            taux=0.01;
            return taux;
        }else {
            if(saison.getSaison().equals("Printemps")) {
                taux=0.009;
                return taux;
            }else{
                if(saison.getSaison().equals("Automne")) {
                    taux=0.009;
                    return taux;
                }else {
                    taux=0;
                    return taux;
                }
            }
        }
    }
	
	public String mapPlanter(Saison saison){ // Plante des fleurs sur la carte
        String s = "";
        TX_APPARITION=verifie_saison(saison);
        for(int i = 0; i<DIMENSION ; i++){
            for(int j = 0; j<DIMENSION ; j++){
                if(i==DIMENSION/2 && j==DIMENSION/2) {
                    s+="R  ";
                }else {
                    if(estPlanter(i,j)) {
                        s+="\u26B5  ";
                    }else {
                        if(!estPlanter(i,j) && Math.random()<TX_APPARITION){
                            s+="\u26B5  ";
                            lf.add(new Fleur(i,j));
                        }else{
                            s+=".   ";
                        }
                    }
                }
            }
            s+="\n";
        }
        return s;
    }
	
	public ArrayList<Fleur> mourrir(){
		ArrayList<Fleur> nvx= new ArrayList<Fleur>();
		for(int i = 0; i<lf.size() ; i++){
			if(lf.get(i).getQttPollen()!=0){
				nvx.add(lf.get(i));
			}
		}
		return nvx;
	}

	public int rangFleurProche(int index,ArrayList<Abeille> la) {
		int ind=-1;
		double tmp_dist=Map.DIMENSION*Map.DIMENSION;
		for(int a=0;a<lf.size();a++) {
			if(((Ouvriere) la.get(index)).getP().distanceTo(lf.get(a).getP())<tmp_dist  && lf.get(a).getQttPollen()>0) {
				tmp_dist=((Ouvriere) la.get(index)).getP().distanceTo(lf.get(a).getP());
				ind=a;
			}	
		}
		return ind;
	}
	
}
