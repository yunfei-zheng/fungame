import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel {
  // set some basic variables
  private int width = 30, x = (Main.FRAME_WIDTH - width) / 2, y = Main.FRAME_HEIGHT - 2 * width;
  //positive = up right
  private double verticalVelocity = 0.0, horizontalVelocity = 0.0;
  private ArrayList<Obstacle> obstacles;

  @Override // this is what gets drawn every update
	public void paintComponent(Graphics g) {
    super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
    g2d.clearRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		g2d.setColor(Color.RED);
    // at top: y = 0, bottom: y = frameheight - 2 * width
    g2d.fillRect(x, y, width, width);

    ArrayList<Obstacle> toRemove = new ArrayList<Obstacle>();
    for (Obstacle o: obstacles) {
      if (o.offscreen()) {
        System.out.println("detec offscre");
        toRemove.add(o);
      }
    }
    obstacles.removeAll(toRemove);
    /*
    if (obstacles.size() < 1) {
      Random r = new Random();
      if (r.nextInt(100) < 5) {
        System.out.println("yes");
        obstacles.add(new Obstacle());
      }
    }*/
    for (Obstacle o : obstacles) {
      System.out.println("Hlp");
      o.paintComponent(g);
    }
	}

  public GamePanel() {
    setFocusable(true);
    GameListener listener = new GameListener();
    addKeyListener(listener);
    obstacles = new ArrayList<Obstacle>();
    for (int i = 0; i < 1; i++) {
      obstacles.add(new Obstacle());
    }
    // this happens every 15 milliseconds
    Timer timer = new Timer(15, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //up and down
        verticalVelocity -= 0.06;
        if (y - verticalVelocity >= 0) {
          y -= verticalVelocity;
        } else {
          y = 0;
          verticalVelocity = 0.0;
        }
        if (y > Main.FRAME_HEIGHT - 2 * width) {
          y = Main.FRAME_HEIGHT - 2 * width;
          verticalVelocity = 0.0;
        }

        //left and right
        x += horizontalVelocity;
        if (horizontalVelocity + 0.06 < 0) {
          horizontalVelocity += 0.06;
        } else if (horizontalVelocity - 0.06 > 0) {
          horizontalVelocity -= 0.06;
        } else {
          horizontalVelocity = 0;
        }
        if (x < 0) {
          x = 0;
        }
        if (x > Main.FRAME_WIDTH - width) {
          x = Main.FRAME_WIDTH - width;
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
        verticalVelocity = 3.0;
        if (y + verticalVelocity <= Main.FRAME_HEIGHT - 2 * width) {
          y += verticalVelocity;
        } else {
          y = Main.FRAME_HEIGHT - 2 * width;
        }
      }

      if (e.getKeyCode() == KeyEvent.VK_LEFT){
        horizontalVelocity = -3.0;
        x += horizontalVelocity;
      }

      if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        horizontalVelocity = 3.0;
        x += horizontalVelocity;
      }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
  }
}