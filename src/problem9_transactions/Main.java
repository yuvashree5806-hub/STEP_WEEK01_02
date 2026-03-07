package problem9_transactions;

public class Main {

    public static void main(String[] args) {

        FraudDetector detector = new FraudDetector();

        long now = System.currentTimeMillis();

        detector.addTransaction(new Transaction(1, 500, "Store A", "acc1", now));
        detector.addTransaction(new Transaction(2, 300, "Store B", "acc2", now + 900000));
        detector.addTransaction(new Transaction(3, 200, "Store C", "acc3", now + 1800000));
        detector.addTransaction(new Transaction(4, 500, "Store A", "acc4", now));

        System.out.println("Two Sum (500):");
        System.out.println(detector.findTwoSum(500));

        System.out.println("\nTwo Sum within 1 hour:");
        System.out.println(detector.findTwoSumWithinHour(500));

        System.out.println("\nDuplicate detection:");
        System.out.println(detector.detectDuplicates());

        System.out.println("\nK-Sum (3 transactions → 1000):");
        detector.findKSum(3, 1000);
    }
}