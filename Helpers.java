import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Helpers {

  private static String customDescriptionS;
  private static String customSizeS;
  private static String customColorS;

  static String[] firstPosition = { "A", "X", "EA", "EX", "EXV", "EAV", "L", "F", "ELV", "EFV", "EF", "EL", "CX", "CA",
      "CD", "CH", "C", "CF", "CL" };
  static String[] secondPosition = { "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "111", "200",
      "201", "202", "203", "204", "205", "206", "207", "208", "209", "400", "401", "402", "403", "404", "405", "406",
      "407", "408", "409", "430", "431", "432", "433", "438", "450", "451", "452", "453", "458", "480", "481", "482",
      "483", "484", "485", "486", "487", "488", "489", "600", "601", "602", "603", "604", "605", "606", "607", "608",
      "609", "680", "681", "682", "683", "684", "685", "686", "687", "688", "689", "700", "701", "702", "703", "704",
      "705", "706", "707", "708", "709", "900", "901", "902", "903", "904", "905", "950", "951", "952", "953", "954",
      "955" };
  static String[] thirdPosition = { "RB", "Y", "N", "B", "G", "R", "F", "S", "H", "K", "L", "LK", "M", "C", "P", "PK",
      "EK", "", "E", "T", "TK" };

  static ArrayList<String> finalDB = new ArrayList<String>();

  /* Holds the new PartNum after toPartNum */
  static ArrayList<String> babyPartNum = new ArrayList<String>();

  /* Holds the new Descriptions based off babyPartNum or customDescription */
  static ArrayList<String> babyDescription = new ArrayList<String>();

  /* Holds the new Sizes based off babyPartNum or customSize */
  static ArrayList<String> babySize = new ArrayList<String>();

  /* Holds the new Colors based off babyPartNum or customColor */
  static ArrayList<String> babyColor = new ArrayList<String>();

  /* Holds the new LeadEQV from createEQV */
  static ArrayList<String> babyEQV = new ArrayList<String>();

  /* Holds the new Manufacturer (for now defaulted to 'AADCO Medical' */
  static String manufacturer = new String("AADCO Medical");

  /*
   * Concats the three different position arrays into one partNum. Appends to
   * babyPartNum
   */
  public static void toPartNum(int indicator) {
    String s = "";
    String s12 = "";
    String s123 = "";

    if (indicator == 1) {
      s = FirstPositionListener.getArrLastIndex();
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
        String s2 = SecondPositionListener.getArrLastIndex();
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
          String s3 = ThirdPositionListener.getArrLastIndex();
          s123 = s12.concat(s3);
          babyPartNum.add(s123);
        }
      }
    }
    s = "";
    s12 = "";
    s123 = "";
  }

  public static void createDescription(String partNum) {
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

  public static void createSize(String partNum) {
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

  public static void createColor(String partNum) {
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

  public static void createEQV(String eqv) {
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

  public static void SQLize() {
    for (int i = 0; i < babyPartNum.size(); i++) {
      createDescription(babyPartNum.get(i));
      createSize(babyPartNum.get(i));
      createColor(babyPartNum.get(i));
      if (babyEQV.size() > 1) {
        createEQV(babyEQV.get(i));
      }
    }
  }

  public static void writeToFile() throws IOException {
    FileWriter f0 = new FileWriter("file1.txt");
    String headers = String.format("_id\tPartNum\tDescription\tSize\tColor\tLeadEqv\tManufacturer\n");
    f0.write(headers);
    for (int i = 0; i < babyPartNum.size(); i++) {
      if (babyEQV.size() > 1) {
        String row = String.format("\tLP-%s\t%s\t%s\t%s\t%s%s\n", babyPartNum.get(i), babyDescription.get(i),
            babySize.get(i), babyColor.get(i), babyEQV.get(i), manufacturer);
        finalDB.add(row);
      } else {
        String row = String.format("\tLP-%s\t%s\t%s\t%s\t%s%s\n", babyPartNum.get(i), babyDescription.get(i),
            babySize.get(i), babyColor.get(i), "0mm", manufacturer);
        finalDB.add(row);
      }
    }

    String DB = finalDB.toString();
    String DBclean = DB.replaceAll("[\\[\\],]", "");
    String DBtrim = DBclean.replaceAll("(?<=\\n)(\\s)", "");
    f0.write(DBtrim);

    f0.close();
  }
}