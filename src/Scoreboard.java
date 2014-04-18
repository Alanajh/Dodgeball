import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Scoreboard {

	private int scoreX;
	private int scoreY;
	private int scoreSpeed;
	private ImageIcon iMenu, iHelp;
	private Image menuButtonImg, helpButtonImg;
	private Font fntScore = new Font("serif", Font.BOLD, 52);
	
	public Scoreboard() {
		scoreX = 300;
		scoreY = 100;
		scoreSpeed = -1;
		
		iMenu = new ImageIcon(getClass().getResource("menuButton.png"));
		menuButtonImg = iMenu.getImage();
		
		iHelp = new ImageIcon(getClass().getResource("helpButton.png"));
		helpButtonImg = iHelp.getImage();
		
	}
	public void update(){
		scoreX = scoreSpeed;
		if(scoreX < 200){ 
			scoreSpeed = -scoreSpeed; 
			scoreX = 200;
		}
		if(scoreX > 400){ 
			scoreSpeed = -scoreSpeed; 
			scoreX = 400;
		}
	}
	public void paint(Graphics g){
		/// ------ SCOREBOARD SCREEN ----- ////////
		g.setFont(fntScore);
		g.fillRect(5, 5, 1033, 540);
		
		g.setColor(Color.ORANGE);
		g.drawString("SCOREBOARD!!", scoreX, scoreY);
	
	}
	public int getScoreX(){
		return scoreX;
	}
	public int getScoreY(){
		return scoreY;
	}
	public Image getMenu(){
		return menuButtonImg;
	}
	public Image getHelp(){
		return helpButtonImg;
	}
}
