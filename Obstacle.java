import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Obstacle extends JPanel {
  private int x, y, width = 30;

  public Obstacle() {
    x = (int) (Math.random() * Main.FRAME_WIDTH);
    y = (int) (Math.random() * Main.FRAME_HEIGHT);
  }

  @Override // this is what gets drawn every update
	public void paintComponent(Graphics g) {
    Random rand = new Random();
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);  
    x = rand.nextInt(Main.FRAME_WIDTH);
    y = rand.nextInt(Main.FRAME_HEIGHT);
    g2d.fillRect(x, y, width, width);
	}
}