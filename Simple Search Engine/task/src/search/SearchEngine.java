package search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchEngine {
    private List<String> data = new ArrayList<>();
    private Map<String, Set<Integer>> invertedIndex = new HashMap<>();

    public void initData(String fileName) {
        try (var reader = Files.newBufferedReader(Path.of(fileName))) {
            while (reader.ready()) {
                data.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < data.size(); i++) {
            for (String s : data.get(i).split("\\s+")) {
                if (!invertedIndex.containsKey(s)) {
                    invertedIndex.put(s, new LinkedHashSet<>());
                }
                invertedIndex.get(s).add(i);
            }
        }
    }

    public void printAll() {
        data.forEach(System.out::println);
    }

    public void search(String target) {
        var indexes = invertedIndex.get(target);
        if (indexes == null) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(indexes.size() + "persons found:");
            indexes.forEach(integer -> System.out.println(data.get(integer)));
        }
    }
}
