
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;


public class Affichage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textField1, textField2, textField3;
	private JLabel quantite_miel,quantite_pollen,nbr_jours,nbr_reine,nbr_ouvriere,nbr_male;
	private JButton b1,b2;
	private JProgressBar p2,p3;
	private JFrame frame;
	private JPanel gridlayoutPanel;
	private JTextArea textArea,textArea2;
	private GestionRuche r;private Miel miel;private Saison saison;private Map map; private Catastrophe catastrophe;
	

	public Affichage() {
		miel= new Miel();
		r = new GestionRuche();
		saison = new Saison();
		map = new Map();
		catastrophe = new Catastrophe();

		frame= new JFrame(); 
		frame.setResizable(false);
		frame.setTitle("Gestion d'une ruche");
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);  
		frame.setLayout(new GridLayout(1, 2));
	    
		textArea = new JTextArea(map.mapPlanter(saison));
		textArea.setEditable(false);
		Font font = new Font("Verdana", Font.BOLD, 7);
		textArea.setFont(font);
		frame.getContentPane().add(textArea);
	    
	    gridlayoutPanel = new JPanel();
	    gridlayoutPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	    gridlayoutPanel.setBackground(new Color(255, 153, 51));
	    gridlayoutPanel.setLayout(new GridLayout(12, 2));
	    	    
	    textField1 = new JTextField("0");
	    textField2 = new JTextField("0");
	    textField3 = new JTextField("0");
	    nbr_jours=new JLabel("Jours: "+saison.getJour()+"    saison: "+saison.getSaison());
	    gridlayoutPanel.add(nbr_jours);
	    gridlayoutPanel.add(new JLabel("[ un male sacrifié-> 5oeufs ]"));
	    
	    nbr_reine=new JLabel("reine: "+r.cpt_reine()+ "    "+(r.JourRestantReine()-saison.getJour())+" jours /"+r.NbrOeufReine()+" oeufs");
	    gridlayoutPanel.add(nbr_reine);
	    gridlayoutPanel.add(textField1);
	    
	    nbr_ouvriere=new JLabel("ouvrières: "+r.cpt_ouvriere());
	    gridlayoutPanel.add(nbr_ouvriere);
	    gridlayoutPanel.add(textField2);
	    
	    nbr_male=new JLabel("mâles: "+r.cpt_fauxbourdon());
	    gridlayoutPanel.add(nbr_male);
	    gridlayoutPanel.add(textField3);
	    
	    gridlayoutPanel.add(new JLabel(""));
	    gridlayoutPanel.add(new JLabel(""));
	    
	    quantite_miel=new JLabel("quantité de miel: "+miel.getQttMiel()+"/"+Miel.getMaxMiel());
	    gridlayoutPanel.add(quantite_miel);
	    quantite_pollen=new JLabel("quantité de pollen:"+miel.getQttPollen()+"/"+Miel.getMaxPollen());
	    gridlayoutPanel.add(quantite_pollen);
	    gridlayoutPanel.add(new JLabel("ouvrières"));
	    
	    p2 = new JProgressBar();
	    p2.setValue(0);
	    p2.setForeground(new Color(74,158,209));
	    gridlayoutPanel.add(p2);
	      
	    gridlayoutPanel.add(new JLabel("mâles"));
	      
	    p3 = new JProgressBar();
	    p3.setValue(100);
	    p3.setForeground(new Color(74,158,209));
	    gridlayoutPanel.add(p3);
	    
	    b1=new JButton("valider");
	    gridlayoutPanel.add(b1);
	    b1.addActionListener(this);
	    b2=new JButton("recommencer");
	    gridlayoutPanel.add(b2);
	    b2.addActionListener(this);
	      
	    textArea2 = new JTextArea("commencez a jouer", 6, 8);
	    textArea2.setLineWrap(true);
	    textArea2.setWrapStyleWord(true);
	    
	    gridlayoutPanel.add(new JScrollPane(textArea2));
	    
	    frame.add(gridlayoutPanel,BorderLayout.EAST);
	    frame.setVisible(true);
	    
	    for(int i=0; i<r.getLA().size();i++) {// affichage des abeilles
	    	System.out.println(r.getLA().get(i).toString());
	   	}
		
		for(int j = 0;j<map.getListe().size();j++) { // affichage des fleurs
			System.out.println(map.getListe().get(j).toString());
		}
	}
	
	  public void actionPerformed(ActionEvent e) {
		  int a=0,b=0,c=0,test;
		  boolean erreur=false;
		  textArea2.setText("");		  
		  String s1=textField1.getText();  
		  String s2=textField2.getText();
		  String s3=textField3.getText();
		  System.out.println("boutton pressé");
		  try {
			  a=Integer.parseInt(s1); 
              b=Integer.parseInt(s2);  
              c=Integer.parseInt(s3);
              if(a<0||b<0||c<0) {
            	  textArea2.setText("entrez un nombre POSITIF svp");
            	  erreur=true;
              }
              
		  }catch ( NumberFormatException nfe  ) {
             textArea2.setText("entrez un nombre svp");
             erreur=true;
         }
         if(!erreur) {
	         if(e.getSource()==b1){
	        	System.out.println("z\n");
	        	test = r.reproduire(a,c,b,miel);
	        
	        	if (test==0||test==1||test==3||test==5||test==6) {
	        		if(test==0) 
	        			textArea2.setText("Vous depassez le quota de 25 oeufs par jours...");
	        		if(test==1) {
	        			frame.dispose();
	        			new FinDuJeu(3);
	        		}
	        		if(test==3) 
	        			textArea2.setText("Vous n'avez pas assez de miel pour réaliser ces actions...");
	        		if(test==5) 
	        			textArea2.setText("Vous avez depasser le quota maximum...");
	        		if(test==6) {
	        			textArea2.setText("Vous n'avez pas assez de mâle pour réaliser ces actions...");
	        		}
	        	}
	        	else {
	        		r.pondre(a,"Reine");
		        	r.pondre(b,"Ouvriere");
		        	r.pondre(c,"FauxBourdon");
	        		r.setLA(r.THEQueen());//reine unique
	         		r.bougeTousOuvrieres(map); // bouger les abeilles
	         		map.setListe(map.mourrir()); // supprime les fleurs
	         		r.setLA(r.mourrir()); // supprime les abeilles
	         		textArea2.setText(catastrophe.ActiveCatastrophe(r.getLA(),map.getListe(),miel,saison));
	                map.setListe(map.mourrir()); // supprime les fleurs
	                r.setLA(r.mourrir()); // supprime les abeilles
	                
	         		for(int i=0; i<r.getLA().size();i++) {// affichage des abeilles
	         			System.out.println(r.getLA().get(i).toString());
	         		}
	         		
	         		/*for(int j = 0;j<map.getListe().size();j++) { // affichage des fleurs
	        			System.out.println(map.getListe().get(j).toString());
	        		}*/
	         		
	         		r.tousManger(saison, miel);//manger
	         		saison.jourSuivant(r.getLA());// jour suivant
	         		quantite_pollen.setText("quantité de pollen: "+miel.getQttPollen()+"/"+Miel.getMaxPollen());
	         		quantite_miel.setText("quantité de miel: "+miel.getQttMiel()+"/"+Miel.getMaxMiel());
	         		nbr_jours.setText("Jours: "+saison.getJour()+"    saison: "+saison.getSaison());
	         		nbr_reine.setText("reine: "+r.cpt_reine()+ "    "+r.JourRestantReine()+" jours /"+r.NbrOeufReine()+" oeufs");
	         		nbr_ouvriere.setText("ouvrières: "+r.cpt_ouvriere());
	         		nbr_male.setText("mâles: "+r.cpt_fauxbourdon());
	         		
	         		if(r.cpt_fauxbourdon()!=0 ) {
	      	    	   p3.setValue(100*r.cpt_fauxbourdon()/(r.cpt_fauxbourdon()+r.cpt_ouvriere()));
	      	    	   p2.setValue(100*r.cpt_ouvriere()/(r.cpt_fauxbourdon()+r.cpt_ouvriere()));
	         		}else {
	      	    	   frame.dispose();
	      	    	   new FinDuJeu(1);
	      	       }
	         		if(miel.getQttMiel()==0) {
	         			frame.dispose();
	         			new FinDuJeu(2);
	         		}
	         		if(r.JourRestantReine()==0) {
	         			frame.dispose();
	         			new FinDuJeu(4);
	         		}
	         		textArea.setText(map.mapPlanter(saison));
	         		r.tousDeposer(miel);
	         		map.setListe(map.mourrir());
	         		miel.transformation() ;
	         		saison.majSaison();
	         		
	         		if(saison.getJour()==250)
		        		new WinterIsComing(1);
		        	if(saison.getJour()==275)
		        		new WinterIsComing(2);
	         		
	        		}
	         	}
	         
	         if(e.getSource()==b2){
	        	 frame.dispose();
	        	 new ReglesDuJeu();
	         }
         }         
     }
}