package eg.edu.eui.sdnd.sp26;

import java.util.Scanner;

/*
 * Hi, this is single file that runs all the codes.
 * Nothing here knows what any demo actually does, it just delegates
 */
public class Main {
    static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        boolean running = true; // looping
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            System.out.println();
            boolean ranDemo = true;

            switch (choice) {
                case "1"  -> solid.payment.Main.main(args);
                case "2"  -> solid.pacman.Main.main(args);
                case "3"  -> solid.university.Main.main(args);
                case "4"  -> patterns.singleton.printer.Main.main(args);
                case "5"  -> patterns.singleton.pipeline.Main.main(args);
                case "6"  -> patterns.singleton.doublechecked.Main.main(args);
                case "7"  -> patterns.factorymethod.electricityplans.Main.main(args);
                case "8"  -> patterns.factorymethod.toy.Main.main(args);
                case "9"  -> patterns.factorymethod.interview.Main.main(args);
                case "10" -> patterns.abstractfactory.bankloan.Main.main(args);
                case "11" -> patterns.abstractfactory.interview.Main.main(args);
                case "12" -> patterns.abstractfactory.food.Main.main(args);
                case "0"  -> { running = false; ranDemo = false; System.out.println("Goodbye!"); }
                default   -> { ranDemo = false; System.out.println("  Invalid option, try again."); }
            }
            if (ranDemo) {
                System.out.println();
                System.out.print("Enter to return to menu");
                sc.nextLine();
            }
        }

        sc.close();
    }

    static void printMenu() {
        System.out.println("SOLID Principles");
        System.out.println("   1   Payment System (solid)");
        System.out.println("   2   Pacman Game (srp & ocp & lcp)");
        System.out.println("   3   University (srp & ocp & dip)");
        System.out.println("Singleton Pattern");
        System.out.println("   4   Printer");
        System.out.println("   5   Pipeline Logger");
        System.out.println("   6   Double Checked");
        System.out.println("Factory Method");
        System.out.println("   7   Electricity Plans");
        System.out.println("   8   Toy Factory");
        System.out.println("   9   Interview Factories");
        System.out.println("Abstract Factory");
        System.out.println("  10   Bank / Loan");
        System.out.println("  11   Interview");
        System.out.println("  12   Food");
        System.out.println("  0   Exit");
    }
}
