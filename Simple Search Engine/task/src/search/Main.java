package search;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var engine = new SearchEngine();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data") && args.length > i + 1) {
                engine.initData(args[i + 1]);
            }
        }

        var scan = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            switch (scan.nextInt()) {
                case 1:
                    scan.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    engine.search(scan.nextLine());
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    engine.printAll();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }
    }
}

