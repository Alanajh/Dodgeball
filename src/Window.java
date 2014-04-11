import java.awt.Color;
import javax.swing.JFrame;

public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	public Window() {

		this.add(new Main());
		
		setBackground(Color.BLUE);
		setSize(1044, 575);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("THINGS && STUFF && MORE");
		setLocationRelativeTo(null);
	}
	public static void main(String args[]){
		new Window();
	}
}
