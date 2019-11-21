import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This listener is shared by the text field and the hire button.
 */
public class FirstPositionListener implements ActionListener, DocumentListener {

  private boolean alreadyEnabled = false;
  private JButton button;

  private static ArrayList<String> array = new ArrayList<String>();

  public FirstPositionListener(JButton button) {
    this.button = button;
  }

  /**
   * Required by ActionListener.(non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
    JTextField firstPos = ListMe.getFirstPos();
    JTextField leadEQV = ListMe.getLeadEQV();

    String firstInput = firstPos.getText();
    String eqvInput = leadEQV.getText();

    /**
     * User didn't type in a unique name...
     */
    if (firstInput.equals("") || alreadyInList(firstInput)) {
      Toolkit.getDefaultToolkit().beep();
      firstPos.requestFocusInWindow();
      firstPos.selectAll();
      return;
    }

    getArr().add(firstInput.toUpperCase());
    Helpers.createEQV(eqvInput);
    Helpers.toPartNum(1);
    Helpers.SQLize();
    try {
      Helpers.writeToFile();
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    /**
     * If we just wanted to add to the end, we'd do this:
     */
    ListMe.getListModel().addElement(firstPos.getText());

    // Reset the text field.
    firstPos.requestFocusInWindow();
    firstPos.setText("");

  }

  /**
   * This method tests for string equality. You could certainly get more
   * sophisticated about the algorithm. For example,
   *
   * you might want to ignore white space and capitalization.
   */
  protected boolean alreadyInList(String name) {
    name = name.toUpperCase();
    return ListMe.getListModel().contains(name) || getArr().contains(name);
  }

  /**
   * Required by DocumentListener.
   */
  public void insertUpdate(DocumentEvent e) {
    enableButton();
  }

  /**
   *  Required by DocumentListener.
   */
  public void removeUpdate(DocumentEvent e) {
    handleEmptyTextField(e);
  }

  /**
   * Required by DocumentListener.
   */
  public void changedUpdate(DocumentEvent e) {
    if (!handleEmptyTextField(e)) {
      enableButton();
    }
  }

  private void enableButton() {
    if (!alreadyEnabled) {
      button.setEnabled(true);
    }
  }

  private boolean handleEmptyTextField(DocumentEvent e) {
    if (e.getDocument().getLength() <= 0) {
      button.setEnabled(false);
      alreadyEnabled = false;
      return true;
    }
    return false;
  }

  /**
   * @return the array
   */
  public ArrayList<String> getArr() {
    return array;
  }

  /**
   * @return the last index of the array, return as string
   */
  public static String getArrLastIndex() {
    return array.get(array.size() - 1).toString();
  }

  /**
   * @param array
   *          the array to set
   */
  public void setArr(ArrayList<String> array) {
    FirstPositionListener.array = array;
  }
}