package calculator;

public class StringCalculator {

  public int Add(String numbers) {

    if (numbers.isEmpty()) {
      return 0;
    }

    endsWithDelimiter(numbers);

    String[] numbersArray = numbers.split(",");

    if (numbersArray.length < 3) {
      return getSum(numbersArray);
    } else {
      throw new IllegalArgumentException("invalid input");
    }
  }

  private int stringToInt(String str) {
    return (Integer.parseInt(str));
  }

  private void endsWithDelimiter(String numbers) throws IllegalArgumentException {
    if (numbers.endsWith(",")) {
      throw new IllegalArgumentException("invalid input");
    }
  }

  private int getSum(String[] numbersArray) throws NumberFormatException {
    try {
      if (numbersArray.length > 1) {
        return stringToInt(numbersArray[0]) + stringToInt(numbersArray[1]);
      }

      return stringToInt(numbersArray[0]);

    } catch (NumberFormatException e) {
      throw new NumberFormatException("integer not found");
    }
  }
}