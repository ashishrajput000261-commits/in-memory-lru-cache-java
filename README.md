# In-Memory LRU Caching System (Java)

A lightweight, generic in-memory key-value caching system implemented in Java using the **Least Recently Used (LRU)** eviction policy.

## Architecture & Design
- **O(1) Lookup & Eviction:** Combines a `HashMap` for constant-time key lookup with a custom **Doubly Linked List** for constant-time node insertion and removal.
- **OOP Principles:** Built using clean interfaces (`Cache<K, V>`), generics, encapsulation, and sentinel dummy `head`/`tail` nodes to eliminate null-edge conditions.

## Time & Space Complexity
| Operation | Time Complexity | Description |
| :--- | :--- | :--- |
| `get(key)` | $O(1)$ | Fetches value from map and moves node to MRU head |
| `put(key, value)` | $O(1)$ | Inserts/updates entry; evicts LRU tail node if full |
| **Space** | $O(C)$ | Where $C$ is the maximum cache capacity |

## How to Run
```bash
javac -d out src/model/*.java src/cache/*.java src/Main.java
java -cp out Main