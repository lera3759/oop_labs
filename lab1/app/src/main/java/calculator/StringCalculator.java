package calculator;

public class StringCalculator {

  public int Add(String numbers) {

    if (numbers.isEmpty()) {
      return 0;
    }

    endsWithDelimiter(numbers);

    String[] numbersArray = numbers.split(",");

    return getSum(numbersArray);
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
    int sum = 0;

    for (int i = 0; i < numbersArray.length; i++) {
      try {
        sum += stringToInt(numbersArray[i]);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("integer not found");
      }
    }

    return sum;
  }
}