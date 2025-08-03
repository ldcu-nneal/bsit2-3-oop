import java.util.Scanner;

public class LabActivity2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNumber = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter Number " + i + ": ");
            int number = scanner.nextInt();
            totalNumber += number;
        }

        System.out.println("\nTotal Number: " + totalNumber);
        scanner.close();
    }
}
