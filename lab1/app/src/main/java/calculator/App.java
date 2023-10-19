package calculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter a string: ");
            String str = scan.nextLine();
            str = str.replace("\\n", "\n");

            if (str.equals("exit")) {
                break;
            }

            try {
                System.out.print("Result: " + calculator.Add(str));
                System.out.println();
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }

        scan.close();
    }
}
