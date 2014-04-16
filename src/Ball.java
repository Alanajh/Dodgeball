import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;

public class Ball {

	private ImageIcon i;
	private Image ball;
	private Random r = new Random();
	private int x, y, xx, yy;
	
	public Ball() {
		
		i = new ImageIcon(getClass().getResource("lightningBall.png"));
		ball = i.getImage();
		
		x = r.nextInt(600);
		y = r.nextInt(400);
	
	}
	public void move(){
		x += xx;
		y += yy;
		if (x < 7){ x = 7; }
		if(x >880){ x = 880; }
		if(y < 45){ y = 45;}
		if(y > 385){ y = 385; }
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return ball;
	}
	public void keyPressed(KeyEvent e){

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP){ yy = -2;}
		if(key == KeyEvent.VK_DOWN){ yy = 2;}
		if(key == KeyEvent.VK_LEFT){ xx = -1;}
		if(key == KeyEvent.VK_RIGHT){ xx = 1;}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){ xx = 0;}
		if (key == KeyEvent.VK_RIGHT){ xx = 0;}
		if (key == KeyEvent.VK_UP){yy = 0;}
		if (key == KeyEvent.VK_DOWN){yy = 0;}
	}
	public void keyTyped(KeyEvent e) {}
}
