package solid.university.after;

public class UniversitySystemAfter {

    private RegistrationRepository repository;
    private EmailService emailService;
    private ReportService reportService;
    private RefundService refundService;

    public UniversitySystemAfter(RegistrationRepository repository,
                                 EmailService emailService,
                                 ReportService reportService,
                                 RefundService refundService) {
        this.repository    = repository;
        this.emailService  = emailService;
        this.reportService = reportService;
        this.refundService = refundService;
    }

    public void registerStudent(Student student, String course, double fees) {
        student.register(course);

        System.out.println("Calculating fees...");
        System.out.println("Fees: " + fees);

        repository.save(course, fees);
        emailService.sendEmail("Registration Successful");
        reportService.generateReport();
        refundService.processRefund();
    }
}
