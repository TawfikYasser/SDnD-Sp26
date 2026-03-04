package solid.university.before;

public class UniversitySystemBefore {

    public void registerStudent(String studentType, String course, double fees) {

        if (studentType.equals("REGULAR")) {
            System.out.println("Registering regular student to " + course);
        } else if (studentType.equals("SCHOLARSHIP")) {
            System.out.println("Registering scholarship student to " + course);
        } else if (studentType.equals("INTERNATIONAL")) {
            System.out.println("Registering international student to " + course);
        }

        System.out.println("Calculating fees...");
        System.out.println("Fees: " + fees);

        sendEmail("Registration Successful");
        generateStudentReport();
        processRefund();
    }

    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }

    public void generateStudentReport() {
        System.out.println("Generating report...");
    }

    public void processRefund() {
        System.out.println("Processing refund...");
    }

    public static void run() {
        UniversitySystemBefore system = new UniversitySystemBefore();
        system.registerStudent("REGULAR",       "Software Design and Development", 5000.0);
        system.registerStudent("SCHOLARSHIP",   "Data Structures",                 3000.0);
        system.registerStudent("INTERNATIONAL", "Algorithms",                      8000.0);
    }
}
