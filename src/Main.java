import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("STUDENT INFORMATION");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Course: ");
        String course = scanner.nextLine();
        System.out.print("Section: ");
        String section = scanner.nextLine();

        System.out.println("\nSTUDENT INFORMATION");
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + firstName + " " + lastName);
        System.out.println("Course: " + course);
        System.out.println("Section: " + section);

        System.out.println("\nEnter Student Scores: ");
        System.out.print("Midterm Exam Score (out of 100): ");
        int midtermExam = scanner.nextInt();
        System.out.print("Final Exam Score (out of 100): ");
        int finalExam = scanner.nextInt();
        System.out.print("Project Score ( out of 100): ");
        int project = scanner.nextInt();
        System.out.print("Attendance Percentage (out of 100): ");
        int attendance = scanner.nextInt();

        int totalScore = midtermExam + finalExam + project + attendance;
        double average = totalScore / 400.0 * 100;

        String status = (average >= 75) ? "PASSED" : "FAILED";

        System.out.println("\nSTUDENT SCORE");
        System.out.println("Midterm Exam Score: " + midtermExam);
        System.out.println("Final Exam Score: " + finalExam);
        System.out.println("Project Score: " + project);
        System.out.println("Attendance Score: " + attendance);
        System.out.printf("\nAverage Score: %.2f\n", average);
        System.out.println("Remarks: " + status);

        scanner.close();

    }
}
