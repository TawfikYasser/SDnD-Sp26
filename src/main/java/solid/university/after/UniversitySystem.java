package solid.university.after;

public class UniversitySystem {

    private final RegistrationRepository repository;
    private final EmailService            emailService;
    private final ReportService           reportService;
    private final RefundService           refundService;

    public UniversitySystem(RegistrationRepository repository,
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
        System.out.println("Calculating fees... Fees: " + fees);
        repository.save(course, fees);
        emailService.sendEmail("Registration Successful");
        reportService.generateReport();
        refundService.processRefund();
    }
}
