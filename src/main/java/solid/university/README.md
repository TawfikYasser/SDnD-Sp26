# University Registration System: All Five SOLID Principles

## The Problem

The `UniversitySystem` class in the before version is a good example of what happens when a system grows without any structural discipline. It has one public method, `registerStudent`, and that method does everything. It checks the student type using string comparisons to decide how to print the registration message. It calculates fees. It sends an email. It generates a report. It processes a refund. All of that happens in one place, in one class, in one method.

Every single one of these responsibilities is a reason for this class to change. If the email provider changes, you open this class. If a new student type is added, you open this class and add another `else if`. If the refund logic changes, same file. If the report format changes, same file. If the fee calculation changes, same file. The class is fragile because any edit for any reason touches the same code that handles all the other reasons.

The string-based student type check is also a specific problem on its own. Adding a new student type means modifying existing code rather than extending it. That is a violation of the Open/Closed Principle. The class is not designed to be extended, it is designed to be edited, which means every new feature is also a potential bug.

Because the email service, report service, and refund logic are all called directly inside `registerStudent` with no abstraction in between, the class is tightly coupled to its own internal implementations. You cannot test `registerStudent` in isolation. You cannot swap the email provider without changing the class. There is no seam anywhere to inject a different behavior. This is a Dependency Inversion problem: the high-level orchestration logic is depending directly on low-level implementation details.

## What Changed

The refactored version introduces a `Student` interface with a single `register` method. `RegularStudent`, `ScholarshipStudent`, and `InternationalStudent` each implement it. Adding a new student type now means writing a new class, not editing the system.

Each cross-cutting concern gets its own interface: `EmailService`, `ReportService`, `RefundService`, and `RegistrationRepository`. Each interface has one method and one reason to exist. The concrete implementations (`SimpleEmailService`, `StudentReportService`, `RefundServiceImpl`, `InMemoryRegistrationRepository`) sit behind those interfaces and can be swapped independently.

The `UniversitySystem` class is restructured to receive all of its dependencies through its constructor. It no longer creates or calls anything directly. It delegates to the interfaces it was given. This means you can test `UniversitySystem` with fake implementations of any of its dependencies, and you can change any one of them in production without touching the orchestration logic.

The result is a class that has one job: coordinate the steps of registration in the right order. Everything else is someone else's responsibility.

## Before

```java
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

    public void sendEmail(String message)   { System.out.println("Email sent: " + message); }
    public void generateStudentReport()     { System.out.println("Generating report..."); }
    public void processRefund()             { System.out.println("Processing refund..."); }
}
```

## After

```java
// Student.java
public interface Student {
    void register(String course);
}

// RegularStudent.java
public class RegularStudent implements Student {
    @Override
    public void register(String course) {
        System.out.println("Registering regular student to " + course);
    }
}

// ScholarshipStudent.java
public class ScholarshipStudent implements Student {
    @Override
    public void register(String course) {
        System.out.println("Registering scholarship student to " + course);
    }
}

// InternationalStudent.java
public class InternationalStudent implements Student {
    @Override
    public void register(String course) {
        System.out.println("Registering international student to " + course);
    }
}

// EmailService.java
public interface EmailService {
    void sendEmail(String message);
}

// ReportService.java
public interface ReportService {
    void generateReport();
}

// RefundService.java
public interface RefundService {
    void processRefund();
}

// RegistrationRepository.java
public interface RegistrationRepository {
    void save(String course, double fees);
}

// UniversitySystem.java
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
```

And this is how it gets wired together at the call site:

```java
UniversitySystem sys = new UniversitySystem(
    new InMemoryRegistrationRepository(),
    new SimpleEmailService(),
    new StudentReportService(),
    new RefundServiceImpl()
);

sys.registerStudent(new RegularStudent(),       "software design and development", 5000.0);
sys.registerStudent(new ScholarshipStudent(),   "data structures",                 3000.0);
sys.registerStudent(new InternationalStudent(), "algorithms",                      8000.0);
```

Every piece is now independently replaceable. You can swap `SimpleEmailService` for an SMTP implementation or a mock in a test without changing a single line inside `UniversitySystem`. Adding a new student type takes one new file and nothing else. The system is open for extension and closed for modification, each class has one reason to change, and the high-level logic no longer cares how its dependencies work internally.
