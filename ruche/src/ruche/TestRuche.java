package ruche;
import java.util.Scanner;
public class TestRuche {

	public static void main(String[] args) {
		System.out.println("entrez le nombre d'ouvrier:");
		Scanner sc = new Scanner(System.in);
	    int i = sc.nextInt();
	    
	    System.out.println("entrez le nombre de male:");
	    Scanner sc2 = new Scanner(System.in);
	    int j = sc2.nextInt();
	    sc.close();
	    sc2.close();
		Ruche r=new Ruche(i,j);
		System.out.println(r);

	}

}
