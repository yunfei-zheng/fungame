import java.awt.*;
import javax.swing.*;

public class Main {
  // the width and height of the frame
  public static final int FRAME_WIDTH = 600, FRAME_HEIGHT = 300;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Swing GUI");
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //GamePanel is the animation
    frame.getContentPane().add(new GamePanel());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
  }
}