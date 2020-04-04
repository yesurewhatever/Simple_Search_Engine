package search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchEngine {
    private List<String> data = new ArrayList<>();

    public void initData(String fileName) {
        try (var reader = Files.newBufferedReader(Path.of(fileName))) {
            while (reader.ready()) {
                data.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAll() {
        data.forEach(System.out::println);
    }

    public void search(String target) {
        var found = new ArrayList<String>();
        var pattern = Pattern.compile(target, Pattern.CASE_INSENSITIVE);
        data.forEach(x -> {
            if (pattern.matcher(x).find()) {
                found.add(x);
            }
        });
        found.forEach(System.out::println);
    }
}
