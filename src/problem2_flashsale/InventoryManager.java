package problem2_flashsale;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryManager {

    // productId -> stock
    private ConcurrentHashMap<String, AtomicInteger> inventory;

    // productId -> waiting list
    private ConcurrentHashMap<String, Queue<Integer>> waitingList;

    public InventoryManager() {
        inventory = new ConcurrentHashMap<>();
        waitingList = new ConcurrentHashMap<>();
    }

    // Add product
    public void addProduct(String productId, int stock) {
        inventory.put(productId, new AtomicInteger(stock));
        waitingList.put(productId, new LinkedList<>());
    }

    // Check stock
    public int checkStock(String productId) {
        AtomicInteger stock = inventory.get(productId);
        if (stock == null) return 0;
        return stock.get();
    }

    // Purchase item
    public synchronized String purchaseItem(String productId, int userId) {

        AtomicInteger stock = inventory.get(productId);

        if (stock == null) {
            return "Product not found";
        }

        if (stock.get() > 0) {
            int remaining = stock.decrementAndGet();
            return "Success, " + remaining + " units remaining";
        }
        else {
            Queue<Integer> queue = waitingList.get(productId);
            queue.add(userId);
            return "Added to waiting list, position #" + queue.size();
        }
    }

    // Show waiting list
    public Queue<Integer> getWaitingList(String productId) {
        return waitingList.get(productId);
    }
}