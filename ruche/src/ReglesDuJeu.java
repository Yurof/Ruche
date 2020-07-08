import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.*;
import javax.swing.*;

public class ReglesDuJeu extends JFrame implements ActionListener {	

	private static final long serialVersionUID = 1L;
	JButton b1;
	JFrame frame;

	public ReglesDuJeu () {

		frame= new JFrame(); 	
		frame.setResizable(false);
		frame.setTitle("Gestion d'une ruche");
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);  
		frame.setLayout(new GridLayout(1, 2));
	
	    JTextArea textArea = new JTextArea(LireText.Lecture());
		textArea.setEditable(false);
		Font font = new Font("Verdana", Font.BOLD, 13);
		textArea.setFont(font);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		frame.getContentPane().add(new JScrollPane(textArea));
	    
	    JPanel gridlayoutPanel = new JPanel();
	    gridlayoutPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	    gridlayoutPanel.setBackground(new Color(255, 153, 51));
	    gridlayoutPanel.setLayout(new GridLayout(2, 2));
	    
	    b1=new JButton(new ImageIcon("hornet-hive.png"));  
	    gridlayoutPanel.add(b1);
	    b1.addActionListener(this);

	    frame.add(gridlayoutPanel,BorderLayout.EAST);
	    frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
         frame.dispose();
         new Affichage();
    }
	  
	public static class LireText{
	
		public static String Lecture() {
			String s="",line;
			try {	            
	            FileReader fileReader = new FileReader("Text.txt");
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {   
	                s+=line+"\n";
	            }   
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Impossible d'ouvrir le fichier ");                
	        }
	        catch(IOException ex) {
	            System.out.println("erreur a l'ouverture du fichier");                  
	        }
			return s;
		} 
	}
              
}
