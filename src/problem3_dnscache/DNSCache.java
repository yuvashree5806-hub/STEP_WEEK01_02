package problem3_dnscache;

import java.util.*;

public class DNSCache {

    private final int capacity;
    private final LinkedHashMap<String, DNSEntry> cache;

    private int hits = 0;
    private int misses = 0;

    public DNSCache(int capacity) {

        this.capacity = capacity;

        // LinkedHashMap used for LRU
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {

            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > DNSCache.this.capacity;
            }
        };

        startCleanupThread();
    }

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            return "Cache HIT → " + entry.ipAddress;
        }

        if (entry != null && entry.isExpired()) {
            cache.remove(domain);
            System.out.println("Cache EXPIRED for " + domain);
        }

        misses++;

        // simulate upstream DNS lookup
        String newIP = queryUpstreamDNS(domain);

        cache.put(domain, new DNSEntry(domain, newIP, 10));

        return "Cache MISS → " + newIP;
    }

    private String queryUpstreamDNS(String domain) {

        // fake DNS lookup
        Random rand = new Random();

        return "172.217." + rand.nextInt(100) + "." + rand.nextInt(255);
    }

    public void getCacheStats() {

        int total = hits + misses;

        double hitRate = total == 0 ? 0 : (hits * 100.0) / total;

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }

    private void startCleanupThread() {

        Thread cleaner = new Thread(() -> {

            while (true) {

                try {
                    Thread.sleep(5000);

                    Iterator<Map.Entry<String, DNSEntry>> iterator = cache.entrySet().iterator();

                    while (iterator.hasNext()) {
                        Map.Entry<String, DNSEntry> entry = iterator.next();

                        if (entry.getValue().isExpired()) {
                            iterator.remove();
                        }
                    }

                } catch (InterruptedException ignored) {
                }
            }

        });

        cleaner.setDaemon(true);
        cleaner.start();
    }
}