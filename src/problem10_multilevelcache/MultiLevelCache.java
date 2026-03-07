package problem10_multilevelcache;

import java.util.HashMap;

public class MultiLevelCache {

    private LRUCache<String, VideoData> L1;
    private LRUCache<String, VideoData> L2;

    private HashMap<String, VideoData> database;

    private int l1Hits = 0;
    private int l2Hits = 0;
    private int dbHits = 0;

    public MultiLevelCache() {

        L1 = new LRUCache<>(10000);
        L2 = new LRUCache<>(100000);

        database = new HashMap<>();

        // sample database data
        database.put("video_123", new VideoData("video_123", "Netflix Movie A"));
        database.put("video_999", new VideoData("video_999", "Netflix Movie B"));
    }

    public VideoData getVideo(String videoId) {

        if (L1.containsKey(videoId)) {

            l1Hits++;
            System.out.println("L1 Cache HIT (0.5ms)");

            return L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {

            l2Hits++;
            System.out.println("L1 MISS → L2 HIT (5ms)");

            VideoData video = L2.get(videoId);

            // promote to L1
            L1.put(videoId, video);

            return video;
        }

        if (database.containsKey(videoId)) {

            dbHits++;
            System.out.println("L1 MISS → L2 MISS → DB HIT (150ms)");

            VideoData video = database.get(videoId);

            // add to L2
            L2.put(videoId, video);

            return video;
        }

        System.out.println("Video not found");
        return null;
    }

    public void invalidate(String videoId) {

        L1.remove(videoId);
        L2.remove(videoId);

        System.out.println("Cache invalidated for " + videoId);
    }

    public void getStatistics() {

        int total = l1Hits + l2Hits + dbHits;

        System.out.println("\nCache Statistics:");

        System.out.println("L1 Hits: " + l1Hits);
        System.out.println("L2 Hits: " + l2Hits);
        System.out.println("DB Hits: " + dbHits);

        double hitRate = (l1Hits + l2Hits) * 100.0 / total;

        System.out.println("Overall Cache Hit Rate: " + hitRate + "%");
    }
}
