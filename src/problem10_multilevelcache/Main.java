package problem10_multilevelcache;

public class Main {

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        cache.getVideo("video_123");  // DB → L2
        cache.getVideo("video_123");  // L2 → L1
        cache.getVideo("video_123");  // L1

        cache.getVideo("video_999");  // DB

        cache.invalidate("video_123");

        cache.getStatistics();
    }
}