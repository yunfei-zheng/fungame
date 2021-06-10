import java.awt.*;
import javax.swing.*;

public class Main {
  // the width and height of the frame
  public static final int FRAME_WIDTH = 600, FRAME_HEIGHT = 300;
  // the total score
  public static int SCORE = 0;

  public static void main(String[] args) {
    // Sets up the game frame
    JFrame frame = new JFrame("Swing GUI");
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //GamePanel is where the animation happens
    frame.getContentPane().add(new GamePanel());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
  }
}