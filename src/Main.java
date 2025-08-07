class Student {
    String name;
    int age;
    String course;
    double grade1;
    double grade2;
    double grade3;


    public Student(String name, int age, String course, double grade1, double grade2, double grade3) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println("Grades: " + " " + grade1 + ", " + grade2 +  ", " + grade3);
    }


    public double calculateAverage() {
        return (grade1 + grade2 + grade3) / 3;
    }


    public String getLetterGrade() {
        double average = calculateAverage();
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }


    public boolean isPassing() {
        return calculateAverage() >= 70;
    }
}
public class Main {
    public static void main(String[] args) {

        Student student1 = new Student("John Doe", 99, "BSIT", 95, 92, 91);
        Student student2 = new Student("Jane Doe", 88, "BSIT", 80, 84, 87);
        Student student3 = new Student("CIT Doe", 77, "BSIT", 60, 60, 60);

        int passingCount = 0;

        System.out.println("");
        student1.displayInfo();
        double avg1 = student1.calculateAverage();
        System.out.println("Average: " + avg1);
        String grade1 = student1.getLetterGrade();
        System.out.println("Letter Grade: " + grade1);
        boolean pass1 = student1.isPassing();
        if (pass1) {
            System.out.println("Status: PASSING");
            passingCount++;
        } else {
            System.out.println("Status: FAILING");
        }


        System.out.println("");
        student2.displayInfo();
        double avg2 = student2.calculateAverage();
        System.out.println("Average: " + avg2);
        String grade2 = student2.getLetterGrade();
        System.out.println("Letter Grade: " + grade2);
        boolean pass2 = student2.isPassing();
        if (pass2) {
            System.out.println("Status: PASSING");
            passingCount++;
        } else {
            System.out.println("Status: FAILING");
        }


        System.out.println("");
        student3.displayInfo();
        double avg3 = student3.calculateAverage();
        System.out.println("Average: " + avg3);
        String grade3 = student3.getLetterGrade();
        System.out.println("Letter Grade: " + grade3);
        boolean pass3 = student3.isPassing();
        if (pass3) {
            System.out.println("Status: PASSING");
            passingCount++;
        } else {
            System.out.println("Status: FAILING");
        }

        System.out.println("");
        System.out.println("Summary: " + passingCount + " out of 3 students are passing.");
    }
}
