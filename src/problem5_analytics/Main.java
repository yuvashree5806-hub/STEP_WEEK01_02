package problem5_analytics;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        AnalyticsEngine engine = new AnalyticsEngine();

        engine.processEvent(new PageViewEvent(
                "/article/breaking-news", "user_123", "google"));

        engine.processEvent(new PageViewEvent(
                "/article/breaking-news", "user_456", "facebook"));

        engine.processEvent(new PageViewEvent(
                "/sports/championship", "user_222", "direct"));

        engine.processEvent(new PageViewEvent(
                "/sports/championship", "user_333", "google"));

        engine.processEvent(new PageViewEvent(
                "/article/breaking-news", "user_123", "google"));

        // simulate dashboard refresh every 5 seconds
        Thread.sleep(5000);

        engine.getDashboard();
    }
}