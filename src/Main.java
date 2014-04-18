
////// GAME TEST FOR A DODGEBALL-LIKE GAME, where you must tag out as many people as fast as you can.
//// need different backgrounds (gym, parking lot, concrete playground, padded room. 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;


import javax.swing.JPanel;

public class Main extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private Thread animator;
	private boolean running = false;
	private boolean gameOver = true;
	private boolean collision = false;
	
	////   size of panel  && and other gameboard stuff  ///////////
	private int WIDTH = 1024;
	private int HEIGHT = 530;
	
	private Font gameFont = new Font("Serif", Font.BOLD, 14);
	
	// variables for off-screen rendering
	private Graphics g;
	private Image image;
	private Image dodgeballImg, courtImage, scoreImage, playButtonImg, scoreboardButtonImg, menuButtonImg, helpButtonImg;
	private BufferedImage backbuffer;
	private Point pos = new Point(75, 15);
	
	private int currentFrame = 0;
	private int totalFrames = 1;
	private int animationDirection = 1;
	private int frameCount = 0;
	private int frameDelay = 4;
	
	// CHARACTER STUFF //
	private Random ranNerd = new Random();
	private Random ranJock = new Random();
	private Random ranHip = new Random();
	
	private int xNerd = ranNerd.nextInt(600) + 25 ;
	private int yNerd = ranNerd.nextInt(450) + 25 ;
	private double xNerdSpeed = 1;
	private double yNerdSpeed = 1;
	private int nerdScoreX = 20;
	private int nerdScoreY = 10;
	private int nerdCount = 0;
	
	private int xJock = ranJock.nextInt(949) + 25 ;
	private int yJock = ranJock.nextInt(450) + 25 ;
	private float xJockSpeed = -1;
	private float yJockSpeed = -1;
	private int jockScoreX = 220;
	private int  jockScoreY = 10;
	private int jockCount = 0;
	
	private int xHip = ranHip.nextInt(949) + 25 ;
	private int yHip = ranHip.nextInt(450) + 25 ;
	private float xHipSpeed = -1;
	private float yHipSpeed = -1;
	private int hipScoreX = 420;
	private int hipScoreY = 10;
	private int hipCount = 0;
	
	private int rectX = xNerd + 5; 
	private int rectY = yNerd + 12;
	private int recX = xJock + 20;
	private int recY = yJock + 5;
	private int rX = xHip + 18;
	private int rY = yHip + 16;
	
	//// ---- collision detection rectangles ---- ///////
	private Rectangle nerdRect;
	private Rectangle jockRect;
	private Rectangle hipRect;
	/// ---- pause button states ----  ///////
	public static boolean pauseOn = false;
	
	private Scoreboard score;
	private Menu menu;
	///  ----- ball collision detection ----- /////
	private Ball ball;
	private RoundRectangle2D ballRect;
	
	public static STATE state;

	public static enum STATE {MENU, PLAY, SCOREBOARD, PAUSE}
	///////   CONSTRUCTOR   ///////////////////
	public Main(){
		//  create game components here //////////
		backbuffer = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		backbuffer.createGraphics();
		////  Load the animation strip   //////////
		Toolkit tk = Toolkit.getDefaultToolkit();
		dodgeballImg = tk.getImage(getURL("dodgeChar.png")); // Spritesheet for game characters
		courtImage = tk.getImage(getURL("dodgeCourt.png")); // Image of gameboard.
		scoreImage = tk.getImage(getURL("scoreboardSprites.png")); // Scoreboard images of characters.
		
		playButtonImg = tk.getImage(getURL("playButton.png"));
		scoreboardButtonImg = tk.getImage(getURL("ScoreboardButton.png"));
		menuButtonImg = tk.getImage(getURL("menuButton.png"));
		helpButtonImg = tk.getImage(getURL("helpButton.png"));
		
		state = STATE.MENU;
		this.addMouseListener(new MouseInput());
		this.addKeyListener(new TAdapter());
		setFocusable(true);
		
		nerdRect = new Rectangle(rectX, rectY, 40, 53);
		jockRect = new Rectangle(recX, recY, 40, 65);
		hipRect = new Rectangle(rX, rY, 39, 61);
		
		ball = new Ball();
		score = new Scoreboard();
		menu = new Menu();
		
		animator = new Thread(this);
		animator.start();
	}
		private URL getURL(String filename) {
			URL url = null;
			try {
			url = this.getClass().getResource(filename);
			}
			catch (Exception e) {}
			return url;
			}
		public void addNotify(){
			// wait for the panel to be added to the JFrame before starting..../////
		/////  addNotify() is where the splash screen goes (???? I think ????)  /////
			super.addNotify(); // creates the peer.
			//System.out.println("The screen is trying to open, dude.");
			startGame(); // starts the thread.
	}
	private void startGame(){
		if (animator == null || running){
			animator = new Thread(this);
			animator.start();
		}
	}
	public void stopGame(){
		/////   if the user stops the game   ///////////
	
		running = false;
		
	}
	public void run() {
	
		running = true;
		while(running){
			update(); // update game state
			render(); // render to a buffer
			repaint(); // paint with the buffer
		}
		try {
			Thread.sleep(20); // sleep for a bit
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0); // Exit the running loop
	}
	public void update(){
		//if (!gameOver){}
	}
	public void render(){
		/////   draw current frame to an image buffer
		if(image == null){   ////// create the buffer
			image = createImage(WIDTH, HEIGHT);
			if (image == null){
				return;
			}else
				g = image.getGraphics();
		}
		/////  the background   ///////////
		g.drawImage(courtImage, 15, 50, null);
		// gym court set to 1024 x 500  /////
		
		//////////   DRAW GAME ELEMENTS   //////////////
		
		/// action happens here. //////
		// NERD ACTIONS //
	
		if(state == STATE.PLAY){
			xNerd += xNerdSpeed;
			yNerd += yNerdSpeed;
		
			if(xNerd < 5){
				xNerdSpeed = -xNerdSpeed;
				xNerd = 5;
			}if(xNerd > 959){
				xNerdSpeed = -xNerdSpeed;
				xNerd = 959;
			}if(yNerd < 30){
				yNerdSpeed = -yNerdSpeed;
				yNerd = 30;
			}if(yNerd > 465){
				yNerdSpeed = -yNerdSpeed;
				yNerd = 465;
			}
		
		/////  JOCK ACTIONS  ////
		xJock += xJockSpeed;
		yJock += yJockSpeed;
		
		if(xJock < 5){
			xJockSpeed = -xJockSpeed;
			xJock = 5;
		}if(xJock > 959){
			xJockSpeed = -xJockSpeed;
			xJock = 959;
		}if(yJock < 30){
			yJockSpeed = -yJockSpeed;
			yJock = 30;
		}if(yJock > 462){
			yJockSpeed = -yJockSpeed;
			yJock = 462;
		}
		
		////   HIPSTER ACTION  ///////
		
		xHip += xHipSpeed;
		yHip += yHipSpeed;
		
		if(xHip < 5){
			xHipSpeed = -xHipSpeed;
			xHip = 5;
		}if(xHip > 959){
			xHipSpeed = -xHipSpeed;
			xHip = 959;
		}if(yHip < 30){
			yHipSpeed = -yHipSpeed;
			yHip = 30;
		}if(yHip > 460){
			yHipSpeed = -yHipSpeed;
			yHip = 460;
		}
		
		score.update();
		ball.move();
		}
		if (state == STATE.PAUSE){}
				
		frameCount++;
		if(frameCount > frameDelay){
			frameCount = 0;
			currentFrame += animationDirection;
			if(currentFrame > totalFrames - 1){
				currentFrame = 0;
				pos.x = 75;
				pos.y = 15;
			}else if (currentFrame < 0){
				currentFrame = totalFrames - 1;
			}
		}
		
		if(gameOver)
			gameOverMessage(g);
	} //////   END OF RENDER   ///////////
	private void gameOverMessage(Graphics g){
		g.drawString("Game Over!", 0, 0);
	} ///   end of gameOverMessage()  ///////
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (image != null){
			if(state == STATE.MENU){
				/// ----- GAME MENU ------- ////////
				menu.paint(g);
			}
			if(state == STATE.SCOREBOARD){
				/// ------ SCOREBOARD SCREEN ----- ////////
				score.paint(g);
				g.drawImage(score.getMenu(), 865, 510, null);
				g.drawImage(score.getHelp(), 960, 510, null);

			}
			if(state == STATE.PLAY || state == STATE.PAUSE){
				Rectangle nerd = new Rectangle(xNerd + 20, yNerd + 12, 40, 53);
				Rectangle jock = new Rectangle(xJock + 20, yJock + 6, 40, 65);
				Rectangle hip = new Rectangle(xHip + 18, yHip + 14, 39, 61);
				
				g.drawImage(image, 0, 0, this);
			
				g.setFont(gameFont);
				g.setColor(Color.BLACK);
				g.drawString("NERDS: \t " + nerdCount, 80, 35);
				g.drawString("JOCKS: \t " + jockCount, 280, 35);
				g.drawString("HIPSTERS: \t " + hipCount, 480, 35);
			
				//// ----- SCORE IMAGES  ------ //////
				drawFrame(scoreImage, g, nerdScoreX, nerdScoreY, 1, 0, 50, 46); // nerd
				drawFrame(scoreImage, g, jockScoreX, jockScoreY, 1, 1, 50, 48); // jock
				drawFrame(scoreImage, g, hipScoreX, hipScoreY, 1, 2, 50, 50); // hipster
			
				drawFrame(dodgeballImg, g, xNerd, yNerd, 1, 0, 84, 80);
				drawFrame(dodgeballImg, g, xJock, yJock, 1, 1, 84, 80);
				drawFrame(dodgeballImg, g, xHip, yHip, 1, 2, 84, 80); 
				
				g.drawImage(ball.getImage(), ball.getX(), ball.getY(), 64, 64, this);
				g.drawRoundRect(ball.getX() + 3, ball.getY() + 3, 56, 57, 80, 80);
				
				if(nerd.intersects(hip)){
					g.drawString("COLLISION!!!", 200, 200);
					Random r = new Random();
					int direction = r.nextInt(4);
					switch(direction){
					
					case 1:
						xNerdSpeed = -xNerdSpeed;
						xHipSpeed = -xHipSpeed;
						System.out.println("Case 1");
					case 2:
						yNerdSpeed = -yNerdSpeed;
						yHipSpeed = -yHipSpeed;
						System.out.println("Case 2");
					case 3:
						xNerdSpeed = -xNerdSpeed;
						yHipSpeed = -yHipSpeed;
						System.out.println("Case 3");
					case 4:
						yNerdSpeed = -yNerdSpeed;
						xHipSpeed = -xHipSpeed;
						System.out.println("Case 4");
					}
				}
					
				///// PAUSE BUTTON  /////
				if(state == STATE.PLAY){
				g.setColor(Color.BLACK);
				g.fillRoundRect(972, 490, 40, 35, 5, 500);
				g.setColor(Color.WHITE);
				g.fillRoundRect(980, 495, 10, 25, 0, 0);
				g.fillRoundRect(995, 495, 10, 25, 0, 0);
				}
				if(state == STATE.PAUSE){
				g.setColor(Color.BLUE);
				g.fillRoundRect(972, 490, 40, 35, 5, 500);
				g.setColor(Color.WHITE);
				g.fillRoundRect(980, 495, 10, 25, 0, 0);
				g.fillRoundRect(995, 495, 10, 25, 0, 0);
			}
		}
	}
}
	public class TAdapter extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			ball.keyReleased(e);
		}
		public void keyPressed(KeyEvent e){
			ball.keyPressed(e);
		}
	}
	// ----- draw images from spritesheet ---///////
	public void drawFrame(Image source, Graphics g2, int x, int y, int cols, int frame, int width, int height){
			int fx = (frame % cols) * width;
			int fy = (frame / cols) * height;
			g2.drawImage(source, x, y, x+width, y+height, fx, fy, fx+width, fy+height, this);
			}
	}
