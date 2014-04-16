import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;

public class Ball {

	private ImageIcon i;
	private Image ball;
	private Random r = new Random();
	private int x, y, xx, yy;
	int wall;
	
	public Ball() {
		
		i = new ImageIcon(getClass().getResource("lightningBall.png"));
		ball = i.getImage();
		
		x = r.nextInt(600);
		y = r.nextInt(400);
		 
	
	}
	public void move(){
		x += xx;
		y += yy;
		if (x < 11){ x = 11; }
		if(x >962){ x = 962; }
		if(y < 49){ y = 49;}
		if(y > 468){ y = 468; }
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
