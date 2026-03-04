package solid.university.before;

public class UniversitySystem {

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

    public void sendEmail(String message)      { System.out.println("Email sent: " + message); }
    public void generateStudentReport()        { System.out.println("Generating report..."); }
    public void processRefund()                { System.out.println("Processing refund..."); }
}
