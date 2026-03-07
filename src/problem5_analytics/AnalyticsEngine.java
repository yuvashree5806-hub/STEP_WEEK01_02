package problem5_analytics;

import java.util.*;

public class AnalyticsEngine {

    // page -> visit count
    private HashMap<String, Integer> pageViews = new HashMap<>();

    // page -> unique visitors
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // source -> count
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    // Process event
    public void processEvent(PageViewEvent event) {

        // page view count
        pageViews.put(
                event.url,
                pageViews.getOrDefault(event.url, 0) + 1
        );

        // unique visitors
        uniqueVisitors
                .computeIfAbsent(event.url, k -> new HashSet<>())
                .add(event.userId);

        // traffic source
        trafficSources.put(
                event.source,
                trafficSources.getOrDefault(event.source, 0) + 1
        );
    }

    // Get top pages
    public List<Map.Entry<String, Integer>> getTopPages() {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(
                        Map.Entry.comparingByValue()
                );

        for (Map.Entry<String, Integer> entry : pageViews.entrySet()) {

            pq.offer(entry);

            if (pq.size() > 10)
                pq.poll();
        }

        List<Map.Entry<String, Integer>> result = new ArrayList<>();

        while (!pq.isEmpty())
            result.add(pq.poll());

        Collections.reverse(result);

        return result;
    }

    // Show dashboard
    public void getDashboard() {

        System.out.println("Top Pages:");

        List<Map.Entry<String, Integer>> topPages = getTopPages();

        int rank = 1;

        for (Map.Entry<String, Integer> entry : topPages) {

            int unique = uniqueVisitors.get(entry.getKey()).size();

            System.out.println(
                    rank++ + ". " + entry.getKey() +
                            " - " + entry.getValue() +
                            " views (" + unique + " unique)"
            );
        }

        System.out.println("\nTraffic Sources:");

        int total = trafficSources.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        for (String source : trafficSources.keySet()) {

            double percent =
                    (trafficSources.get(source) * 100.0) / total;

            System.out.println(
                    source + ": " + String.format("%.2f", percent) + "%"
            );
        }
    }
}