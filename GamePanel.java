import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel {
  // set some basic variables
  private int width = 30, x = (Main.FRAME_WIDTH - width) / 2, y = Main.FRAME_HEIGHT - 2 * width;
  //positive = up right
  //this is the speed at which the player is moving
  private double verticalVelocity = 0.0, horizontalVelocity = 0.0;
  //it represents the obstacles and coins
  private ArrayList<Obstacle> obstacles;
  private ArrayList<Coin> coins;
  //the timer is what is used to animate
  private Timer timer;

  @Override // this is what gets drawn every update
	public void paintComponent(Graphics g) {
    super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
    //clears the frame for each 
    g2d.clearRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		g2d.setColor(Color.RED);
    // at top: y = 0, bottom: y = frameheight - 2 * width
    // this draws the player
    g2d.fillRect(x, y, width, width);

    //this code checks for collisions with obstacles
    ArrayList<Obstacle> toRemove = new ArrayList<Obstacle>();
    for (Obstacle o: obstacles) {
      if (o.offscreen() || o.collidePlayer(this)) {
        toRemove.add(o);
      }
    }
    //this code removes any that were colliding with the player
    obstacles.removeAll(toRemove);
    //if there are less than 8 obstacles then add some new obstacle with a 0.5% chance
    if (obstacles.size() < 8) {
      Random r = new Random();
      if (r.nextInt(1000) < 5) {
        obstacles.add(new Obstacle());
      }
    }
    //paint all of the obstacles
    for (Obstacle o : obstacles) {
      o.paintComponent(g);
    }

    //this code checks for collisions with obstacles
    ArrayList<Coin> toRemov = new ArrayList<Coin>();
    for (Coin c: coins) {
      if (c.offscreen() || c.collidePlayer(this)) {
        toRemov.add(c);
      }
    }
    //removes all the colliding coins
    coins.removeAll(toRemov);
    //if there are less than 2 coins then add some new coin with a 0.5% chance
    if (coins.size() < 2) {
      Random r = new Random();
      if (r.nextInt(1000) < 5) {
        coins.add(new Coin());
      }
    }
    //draw the coins
    for (Coin c : coins) {
      c.paintComponent(g);
    }
    
    //draw the score, you want the score to go as high as you can
    g2d.setColor(Color.BLACK);
    g2d.drawString(Integer.toString(Main.SCORE), 200, 200);

    //the game ends when you get a score of 10!
    if (Main.SCORE == 10) {
      timer.stop();
      g2d.drawString("YOU WIN!!!!!!!!!!!!!", 300, 200);
    }
	}

  public GamePanel() {
    setFocusable(true);
    GameListener listener = new GameListener();
    addKeyListener(listener);
    obstacles = new ArrayList<Obstacle>();
    coins = new ArrayList<Coin>();
    // this happens every 10 milliseconds
    timer = new Timer(10, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //up and down
        //this simulates the gravity the velocity decreases by 0.06 each loop
        verticalVelocity -= 0.06;
        if (y - verticalVelocity >= 0) {
          y -= verticalVelocity;
        } else {
          //this code makes sure it doesn't fall through the floor
          y = 0;
          verticalVelocity = 0.0;
        }
        //this code makes sure it doesn't jump through the ceiling
        if (y > Main.FRAME_HEIGHT - 2 * width) {
          y = Main.FRAME_HEIGHT - 2 * width;
          verticalVelocity = 0.0;
        }

        //left and right
        x += horizontalVelocity;
        // this moves the horizontal velocity closer to 0 each loop to slow it down
        if (horizontalVelocity + 0.06 < 0) {
          horizontalVelocity += 0.06;
        } else if (horizontalVelocity - 0.06 > 0) {
          horizontalVelocity -= 0.06;
        } else {
          horizontalVelocity = 0;
        }
        //this makes sure it doesn't move through the left side of the screen
        if (x < 0) {
          x = 0;
        }
        //this makes sure it doesn't move through the right side of the screen
        if (x > Main.FRAME_WIDTH - width) {
          x = Main.FRAME_WIDTH - width;
        }

        //runs the paint() method this is what animates it every cycle
        repaint();
      }
    });
    //starts the timer
    timer.start();
  }

  //returns X position
  public int X() {
    return x;
  }

  //returns Y position
  public int Y() {
    return y;
  }

  private class GameListener implements KeyListener {
    @Override //this happens when a key is pressed
    public void keyPressed(KeyEvent e) {
      //when the up key is pressed it sets the up speed to 3
      if (e.getKeyCode() == KeyEvent.VK_UP){
        verticalVelocity = 3.0;
        //this makes sure that it can't go up above the ceiling
        if (y + verticalVelocity <= Main.FRAME_HEIGHT - 2 * width) {
          y += verticalVelocity;
        } else {
          y = Main.FRAME_HEIGHT - 2 * width;
        }
      }

      //sets the left speed to 3
      if (e.getKeyCode() == KeyEvent.VK_LEFT){
        horizontalVelocity = -3.0;
        x += horizontalVelocity;
      }

      //sets the right speed to 3
      if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        horizontalVelocity = 3.0;
        x += horizontalVelocity;
      }
    }

    //this code is just there for the interface
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
  }
}