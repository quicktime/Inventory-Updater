import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class ListMe extends JPanel implements ListSelectionListener {

	private JList<Serializable> list;
	private DefaultListModel<Serializable> listModel;
	private JTextField firstPos;
	private JTextField secondPos;
	private JTextField thirdPos;
	private JTextField leadEQV;
	private JTextField customSize;
	private JTextField customDescription;
	private JTextField customColor;
	
	private static final String firstLabel = "First Position";
	private static final String secondLabel = "Second Position";
	private static final String thirdLabel = "Third Position";
	private String customDescriptionS;
	private String customSizeS;
	private String customColorS;

	ArrayList<String> firstArr = new ArrayList<String>();
	ArrayList<String> secondArr = new ArrayList<String>();
	ArrayList<String> thirdArr = new ArrayList<String>();

	static String[] firstPosition = { "A", "X", "EA", "EX", "EXV", "EAV", "L", "F", "ELV", "EFV", "EF", "EL", "CX",
			"CA", "CD", "CH", "C", "CF", "CL" };
	static String[] secondPosition = { "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "111",
			"200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "400", "401", "402", "403", "404",
			"405", "406", "407", "408", "409", "430", "431", "432", "433", "438", "450", "451", "452", "453", "458",
			"480", "481", "482", "483", "484", "485", "486", "487", "488", "489", "600", "601", "602", "603", "604",
			"605", "606", "607", "608", "609", "680", "681", "682", "683", "684", "685", "686", "687", "688", "689",
			"700", "701", "702", "703", "704", "705", "706", "707", "708", "709", "900", "901", "902", "903", "904",
			"905", "950", "951", "952", "953", "954", "955" };
	static String[] thirdPosition = { "RB", "Y", "N", "B", "G", "R", "F", "S", "H", "K", "L", "LK", "M", "C", "P", "PK",
			"EK", "", "E", "T", "TK" };

	ArrayList<String> finalDB = new ArrayList<String>();

	/* Holds the new PartNum after toPartNum */
	ArrayList<String> babyPartNum = new ArrayList<String>();

	/* Holds the new Descriptions based off babyPartNum or customDescription */
	ArrayList<String> babyDescription = new ArrayList<String>();

	/* Holds the new Sizes based off babyPartNum or customSize */
	ArrayList<String> babySize = new ArrayList<String>();

	/* Holds the new Colors based off babyPartNum or customColor */
	ArrayList<String> babyColor = new ArrayList<String>();

	/* Holds the new LeadEQV from createEQV */
	ArrayList<String> babyEQV = new ArrayList<String>();

	/* Holds the new Manufacturer (for now defaulted to 'AADCO Medical' */
	String manufacturer = new String("AADCO Medical");

	/* Concats the three different position arrays into one partNum. Appends to babyPartNum */
	public void toPartNum(int indicator) {
		String s = "";
		String s12 = "";
		String s123 = "";

		if (indicator == 1) {
			s = firstArr.get(firstArr.size() - 1).toString();
			for (int i2 = 0; i2 < secondPosition.length; i2++) {
				String s2 = secondPosition[i2];
				s12 = s.concat(s2);
				for (int i3 = 0; i3 < thirdPosition.length; i3++) {
					String s3 = thirdPosition[i3];
					s123 = s12.concat(s3);
					babyPartNum.add(s123);
				}
			}
		} else if (indicator == 2) {
			for (int i = 0; i < firstPosition.length; i++) {
				s = firstPosition[i];
				String s2 = secondArr.get(secondArr.size() - 1).toString();
				s12 = s.concat(s2);
				for (int i3 = 0; i3 < thirdPosition.length; i3++) {
					String s3 = thirdPosition[i3];
					s123 = s12.concat(s3);
					babyPartNum.add(s123);
				}
			}
		} else {
			for (int i = 0; i < firstPosition.length; i++) {
				s = firstPosition[i];
				for (int i2 = 0; i2 < secondPosition.length; i2++) {
					String s2 = secondPosition[i2];
					s12 = s.concat(s2);
					String s3 = thirdArr.get(thirdArr.size() - 1).toString();
					s123 = s12.concat(s3);
					babyPartNum.add(s123);
				}
			}
		}
		s = "";
		s12 = "";
		s123 = "";
	}

	public void createDescription(String partNum) {
		if (partNum.matches(".*1[0-9][0-9].*")) {
			babyDescription.add("Adjustable Apron");
		} else if (partNum.matches(".*2[0-9][0-9].*")) {
			babyDescription.add("Support Buckle Apron");
		} else if (partNum.matches(".*4[0-9][0-9].*")) {
			if (partNum.matches(".*4(0|8)(0|1|2|3|8).*")) {
				babyDescription.add("ErgoFit Vest");
			} else if (partNum.matches(".*4(0|8)(4|5|6|7|9).*")) {
				babyDescription.add("ErgoFit Kilt");
			} else
				babyDescription.add("MagnaBack");
		} else if (partNum.matches(".*6[0-9][0-9].*")) {
			if (partNum.matches(".*6[0-9](0|1|2|3|8).*")) {
				babyDescription.add("Vest");
			} else
				babyDescription.add("Kilt");
		} else if (partNum.matches(".*7[0-9][0-9].*")) {
			babyDescription.add("Wraparound Apron");
		} else if (partNum.matches(".*9[0-9][0-9].*")) {
			if (partNum.matches(".*90[0-9].*")) {
				babyDescription.add("O.R Quick-Drop X-Ray Apron");
			} else
				babyDescription.add("O.R Quick-Drop 950 X-Ray Apron");
		} else {
			babyDescription.add(customDescriptionS);
		}
	}

	public void createSize(String partNum) {
		if (partNum.matches(".*(1|2|7)[0-9][0-9].*")) {
			if (partNum.matches(".*(1|2|7)00.*")) {
				babySize.add("Male Small");
			} else if (partNum.matches(".*(1|2|7)01.*")) {
				babySize.add("Male Medium");
			} else if (partNum.matches(".*(1|2|7)02.*")) {
				babySize.add("Male Large");
			} else if (partNum.matches(".*(1|2|7)03.*")) {
				babySize.add("Male X-Large");
			} else if (partNum.matches(".*(1|2|7)04.*")) {
				babySize.add("Female Petite");
			} else if (partNum.matches(".*(1|2|7)05.*")) {
				babySize.add("Female Small");
			} else if (partNum.matches(".*(1|2|7)06.*")) {
				babySize.add("Female Medium");
			} else if (partNum.matches(".*(1|2|7)07.*")) {
				babySize.add("Female Large");
			} else if (partNum.matches(".*(1|2|7)08.*")) {
				babySize.add("Female X-Large");
			} else
				babySize.add("Male XX-Large");
		} else if (partNum.matches(".*9[0-9][0-9].*")) {
			if (partNum.matches(".*9[0-1]1.*")) {
				babySize.add("Small");
			} else if (partNum.matches(".*9[0-9]2.*")) {
				babySize.add("Medium");
			} else if (partNum.matches(".*9[0-9]3.*")) {
				babySize.add("Large");
			} else if (partNum.matches(".*9[0-9]4.*")) {
				babySize.add("X-Large");
			} else if (partNum.matches(".*9[0-9]5.*")) {
				babySize.add("XX-Large");
			} else
				babySize.add("Custom");
		} else if (partNum.matches(".*6[0-9][0-9].*")) {
			if (partNum.matches(".*60[0-9].*")) {
				if (partNum.matches(".*60(0|4).*")) {
					babySize.add("Female Petite");
				} else if (partNum.matches(".*60(1|5).*")) {
					babySize.add("Female Small");
				} else if (partNum.matches(".*60(2|6).*")) {
					babySize.add("Female Medium");
				} else if (partNum.matches(".*60(3|7).*")) {
					babySize.add("Female Large");
				} else
					babySize.add("Female X-Large");
			} else if (partNum.matches(".*68[0-9].*")) {
				if (partNum.matches(".*68(0|4).*")) {
					babySize.add("Male Small");
				} else if (partNum.matches(".*68(1|5).*")) {
					babySize.add("Male Medium");
				} else if (partNum.matches(".*68(2|6).*")) {
					babySize.add("Male Large");
				} else if (partNum.matches(".*68(3|7).*")) {
					babySize.add("Male X-Large");
				} else
					babySize.add("Male XX-Large");
			} else
				babySize.add("6XX SIZE NOT MATCH");
		} else if (partNum.matches(".*4[0-9][0-9].*")) {
			if (partNum.matches(".*40[0-9].*")) {
				if (partNum.matches(".*40(0|4).*")) {
					babySize.add("Male Small");
				} else if (partNum.matches(".*40(1|5).*")) {
					babySize.add("Male Medium");
				} else if (partNum.matches(".*40(2|6).*")) {
					babySize.add("Male Large");
				} else if (partNum.matches(".*40(3|7).*")) {
					babySize.add("Male X-Large");
				} else if (partNum.matches(".*40(4|8).*")) {
					babySize.add("Male XX-Large");
				} else
					babySize.add("40X SIZE NOT MATCH");
			} else if (partNum.matches(".*48[0-9].*")) {
				if (partNum.matches(".*48(0|4).*")) {
					babySize.add("Female Petite");
				} else if (partNum.matches(".*48(1|5).*")) {
					babySize.add("Female Small");
				} else if (partNum.matches(".*48(2|6).*")) {
					babySize.add("Female Medium");
				} else if (partNum.matches(".*48(3|7).*")) {
					babySize.add("Female Large");
				} else if (partNum.matches(".*48(8|9).*")) {
					babySize.add("Female X-Large");
				} else
					babySize.add("48X SIZE NOT MATCH");
			} else if (partNum.matches(".*4(3|5)[0-9].*")) {
				if (partNum.matches(".*43[0-9].*")) {
					if (partNum.matches(".*430.*")) {
						babySize.add("Male Small");
					} else if (partNum.matches(".*431.*")) {
						babySize.add("Male Medium");
					} else if (partNum.matches(".*432.*")) {
						babySize.add("Male Large");
					} else if (partNum.matches(".*433.*")) {
						babySize.add("Male X-Large");
					} else if (partNum.matches(".*438.*")) {
						babySize.add("Male XX-Large");
					} else
						babySize.add("43X SIZE NOT MATCH");
				} else if (partNum.matches(".*45[0-9].*")) {
					if (partNum.matches(".*450.*")) {
						babySize.add("Female Petite");
					} else if (partNum.matches(".*451.*")) {
						babySize.add("Female Small");
					} else if (partNum.matches(".*452.*")) {
						babySize.add("Female Medium");
					} else if (partNum.matches(".*453.*")) {
						babySize.add("Female Large");
					} else if (partNum.matches(".*458.*")) {
						babySize.add("Female X-Large");
					} else
						babySize.add("45X SIZE NOT MATCH");
				}
			} else
				babySize.add("XXX SIZE NOT MATCH");
		} else {
			babySize.add(customSizeS);
		}
	}

	public void createColor(String partNum) {
		if (partNum.matches(".*[0-9]B$")) {
			babyColor.add("Burgundy");
		} else if (partNum.matches(".*C$")) {
			babyColor.add("Custom");
		} else if (partNum.matches(".*D$")) {
			babyColor.add("Desert Storm");
		} else if (partNum.matches(".*E$")) {
			babyColor.add("Blue");
		} else if (partNum.matches(".*EK$")) {
			babyColor.add("Emerald Krinkle");
		} else if (partNum.matches(".*F$")) {
			babyColor.add("Fuschia");
		} else if (partNum.matches(".*G$")) {
			babyColor.add("Green");
		} else if (partNum.matches(".*H$")) {
			babyColor.add("Hot Pink");
		} else if (partNum.matches(".*K$")) {
			babyColor.add("Black");
		} else if (partNum.matches(".*L$")) {
			babyColor.add("Light Blue");
		} else if (partNum.matches(".*LK$")) {
			babyColor.add("Lavender Krinkle");
		} else if (partNum.matches(".*M$")) {
			babyColor.add("Tie Dye");
		} else if (partNum.matches(".*N$")) {
			babyColor.add("Navy");
		} else if (partNum.matches(".*P$")) {
			babyColor.add("Purple");
		} else if (partNum.matches(".*PK$")) {
			babyColor.add("Pink Krinkle");
		} else if (partNum.matches(".*R$")) {
			babyColor.add("Red");
		} else if (partNum.matches(".*RB$")) {
			babyColor.add("Royal Blue");
		} else if (partNum.matches(".*S$")) {
			babyColor.add("Silver");
		} else if (partNum.matches(".*T$")) {
			babyColor.add("Teal");
		} else if (partNum.matches(".*TK$")) {
			babyColor.add("Tangerine Krinkle");
		} else if (partNum.matches(".*X$")) {
			babyColor.add("Pattern");
		} else if (partNum.matches(".*Y$")) {
			babyColor.add("Yellow");
		} else if (partNum.matches(".*[0-9]$")) {
			babyColor.add("Blue");
		} else {
			babyColor.add(customColorS);
		}
	}

	public void createEQV(String eqv) {
		babyEQV.add(eqv);
	}

	public void createCustomSize(String sizer) {
		babySize.add(sizer);
	}

	public void createCustomDescription(String descriptoner) {
		babyDescription.add(descriptoner);
	}
	
	public void createCustomColor(String colorer) {
		babyColor.add(colorer);
	}
	
	public void SQLize() {
		for (int i = 0; i < babyPartNum.size(); i++) {
			createDescription(babyPartNum.get(i));
			createSize(babyPartNum.get(i));
			createColor(babyPartNum.get(i));
			if (babyEQV.size() > 1) {
				createEQV(babyEQV.get(i));
			}
		}
	}

	public void FileWriterDB() throws IOException {
		FileWriter f0 = new FileWriter("file1.txt");
		f0.write("_id" + "\t" + "PartNum" + "\t" + "Description" + "\t" + "Size" + "\t" + "Color" + "\t" + "LeadEqv"
				+ "\t" + "Manufacturer\n");
		for (int i = 0; i < babyPartNum.size(); i++) {
			if (babyEQV.size() > 1) {
				finalDB.add("\t" + "LP-" + babyPartNum.get(i) + "\t" + babyDescription.get(i) + "\t" + babySize.get(i)
					+ "\t" + babyColor.get(i) + "\t" + babyEQV.get(i) + "\t" + manufacturer + "\n"); 
			} else {
				finalDB.add("\t" + "LP-" + babyPartNum.get(i) + "\t" + babyDescription.get(i) + "\t" + babySize.get(i)
					+ "\t" + babyColor.get(i) + "\t" + "0mm" + "\t" + manufacturer + "\n"); 
			}
		}

		String DB = finalDB.toString();
		String DBclean = DB.replaceAll("[\\[\\],]", "");
		String DBtrim = DBclean.replaceAll("(?<=\\n)(\\s)", "");
		f0.write(DBtrim);

		// String partNumDB = babyPartNum.toString();
		// char buffer[] = new char[partNumDB.length()];
		// partNumDB.getChars(1, partNumDB.length() - 1, buffer, 0);
		// for (int i = 0; i < buffer.length; i++) {
		// f0.write(buffer[i]);
		// }

		f0.close();
	}

	public ListMe() {
		super(new BorderLayout());

		firstArr.addAll(Arrays.asList(firstPosition));
		secondArr.addAll(Arrays.asList(secondPosition));
		thirdArr.addAll(Arrays.asList(thirdPosition));

		listModel = new DefaultListModel<Serializable>();
		listModel.addElement(babyPartNum);

		// Create the list and put it in a scroll pane.
		list = new JList<Serializable>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(list);

		JButton firstButton = new JButton(firstLabel);
		firstListener firstListener = new firstListener(firstButton);

		JButton secondButton = new JButton(secondLabel);
		secondListener secondListener = new secondListener(secondButton);

		JButton thirdButton = new JButton(thirdLabel);
		thirdListener thirdListener = new thirdListener(thirdButton);

		firstButton.setActionCommand(firstLabel);
		firstButton.addActionListener(firstListener);
		firstButton.setEnabled(false);

		secondButton.setActionCommand(secondLabel);
		secondButton.addActionListener(secondListener);
		secondButton.setEnabled(false);

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

		// customSizeDigits = new JTextField(10);
		// TextPrompt customSizeDigitsTP = new TextPrompt("Enter 3 digits",
		// customSizeDigits);
		// customSizeDigits.addActionListener(secondListener);
		// customSizeDigits.getDocument().addDocumentListener(secondListener);
		// customSizeDigits.setToolTipText("Enter 3 digits");

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
		secondPane.add(Box.createHorizontalStrut(4));
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
		thirdPane.add(Box.createHorizontalStrut(16));
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

	// This listener is shared by the text field and the hire button.
	class firstListener implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public firstListener(JButton button) {
			this.button = button;
		}

		// Required by ActionListener.
		public void actionPerformed(ActionEvent e) {
			String firstInput = firstPos.getText();
			String eqvInput = leadEQV.getText();

			// User didn't type in a unique name...
			if (firstInput.equals("") || alreadyInList(firstInput)) {
				Toolkit.getDefaultToolkit().beep();
				firstPos.requestFocusInWindow();
				firstPos.selectAll();
				return;
			}

			firstArr.add(firstInput.toUpperCase());
			createEQV(eqvInput);
			toPartNum(1);
			SQLize();
			try {
				FileWriterDB();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// If we just wanted to add to the end, we'd do this:
			listModel.addElement(firstPos.getText());

			// Reset the text field.
			firstPos.requestFocusInWindow();
			firstPos.setText("");

		}

		// This method tests for string equality. You could certainly
		// get more sophisticated about the algorithm. For example,
		// you might want to ignore white space and capitalization.
		protected boolean alreadyInList(String name) {
			name = name.toUpperCase();
			return listModel.contains(name) || firstArr.contains(name);
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
	}

	// This listener is shared by the text field and the hire button.
	class secondListener implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public secondListener(JButton button) {
			this.button = button;
		}

		// Required by ActionListener.
		public void actionPerformed(ActionEvent e) {
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
			listModel.addElement(secondPos.getText());

			secondArr.add(secondInput);
			customSizeS = customSizeInput;
			customDescriptionS = customDescriptionInput;
			toPartNum(2);
			SQLize();
			try {
				FileWriterDB();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
			return listModel.contains(name) || secondArr.contains(name);
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
	}

	// This listener is shared by the text field and the hire button.
	class thirdListener implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public thirdListener(JButton button) {
			this.button = button;
		}

		// Required by ActionListener.
		public void actionPerformed(ActionEvent e) {
			String thirdInput = thirdPos.getText();
			String customColorInput = customColor.getText();

			// User didn't type in a unique name...
			if (thirdInput.equals("") || alreadyInList(thirdInput)) {
				Toolkit.getDefaultToolkit().beep();
				thirdPos.requestFocusInWindow();
				thirdPos.selectAll();
				return;
			}

			// listModel.insertElementAt(thirdPos.getText(), index);
			// If we just wanted to add to the end, we'd do this:
			listModel.addElement(thirdPos.getText());

			thirdArr.add(thirdInput.toUpperCase());
			customColorS = customColorInput;
			toPartNum(3);
			SQLize();
			try {
				FileWriterDB();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
			return listModel.contains(name) || thirdArr.contains(name);
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

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
