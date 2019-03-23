package ruche;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class T extends JFrame {
    public T() {/*blabla*/
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(350, 200);
        HumanProgressBar p = new HumanProgressBar();
        p.setValue(50);
        p.setBounds(15, 15, 300, 15);
        this.add(p);
        HumanProgressBar p2 = new HumanProgressBar();
        p2.setValue(20);
        p2.setBounds(15, 50, 300, 15);
        this.add(p2);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new T();
            }
        });
    }
}