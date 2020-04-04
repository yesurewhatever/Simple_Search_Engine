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
                s = s.toLowerCase();
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

    public void search(String target, String strategy) {
        Set<String> found;
        switch (strategy) {
            case "ANY":
                found = searchAny(target);
                break;
            case "NONE":
                found = searchNone(target);
                break;
            case "ALL":
            default:
                found = searchAll(target);
                break;
        }
        if (found.isEmpty()) {
            System.out.println("Nothing found");
        } else {
            System.out.println(found.size() + " persons found");
            found.forEach(System.out::println);
        }
    }

    private Set<String> searchAll(String target) {
        var indexes = invertedIndex.get(target);
        var set = new LinkedHashSet<String>();
        if (indexes != null) {
            indexes.forEach(integer -> set.add(data.get(integer)));
        }
        return set;
    }

    private Set<String> searchAny(String target) {
        var keywords = target.split("\\s+");
        var set = new LinkedHashSet<String>();
        for (String keyword : keywords) {
            set.addAll(searchAll(keyword));
        }
        return set;
    }

    private Set<String> searchNone(String target) {
        var set = new LinkedHashSet<>(data);
        var exclude = searchAny(target);
        set.removeAll(exclude);
        return set;
    }
}
