import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Menu {

	private int titleX, titleY;
	private ImageIcon iCourt, iPlay, iScore;
	private Image courtImage, playButtonImg, scoreboardButtonImg, helpButtonImg;
	private Font fntMenu = new Font("serif", Font.BOLD, 112);
	
	public Menu() {
		
		titleX = 150;
		titleY = 130;
		
		iCourt = new ImageIcon(getClass().getResource("dodgeCourt.png"));
		courtImage = iCourt.getImage();
		 
		iScore = new ImageIcon(getClass().getResource("ScoreboardButton.png"));
		scoreboardButtonImg = iScore.getImage();
		 
		iPlay = new ImageIcon(getClass().getResource("playButton.png"));
		playButtonImg = iPlay.getImage();
	}
	public void update(){
		
	}
	public void paint(Graphics g){
		
		g.drawImage(courtImage, 10, 150, null);
		g.setFont(fntMenu);
		g.setColor(Color.ORANGE);
		g.drawString("DODGEBALL", titleX, titleY);
		
		g.drawImage(playButtonImg, 200, 200, null); // 159 x 83
		g.drawImage(scoreboardButtonImg, 400, 200, null); /// 344 x 83
		g.drawImage(helpButtonImg, 960, 510, null); //  57 x 29
		
	}

}
