package cache;

import model.Node;
import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cacheMap;
    private final Node<K, V> head; // Most Recently Used (MRU) side
    private final Node<K, V> tail; // Least Recently Used (LRU) side

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cache capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = cacheMap.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        Node<K, V> existingNode = cacheMap.get(key);
        if (existingNode != null) {
            existingNode.value = value;
            moveToHead(existingNode);
            return;
        }

        if (cacheMap.size() == capacity) {
            Node<K, V> lruNode = removeTail();
            cacheMap.remove(lruNode.key);
        }

        Node<K, V> newNode = new Node<>(key, value);
        cacheMap.put(key, newNode);
        addToHead(newNode);
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public void clear() {
        cacheMap.clear();
        head.next = tail;
        tail.prev = head;
    }

    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }

    private Node<K, V> removeTail() {
        Node<K, V> lruNode = tail.prev;
        removeNode(lruNode);
        return lruNode;
    }
}