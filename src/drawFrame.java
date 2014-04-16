import java.awt.Graphics;
import java.awt.Image;

public class drawFrame {

	public drawFrame(Image source, Graphics g2, int x, int y, int cols, int frame, int width, int height){
		int fx = (frame % cols) * width;
		int fy = (frame / cols) * height;
		g2.drawImage(source, x, y, x+width, y+height, fx, fy, fx+width, fy+height, null);
	}
}