package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (var scan = new Scanner(System.in)) {
            System.out.println("Enter the number of people:");
            int n = scan.nextInt();
            scan.nextLine();
            System.out.println();

            var people = new ArrayList<String>();
            System.out.println("Enter all people:");
            for (int i = 0; i < n; i++) {
                people.add(scan.nextLine());
            }
            System.out.println();

            System.out.println("Enter number of search queries:");
            int m = scan.nextInt();
            scan.nextLine();
            System.out.println();

            for (int i = 0; i < m; i++) {
                System.out.println("Enter data to search for:");
                var searchFor = scan.nextLine();
                System.out.println();

                var found = search(searchFor, people);
                found.forEach(System.out::println);
            }
        }
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

