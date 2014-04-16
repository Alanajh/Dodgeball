import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{

	Ball ball;
	
	public KeyInput() {
		ball = new Ball();
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_DOWN){
			System.out.println("Going down");
		}
	}
	public void keyReleassed(KeyEvent e){
		
	}

}
