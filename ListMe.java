import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListMe extends JPanel implements ListSelectionListener {
  
  private static final long serialVersionUID = 1L;
  FirstPositionListener firstListener;
  SecondPositionListener secondListener;
  ThirdPositionListener thirdListener;
  private JList<Serializable> list;
  private static DefaultListModel<Serializable> listModel;
  private static JTextField firstPos;
  private static JTextField secondPos;
  private static JTextField thirdPos;
  private static JTextField leadEQV;
  private static JTextField customSize;
  private static JTextField customDescription;
  private static JTextField customColor;

  private static final String firstLabel = "First Position";
  private static final String secondLabel = "Second Position";
  private static final String thirdLabel = "Third Position";
  
  /**
   * @return the listModel
   */
  public static DefaultListModel<Serializable> getListModel() {
    return listModel;
  }

  /**
   * @param listModel the listModel to set
   */
  public void setListModel(DefaultListModel<Serializable> listModel) {
    ListMe.listModel = listModel;
  }
  
  public ListMe() {
    super(new BorderLayout());

    setListModel(new DefaultListModel<Serializable>());
    getListModel().addElement(Helpers.babyPartNum);

    // Create the list and put it in a scroll pane.
    list = new JList<Serializable>(getListModel());
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.addListSelectionListener(this);
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);

    JButton firstButton = new JButton(firstLabel);
    firstListener = new FirstPositionListener(firstButton);

    JButton secondButton = new JButton(secondLabel);
    secondListener = new SecondPositionListener(secondButton);

    JButton thirdButton = new JButton(thirdLabel);
    thirdListener = new ThirdPositionListener(thirdButton);

    firstButton.setActionCommand(firstLabel);
    firstButton.addActionListener(firstListener);
    firstButton.setEnabled(false);

    Dimension buttonSize = firstButton.getSize();

    secondButton.setActionCommand(secondLabel);
    secondButton.addActionListener(secondListener);
    secondButton.setEnabled(false);
    secondButton.setSize(buttonSize.width, buttonSize.height);

    thirdButton.setActionCommand(thirdLabel);
    thirdButton.addActionListener(thirdListener);
    thirdButton.setEnabled(false);

    firstPos = new JTextField(10);
    firstPos.addActionListener(firstListener);
    firstPos.getDocument().addDocumentListener(firstListener);
    TextPrompt firstPosTP = new TextPrompt("Enter New 1st Position", firstPos);
    firstPos.setToolTipText("Enter New 1st Position");

    secondPos = new JTextField(10);
    TextPrompt secondPosTP = new TextPrompt("Enter New 2nd Position", secondPos);
    secondPos.addActionListener(secondListener);
    secondPos.getDocument().addDocumentListener(secondListener);

    thirdPos = new JTextField(10);
    TextPrompt thirdPosTP = new TextPrompt("Enter New 3rd Position", thirdPos);
    thirdPos.addActionListener(thirdListener);
    thirdPos.getDocument().addDocumentListener(thirdListener);

    leadEQV = new JTextField(10);
    TextPrompt leadTP = new TextPrompt("Enter Lead EQV", leadEQV);
    leadEQV.addActionListener(firstListener);
    leadEQV.getDocument().addDocumentListener(firstListener);
    leadEQV.setToolTipText("Enter Lead EQV");

    customSize = new JTextField(10);
    TextPrompt customSizeTP = new TextPrompt("Enter Size", customSize);
    customSize.addActionListener(secondListener);
    customSize.getDocument().addDocumentListener(secondListener);
    customSize.setToolTipText("Enter Size");

    customDescription = new JTextField(10);
    TextPrompt customDescriptionTP = new TextPrompt("Enter New Description", customDescription);
    customDescription.addActionListener(secondListener);
    customDescription.getDocument().addDocumentListener(secondListener);
    customDescription.setToolTipText("Enter New Description");

    customColor = new JTextField(10);
    TextPrompt customColorTP = new TextPrompt("Enter New Color", customColor);
    customColor.addActionListener(thirdListener);
    customColor.getDocument().addDocumentListener(thirdListener);
    customColor.setToolTipText("Enter New Color");

    // Create a panel that uses BoxLayout.
    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

    JPanel firstPane = new JPanel();
    firstPane.setLayout(new BoxLayout(firstPane, BoxLayout.LINE_AXIS));
    firstPane.add(firstButton);
    firstPane.add(Box.createHorizontalStrut(21));
    firstPane.add(new JSeparator(SwingConstants.VERTICAL));
    firstPane.add(Box.createHorizontalStrut(5));
    firstPane.add(firstPos);
    firstPosTP.changeAlpha(0.5f);
    firstPosTP.setShow(TextPrompt.Show.FOCUS_LOST);
    firstPane.add(Box.createHorizontalStrut(5));
    firstPane.add(new JSeparator(SwingConstants.VERTICAL));
    firstPane.add(Box.createHorizontalStrut(5));
    firstPane.add(leadEQV);
    leadTP.changeAlpha(0.5f);
    leadTP.setShow(TextPrompt.Show.FOCUS_LOST);
    firstPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

    JPanel secondPane = new JPanel();
    secondPane.setLayout(new BoxLayout(secondPane, BoxLayout.LINE_AXIS));
    secondPane.add(secondButton);
    secondPane.add(Box.createHorizontalStrut(3));
    secondPane.add(new JSeparator(SwingConstants.VERTICAL));
    secondPane.add(Box.createHorizontalStrut(5));
    secondPane.add(secondPos);
    secondPosTP.changeAlpha(0.5f);
    secondPosTP.setShow(TextPrompt.Show.FOCUS_LOST);
    secondPane.add(Box.createHorizontalStrut(5));
    secondPane.add(new JSeparator(SwingConstants.VERTICAL));
    secondPane.add(Box.createHorizontalStrut(5));
    secondPane.add(customDescription);
    customDescriptionTP.changeAlpha(0.5f);
    customDescriptionTP.setShow(TextPrompt.Show.FOCUS_LOST);
    secondPane.add(Box.createHorizontalStrut(5));
    secondPane.add(new JSeparator(SwingConstants.VERTICAL));
    secondPane.add(Box.createHorizontalStrut(5));
    secondPane.add(customSize);
    customSizeTP.changeAlpha(0.5f);
    customSizeTP.setShow(TextPrompt.Show.FOCUS_LOST);
    secondPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

    JPanel thirdPane = new JPanel();
    thirdPane.setLayout(new BoxLayout(thirdPane, BoxLayout.LINE_AXIS));

    thirdPane.add(thirdButton);
    thirdPane.add(Box.createHorizontalStrut(17));
    thirdPane.add(new JSeparator(SwingConstants.VERTICAL));
    thirdPane.add(Box.createHorizontalStrut(5));
    thirdPane.add(thirdPos);
    thirdPosTP.changeAlpha(0.5f);
    thirdPosTP.setShow(TextPrompt.Show.FOCUS_LOST);
    thirdPane.add(Box.createHorizontalStrut(5));
    thirdPane.add(new JSeparator(SwingConstants.VERTICAL));
    thirdPane.add(Box.createHorizontalStrut(5));
    thirdPane.add(customColor);
    customColorTP.changeAlpha(0.5f);
    customColorTP.setShow(TextPrompt.Show.FOCUS_LOST);
    thirdPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

    buttonPane.add(firstPane, BorderLayout.NORTH);
    buttonPane.add(secondPane, BorderLayout.CENTER);
    buttonPane.add(thirdPane, BorderLayout.SOUTH);

    add(listScrollPane, BorderLayout.CENTER);
    add(buttonPane, BorderLayout.PAGE_END);
  }

  @Override
  public void valueChanged(ListSelectionEvent arg0) {
    
  }

  public static JTextField getFirstPos() {
    return firstPos;
  }
  
  public static JTextField getSecondPos() {
    return secondPos;
  }
  
  public static JTextField getThirdPos() {
    return thirdPos;
  }

  public static JTextField getLeadEQV() {
    return leadEQV;
  }

  public static JTextField getCustomDescription() {
    return customDescription;
  }

  public static JTextField getCustomSize() {
    return customSize;
  }

  public static JTextField getCustomColor() {
    return customColor;
  }

}
