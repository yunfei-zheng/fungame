import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Obstacle extends JPanel {
  protected int x, y, width = 30;

  //these make you lose one point when you collide
  public Obstacle() {
    //these go from right to left on the screen
    x = Main.FRAME_WIDTH;
    y = (int) (Math.random() * Main.FRAME_HEIGHT);
  }

  //returns if its offscreen
  public boolean offscreen() {
    return x < -width;
  }

  //checks if it collides with the player by checking if the X and Y variables are close enough to each other
  public boolean collidePlayer(GamePanel g) {
    if (Math.abs(x - g.X()) <= 30 && Math.abs(y - g.Y()) <= 30) {
      Main.SCORE--;
      return true;
    }
    return false;
  }

  @Override // this is what gets drawn every update
	public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);  
    x--; //move it to the left
    g2d.fillRect(x, y, width, width);
	}
}