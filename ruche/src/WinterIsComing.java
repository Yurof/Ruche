import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class WinterIsComing extends JFrame implements ActionListener {	
	private static final long serialVersionUID = 1L;
	JButton b1;
	JFrame frame;

	public WinterIsComing (int i) {
		
		frame= new JFrame(); 
		frame.setResizable(false);
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);  
	    
	    if(i==1)
	    	b1=new JButton(new ImageIcon("WinterIsComing.jpg"));
	    if(i==2) {
	    	b1=new JButton(new ImageIcon("WinsterIsHere.jpg"));
	    	frame.setSize(670, 350);
	    }
	    frame.add(b1);
	    b1.addActionListener(this);
	    frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
    }
}
