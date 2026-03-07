package problem3_dnscache;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        DNSCache cache = new DNSCache(5);

        System.out.println(cache.resolve("google.com"));
        System.out.println(cache.resolve("google.com"));
        System.out.println(cache.resolve("openai.com"));

        Thread.sleep(11000);

        System.out.println(cache.resolve("google.com"));

        cache.getCacheStats();
    }
}