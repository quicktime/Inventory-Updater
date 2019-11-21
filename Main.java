import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
    // Schedule a job for the event-dispatching thread:
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      // creating and showing this application's GUI.
      public void run() {
        createAndShowGUI();
      }
    });
  }
  
  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("Inventory Updater");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create and set up the content pane.
    JComponent newContentPane = new ListMe();
    newContentPane.setOpaque(true); // content panes must be opaque
    frame.setContentPane(newContentPane);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

}
