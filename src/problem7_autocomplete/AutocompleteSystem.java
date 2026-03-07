package problem7_autocomplete;

import java.util.*;

public class AutocompleteSystem {

    private TrieNode root = new TrieNode();

    // query → frequency
    private HashMap<String, Integer> frequencyMap = new HashMap<>();

    // Insert query
    public void insert(String query) {

        TrieNode node = root;

        for (char c : query.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isEnd = true;

        frequencyMap.put(
                query,
                frequencyMap.getOrDefault(query, 0) + 1
        );
    }

    // Search suggestions
    public List<String> search(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c))
                return new ArrayList<>();

            node = node.children.get(c);
        }

        List<String> results = new ArrayList<>();
        dfs(node, prefix, results);

        // get top 10
        PriorityQueue<String> pq =
                new PriorityQueue<>(
                        (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
                );

        for (String q : results) {

            pq.offer(q);

            if (pq.size() > 10)
                pq.poll();
        }

        List<String> suggestions = new ArrayList<>();

        while (!pq.isEmpty())
            suggestions.add(pq.poll());

        Collections.reverse(suggestions);

        return suggestions;
    }

    // DFS to collect queries
    private void dfs(TrieNode node, String current, List<String> results) {

        if (node.isEnd)
            results.add(current);

        for (char c : node.children.keySet()) {
            dfs(node.children.get(c), current + c, results);
        }
    }

    // Update frequency when user searches
    public void updateFrequency(String query) {

        insert(query);

        System.out.println(
                query + " frequency: " + frequencyMap.get(query)
        );
    }
}