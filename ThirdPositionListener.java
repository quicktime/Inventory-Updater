import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// This listener is shared by the text field and the hire button.
  class ThirdPositionListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private static ArrayList<String> array = new ArrayList<String>();

    public ThirdPositionListener(JButton button) {
      this.button = button;
    }

    // Required by ActionListener.
    public void actionPerformed(ActionEvent e) {
      JTextField thirdPos = ListMe.getThirdPos();
      JTextField customColor = ListMe.getCustomColor();
      
      String thirdInput = ListMe.getThirdPos().getText();
      String customColorInput = ListMe.getCustomColor().getText();

      // User didn't type in a unique name...
      if (thirdInput.equals("") || alreadyInList(thirdInput)) {
        Toolkit.getDefaultToolkit().beep();
        thirdPos.requestFocusInWindow();
        thirdPos.selectAll();
        return;
      }

      // If we just wanted to add to the end, we'd do this:
      ListMe.getListModel().addElement(thirdPos.getText());

      array.add(thirdInput.toUpperCase());
      Helpers.toPartNum(3);
      Helpers.SQLize();
      try {
        Helpers.writeToFile();
      } catch (IOException e1) {
        e1.printStackTrace();
      }

      // Reset the text field.
      thirdPos.requestFocusInWindow();
      thirdPos.setText("");
      customColor.requestFocusInWindow();
      customColor.setText("");

    }

    // This method tests for string equality. You could certainly
    // get more sophisticated about the algorithm. For example,
    // you might want to ignore white space and capitalization.
    protected boolean alreadyInList(String name) {
      name = name.toUpperCase();
      return ListMe.getListModel().contains(name) || array.contains(name);
    }

    // Required by DocumentListener.
    public void insertUpdate(DocumentEvent e) {
      enableButton();
    }

    // Required by DocumentListener.
    public void removeUpdate(DocumentEvent e) {
      handleEmptyTextField(e);
    }

    // Required by DocumentListener.
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
    
    public static String getArrLastIndex() {
      return array.get(array.size() - 1).toString();
    }

    /**
     * @param array the array to set
     */
    public void setArr(ArrayList<String> firstArr) {
      ThirdPositionListener.array = firstArr;
    }
  }
