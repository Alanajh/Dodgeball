import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private Graphics g;
	
	public MouseInput() {
		
	}

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX(); /// mouse variable location for x
		int my = e.getY(); /// mouse variable location for y
		
		if (mx >= 200 && mx <= 359 && my >= 200 && my <= 283){
				///PLAY BUTTON //////
				try {Thread.sleep(200);} catch (InterruptedException e1) {e1.printStackTrace();}
				Main.state = Main.STATE.PLAY;
			}
		if (mx >= 400 && mx <= 744 && my >= 200 && my <= 283){
			 Main.state = Main.STATE.SCOREBOARD;///SCOREBOARD BUTTON //////
		}if (mx >= 865 && mx <= 935 && my >= 510 && my <= 538){
			Main.state = Main.STATE.MENU;  /// MENU BUTTON  //////
		}
		if(mx >= 972 && mx <= 1012 && my >= 490 && my <= 525 && Main.pauseOn == false){
			Main.state = Main.STATE.PAUSE;
			Main.pauseOn = true;
		}else if(mx >= 972 && mx <= 1012 && my >= 490 && my <= 525 && Main.pauseOn == true){
			Main.state = Main.STATE.PLAY;
			Main.pauseOn = false;
		}
	}
	
	public void mouseReleased(MouseEvent arg0) {}
	public void render(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRoundRect(200, 200, 159, 83, 25, 25);
	}
}
