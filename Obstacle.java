import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Obstacle extends JPanel {
  private int x, y, width = 30;

  public Obstacle() {
    x = (int) (Math.random() * Main.FRAME_WIDTH);
    y = (int) (Math.random() * Main.FRAME_HEIGHT);
  }

  public boolean offscreen() {
    System.out.println(x + " " + (Main.FRAME_WIDTH - width));
    return x < -width;
  }

  @Override // this is what gets drawn every update
	public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);  
    x--;
    g2d.fillRect(x, y, width, width);
	}
}