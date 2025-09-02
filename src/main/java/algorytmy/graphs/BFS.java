package algorytmy.graphs;

import java.util.*;

public class BFS {

    public static void main(String[] args) {

        Map<String, List<String>> cities = new HashMap<>();

        cities.put("Warszawa", List.of("Berlin", "Kolonia"));
        cities.put("Berlin", List.of("Warszawa", "Chicago"));
        cities.put("Chicago", List.of("Berlin"));
        cities.put("Kolonia", List.of("Warszawa", "Madryt", "Londyn"));
        cities.put("Madryt", List.of("Kolonia", "Nowy Jork", "Londyn"));
        cities.put("Nowy Jork", List.of("Madryt", "Londyn", "Tokio"));
        cities.put("Londyn", List.of("Kolonia", "Madryt", "Nowy Jork", "Tokio"));
        cities.put("Tokio", List.of("Nowy Jork", "Londyn"));

        bfs(cities, "Warszawa", "Madryt");
    }

    private static void bfs(Map<String, List<String>> cities, String startingNode, String endingNode) {

        List<String> visited = new ArrayList<>();
        Deque<String> toBeVisited = new LinkedList<>();
        Map<String, Integer> levels = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        toBeVisited.add(startingNode);
        levels.put(startingNode, 0);

        while (!toBeVisited.isEmpty()) {
            String current = toBeVisited.poll();
            visited.add(current);
            System.out.println("Visited - " + current + " - level - " + levels.getOrDefault(current, 0));
            for (String child : cities.get(current)) {
                int level = levels.get(current) + 1;
                if (!visited.contains(child) && !toBeVisited.contains(child)) {
                    toBeVisited.add(child);
                    //System.out.println("To be visited " + level + " - " + child);
                    levels.putIfAbsent(child, level);
                    previous.putIfAbsent(child, current);
                }
            }
        }

        final var shortestPath = new StringBuilder();
        while (previous.containsKey(endingNode) && previous.get(endingNode) != null) {
            shortestPath.append(endingNode).append(" <- ");
            endingNode = previous.get(endingNode);
        }
        System.out.println(shortestPath.append(startingNode));
    }
}
