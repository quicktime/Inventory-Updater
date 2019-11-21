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
  class SecondPositionListener implements ActionListener, DocumentListener {
    
    private boolean alreadyEnabled = false;
    private JButton button;
    private static ArrayList<String> array = new ArrayList<String>();
    
    public SecondPositionListener(JButton button) {
      this.button = button;
    }

    // Required by ActionListener.
    public void actionPerformed(ActionEvent e) {
      
      JTextField secondPos = ListMe.getSecondPos();
      JTextField customDescription = ListMe.getCustomDescription();
      JTextField customSize = ListMe.getCustomSize();
      
      String secondInput = secondPos.getText();
      String customDescriptionInput = customDescription.getText();
      String customSizeInput = customSize.getText();

      // User didn't type in a unique name...
      if (secondInput.equals("") || alreadyInList(secondInput)) {
        Toolkit.getDefaultToolkit().beep();
        secondPos.requestFocusInWindow();
        secondPos.selectAll();
        return;
      }

      // listModel.insertElementAt(secondPos.getText(), index);
      // If we just wanted to add to the end, we'd do this:
      ListMe.getListModel().addElement(secondPos.getText());

      array.add(secondInput);
      Helpers.toPartNum(2);
      Helpers.SQLize();
      try {
        Helpers.writeToFile();
      } catch (IOException e1) {
        e1.printStackTrace();
      }

      // Reset the text field.
      secondPos.requestFocusInWindow();
      secondPos.setText("");
      customDescription.requestFocusInWindow();
      customDescription.setText("");
      customSize.requestFocusInWindow();
      customSize.setText("");

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
      SecondPositionListener.array = firstArr;
    }
    
  }