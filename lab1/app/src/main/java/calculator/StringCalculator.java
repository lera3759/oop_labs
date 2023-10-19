package calculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

  private static final int MAX_NUMBER = 1000;

  public int Add(String numbers) {
    String delimeter = ",|\\n";
    String[] numbersArray;

    if (numbers.isEmpty()) {
      return 0;
    }

    if (numbers.startsWith("//")) {
      numbers = replaceCustomedDelimeter(numbers);
    }

    endsWithDelimiter(numbers);

    numbersArray = numbers.split(delimeter);

    return getSum(numbersArray);
  }

  private String replaceCustomedDelimeter(String numbers) {

    String customedDelimeter = numbers.substring(numbers.indexOf("//") + 2, numbers.indexOf("\n"));
    numbers = numbers.substring(numbers.indexOf("\n") + 1);

    if (customedDelimeter.length() != 1) {
      if (multipleDelimeters(customedDelimeter)) {
        numbers = replaceDelimetersToComa(numbers, customedDelimeter);
      } else {
        numbers = numbers.replace(customedDelimeter.substring(1, customedDelimeter.length() - 1), ",");
      }
    } else {
      numbers = numbers.replace(customedDelimeter, ",");
    }

    return numbers;
  }

  private String replaceDelimetersToComa(String numbers, String delimeter) {
    Pattern pattern = Pattern.compile("\\[(.*?)\\]");
    Matcher matcher = pattern.matcher(delimeter);

    ArrayList<String> delimeters = new ArrayList<>();

    while (matcher.find()) {
      for (int i = 0; i <= matcher.groupCount(); i++) {
        String m = matcher.group(i);

        if (!m.startsWith("[") && !m.endsWith("]")) {
          delimeters.add(m);
        }
      }
    }

    delimeters.sort((s2, s1) -> s1.length() - s2.length());

    for (String del : delimeters) {
      numbers = numbers.replace(del, ",");
    }

    return numbers;
  }

  private boolean multipleDelimeters(String delimeter) {
    Pattern pattern = Pattern.compile("\\[(.*?)\\]");
    Matcher matcher = pattern.matcher(delimeter);

    return matcher.find();
  }

  private int stringToInt(String str) {
    return (Integer.parseInt(str));
  }

  private void endsWithDelimiter(String numbers) throws IllegalArgumentException {
    if (numbers.endsWith(",") || numbers.endsWith("\n")) {
      throw new IllegalArgumentException("invalid input");
    }
  }

  private int getSum(String[] numbersArray) throws NumberFormatException {
    int sum = 0, number;
    boolean negativeIsFound = false;
    ArrayList<Integer> negativeNumbers = new ArrayList<>();

    for (int i = 0; i < numbersArray.length; i++) {
      try {
        number = stringToInt(numbersArray[i]);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("integer not found");
      }

      if (number < 0) {
        negativeIsFound = true;
        negativeNumbers.add(number);
      } else if (!negativeIsFound && number <= MAX_NUMBER) {
        sum += number;
      }
    }

    if (negativeIsFound) {
      throw new IllegalArgumentException("negatives not allowed: " + negativeNumbers.toString());
    }

    return sum;
  }
}