package problem9_transactions;

import java.util.*;

public class FraudDetector {

    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    // Classic Two-Sum
    public List<String> findTwoSum(int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                Transaction other = map.get(complement);

                result.add("(" + other.id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }

        return result;
    }

    // Two-Sum within 1 hour
    public List<String> findTwoSumWithinHour(int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                Transaction other = map.get(complement);

                long diff = Math.abs(t.time - other.time);

                if (diff <= 3600000) { // 1 hour
                    result.add("(" + other.id + ", " + t.id + ")");
                }
            }

            map.put(t.amount, t);
        }

        return result;
    }

    // Duplicate detection
    public List<String> detectDuplicates() {

        HashMap<String, List<String>> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (Transaction t : transactions) {

            String key = t.amount + "-" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t.account);
        }

        for (String key : map.keySet()) {

            List<String> accounts = map.get(key);

            if (accounts.size() > 1) {
                result.add(key + " accounts: " + accounts);
            }
        }

        return result;
    }

    // K-Sum (simple recursive approach)
    public void findKSum(int k, int target) {
        kSumHelper(0, k, target, new ArrayList<>());
    }

    private void kSumHelper(int start, int k, int target, List<Integer> path) {

        if (k == 0 && target == 0) {
            System.out.println(path);
            return;
        }

        if (k == 0 || start >= transactions.size())
            return;

        for (int i = start; i < transactions.size(); i++) {

            Transaction t = transactions.get(i);

            path.add(t.id);

            kSumHelper(i + 1, k - 1, target - t.amount, path);

            path.remove(path.size() - 1);
        }
    }
}
