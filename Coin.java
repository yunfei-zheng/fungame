import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Coin extends Obstacle {
  //this class is like an Obstacle, but these give you one point when you collide
  public Coin() {
    //these will spawn on the left side of the screen and move to the right
    x = -width;
    y = (int) (Math.random() * Main.FRAME_HEIGHT);
  }

  @Override // checks if its offscreen
  public boolean offscreen() {
    return x > Main.FRAME_WIDTH;
  }

  @Override //checks if it collides with player
  public boolean collidePlayer(GamePanel g) {
     if (Math.abs(x - g.X()) <= 30 && Math.abs(y - g.Y()) <= 30) {
      Main.SCORE++;
      return true;
    }
    return false;
  }

  @Override // this is what gets drawn every update
	public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.YELLOW);  
    x++; //move it to the right
    g2d.fillRect(x, y, width, width);
	}
}