package calculator;

import java.util.ArrayList;

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
    String customedDelimeter = numbers.substring(2, numbers.indexOf("\n"));
    numbers = numbers.substring(numbers.indexOf("\n") + 1);

    if (customedDelimeter.contains("[") & customedDelimeter.contains("]")) {
      customedDelimeter = customedDelimeter.substring(1, customedDelimeter.length() - 1);
    }

    numbers = numbers.replace(customedDelimeter, ",");

    return numbers;
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