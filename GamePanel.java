import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
  private int width = 30, x = (Main.FRAME_WIDTH - width) / 2, y = Main.FRAME_HEIGHT - 2 * width;
  private double velocity = 0.0, acceleration = 0.0; //add this next

  @Override //this is what gets drawn every update
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
    g2d.clearRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		g2d.setColor(Color.RED);	
		g2d.fillRect(x, y, width, width);
	}

  public GamePanel() {
    setFocusable(true);
    GameListener listener = new GameListener();
    addKeyListener(listener);
    Timer timer = new Timer(100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        acceleration -= 1.0;
        velocity += acceleration;
        if (y - velocity <= Main.FRAME_HEIGHT - 2 * width) {
          y -= velocity;
        } else {
          y = Main.FRAME_HEIGHT - 2 * width;
          acceleration = 0.0;
        }
        repaint();
      }
    });
    timer.start();
  }

  private class GameListener implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP){
        acceleration += 3.0;
        velocity += acceleration;
        if (y + velocity >= 0) {
          y += velocity;
        } else {
          y = 0;
        }
        repaint();
      }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
  }
}