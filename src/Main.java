import cache.Cache;
import cache.LRUCache;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing In-Memory LRU Cache (Capacity: 3) ===");
        Cache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "User_101");
        cache.put(2, "User_102");
        cache.put(3, "User_103");
        System.out.println("Cache size after 3 insertions: " + cache.size());

        // Access key 1 -> makes key 1 Most Recently Used; key 2 becomes Least Recently Used
        System.out.println("Get key 1: " + cache.get(1));

        // Insert key 4 -> should evict key 2 (LRU)
        cache.put(4, "User_104");
        System.out.println("Get key 2 (should be null/evicted): " + cache.get(2));
        System.out.println("Get key 3: " + cache.get(3));
        System.out.println("Get key 4: " + cache.get(4));

        // Update existing key 1
        cache.put(1, "User_101_Updated");
        System.out.println("Updated key 1: " + cache.get(1));

        // Test invalid capacity exception handling
        try {
            new LRUCache<>(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception for 0 capacity: " + e.getMessage());
        }
    }
}