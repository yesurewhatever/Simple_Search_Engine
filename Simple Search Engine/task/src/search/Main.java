package search;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    private static List<String> people = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data") && args.length > i + 1) {
                initData(args[i + 1]);
            }
        }

        while (true) {
            System.out.println("=== MENU ===");
            System.out.println("1. Search information.");
            System.out.println("2. Print all data.");
            System.out.println("0. Exit.");
            switch (scan.nextInt()) {
                case 1:
                    scan.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    var target = scan.nextLine();
                    var found = search(target, people);
                    found.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    people.forEach(System.out::println);
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

    private static List<String> initData(String fileName) {
        System.out.println("Enter the number of people:");
        var scan = new Scanner(Path.of(fileName));
        int n = scan.nextInt();
        scan.nextLine();

        var people = new ArrayList<String>();
        System.out.println("Enter all people:");
        for (int i = 0; i < n; i++) {
            people.add(scan.nextLine());
        }
        System.out.println();
        return people;
    }

    private static List<String> search(String target, List<String> list) {
        var resList = new ArrayList<String>();
        var pattern = Pattern.compile(target, Pattern.CASE_INSENSITIVE);
        list.forEach(x -> {
            if (pattern.matcher(x).find()) {
                resList.add(x);
            }
        });
        if (resList.isEmpty()) {
            resList.add("No matching people found.");
        } else {
            resList.add(0, "Found people:");
        }
        return resList;
    }
}

