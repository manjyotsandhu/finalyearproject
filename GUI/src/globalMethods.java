import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * A class to contain any global methods needed
 * 
 * @author manjyot
 *
 */
public class globalMethods {

  // Method will centre any frame on the screen
  public static void centreWindow(Window frame) {
    Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize(); // Screen
                                                                              // size
    // Calculation of centre position
    int x = (int) ((screenDimensions.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((screenDimensions.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
  }
}
