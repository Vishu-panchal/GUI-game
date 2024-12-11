
import javax.swing.JFrame;

public class AppFrame extends JFrame {
    AppFrame() {
        setTitle("SEC B");
        setSize(700, 500);
        setLocationRelativeTo(null);
        AppPanel aPanel = new AppPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(aPanel);
        setVisible(true);

        
    }
}
