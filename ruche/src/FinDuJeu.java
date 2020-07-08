import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class FinDuJeu extends JFrame implements ActionListener {	
	private static final long serialVersionUID = 1L;
	JButton b1;
	JFrame frame;

	public FinDuJeu (int nbr_erreur) {
		String s="Vous avez perdu car ";
		if(nbr_erreur==1)
			s+="vous n'avez plus de mâle pour faire prosperer votre royaume...";
		if(nbr_erreur==2)
			s+="vous n'avez plus de miel pour alimenter votre royaume...";
		if(nbr_erreur==3)
			s+="votre reine n'est plus capable de pondre des oeufs...";
		if(nbr_erreur==4)
			s+="votre reine est morte...";

		frame= new JFrame(); 
		frame.setResizable(false);
	    frame.setSize(900, 500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);  
	    frame.setLayout(new GridLayout(1, 2));

	    JTextArea textArea = new JTextArea(s);
		textArea.setEditable(false);
		Font font = new Font("Verdana", Font.BOLD, 13);
		textArea.setFont(font);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		frame.getContentPane().add(new JScrollPane(textArea));
	    
	    JPanel gridlayoutPanel = new JPanel();
	    gridlayoutPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	    gridlayoutPanel.setBackground(new Color(255, 153, 51));
	    gridlayoutPanel.setLayout(new GridLayout(2, 2));
	    
	    b1=new JButton("RECOMMENCER");
	    gridlayoutPanel.add(b1);
	    b1.addActionListener(this);
	    frame.add(gridlayoutPanel,BorderLayout.EAST);
	    frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new Affichage(); 
	}
          
}

