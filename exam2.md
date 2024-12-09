# Priority Queues

## Overview
A priority queue is a data structure where each element is associated with a priority, and elements are dequeued in order of their priority. It supports the following core operations:

- **Insert**: Add an element with a priority.
- **Delete-Max**: Remove and return the element with the highest priority.

### Types of Queues
- **Stack**: Removes the most recently added item (LIFO).
- **Queue**: Removes the least recently added item (FIFO).
- **Priority Queue**: Removes the item with the highest priority.

---

## API for Priority Queues

### Basic Operations
```java
public class MaxPQ<Key extends Comparable<Key>> {
    MaxPQ()                     // Create an empty priority queue
    MaxPQ(Key[] a)             // Create a priority queue with given keys
    void insert(Key v)         // Insert a key into the priority queue
    Key delMax()               // Return and remove the largest key
    boolean isEmpty()          // Check if the priority queue is empty
    Key max()                  // Return the largest key
    int size()                 // Get the number of entries in the priority queue
}
```
- **Key** must implement `Comparable`.
- Duplicate keys are allowed.

---

## Applications

1. **Event-Driven Simulation**: Managing customers in a line or colliding particles.
2. **Numerical Computation**: Reducing roundoff errors.
3. **Discrete Optimization**: Tasks like bin packing and scheduling.
4. **Artificial Intelligence**: A* search algorithm.
5. **Data Compression**: Huffman coding.
6. **Graph Algorithms**: Dijkstra's shortest path and Prim's MST.
7. **Statistics**: Computing the online median in a data stream.

---

## Implementations

### Unordered Array
- **Insert**: \( O(1) \)
- **Delete-Max**: \( O(n) \)

### Ordered Array
- **Insert**: \( O(n) \)
- **Delete-Max**: \( O(1) \)

### Binary Heap (Common Choice)
- **Insert**: \( O(\log n) \)
- **Delete-Max**: \( O(\log n) \)
- Provides a good balance of performance.

---

## Binary Heap

### Definition
A binary heap is a complete binary tree represented as an array.

- **Heap-Ordered Property**: Parent keys are larger than their children.
- **Array Representation**:
  - Root at index 1.
  - Parent of node \( k \) is at \( k/2 \).
  - Children of node \( k \) are at \( 2k \) and \( 2k+1 \).

#### Example Array Representation:
| Index | Key  |
|-------|-------|
| 1     | T     |
| 2     | S     |
| 3     | R     |
| 4     | P     |
| 5     | N     |

---

### Operations on a Binary Heap

1. **Insert**:
   - Add the new element at the end.
   - Perform **swim** operation to restore heap order.

   ```java
   private void swim(int k) {
       while (k > 1 && less(k/2, k)) {
           exch(k, k/2);
           k = k/2;
       }
   }
   ```

2. **Delete-Max**:
   - Swap the root with the last element.
   - Remove the last element.
   - Perform **sink** operation to restore heap order.

   ```java
   private void sink(int k) {
       while (2*k <= n) {
           int j = 2*k;
           if (j < n && less(j, j+1)) j++;
           if (!less(k, j)) break;
           exch(k, j);
           k = j;
       }
   }
   ```

3. **Reheapify**:
   - Bottom-up **swim** for insertion.
   - Top-down **sink** for deletion.

---

## Cost Summary
| Implementation   | Insert       | Delete-Max   | Max           |
|------------------|--------------|--------------|---------------|
| Unordered Array  | \( O(1) \)    | \( O(n) \)    | \( O(n) \)     |
| Ordered Array    | \( O(n) \)    | \( O(1) \)    | \( O(1) \)     |
| Binary Heap      | \( O(\log n) \) | \( O(\log n) \) | \( O(1) \)     |

---

## Advanced Topics

1. **D-Way Heaps**:
   - Height: \( \sim \log_d n \).
   - Insert: \( O(d \log_d n) \).
   - Delete-Max: \( O(d \log_d n) \).

2. **Floyd's Heap Construction**:
   - Sink nodes starting from the last internal node.

3. **Immutability**:
   - Use immutable keys to ensure consistency.

---

## Study Prompts

1. What are the time complexities of insert and delete-max in a binary heap?
2. How is the heap-order property maintained in a binary heap?
3. Describe the swim and sink operations with examples.
4. What are the advantages of using a binary heap over other implementations of priority queues?

---

## Visuals
- **Heap Representation**: Draw a binary tree and show its array equivalent.
- **Operation Examples**: Illustrate insert and delete-max step by step with diagrams.

---

# Symbol Tables

## Overview
A symbol table is a data structure for storing key-value pairs, enabling efficient search, insertion, and deletion operations.

### Applications
- **DNS Lookup**: Map domain names to IP addresses.
- **File Systems**: Locate files using filenames.
- **Compilers**: Store properties of variables.

---

## API for Symbol Tables

### Basic Operations
```java
public class ST<Key, Value> {
    ST()                        // Create an empty symbol table
    void put(Key key, Value val) // Insert key-value pair
    Value get(Key key)          // Retrieve value associated with key
    void delete(Key key)        // Remove key-value pair
    boolean contains(Key key)   // Check if key exists
    boolean isEmpty()           // Check if table is empty
    int size()                  // Get the number of key-value pairs
    Iterable<Key> keys()        // Get all keys
}
```

---

## Implementations

| Implementation        | Search (Average Case) | Search (Worst Case) | Insert (Average Case) | Insert (Worst Case) |
|------------------------|-----------------------|----------------------|------------------------|---------------------|
| Unordered Linked List | \( O(n) \)            | \( O(n) \)           | \( O(1) \)             | \( O(1) \)          |
| Binary Search Tree    | \( O(\log n) \)       | \( O(n) \)           | \( O(\log n) \)        | \( O(n) \)          |
| Hash Table            | \( O(1) \)           | \( O(n) \)           | \( O(1) \)            | \( O(n) \)          |

---

## Study Prompts
1. What are the differences between hash tables and binary search trees?
2. How does the put operation work in a symbol table?
3. Discuss the importance of key immutability in symbol tables.

---

# Binary Search Trees (BSTs)

## Overview
A binary search tree (BST) is a binary tree where each node has a key, and:
- All keys in the left subtree are smaller.
- All keys in the right subtree are larger.

---

## Operations

1. **Search**:
   - Traverse left or right depending on the key comparison.

   ```java
   public Value get(Key key) {
       Node x = root;
       while (x != null) {
           int cmp = key.compareTo(x.key);
           if      (cmp < 0) x = x.left;
           else if (cmp > 0) x = x.right;
           else return x.val;
       }
       return null;
   }
   ```

2. **Insert**:
   - Traverse to the correct null link and insert a new node.

   ```java
   private Node put(Node x, Key key, Value val) {
       if (x == null) return new Node(key, val);
       int cmp = key.compareTo(x.key);
       if      (cmp < 0) x.left  = put(x.left,  key, val);
       else if (cmp > 0) x.right = put(x.right, key, val);
       else x.val = val;
       return x;
   }
   ```

---

## Performance
| Operation | Best Case      | Average Case   | Worst Case     |
|-----------|----------------|----------------|----------------|
| Search    | \( O(\log n) \) | \( O(\log n) \) | \( O(n) \)       |
| Insert    | \( O(\log n) \) | \( O(\log n) \) | \( O(n) \)       |
| Delete    | \( O(\log n) \) | \( O(\log n) \) | \( O(n) \)       |

---

## Study Prompts
1. Explain the structure and properties of a BST.
2. How does the shape of a BST affect its performance?
3. What are the benefits of balanced trees compared to unbalanced ones?

---

# Balanced Search Trees

## Overview
Balanced search trees maintain a height of \( O(\log n) \), ensuring efficient search, insert, and delete operations.

### Types
1. **2-3 Trees**:
   - Nodes contain 1 or 2 keys.
   - Guarantees perfect balance.

2. **Red-Black Trees**:
   - Binary representation of 2-3 trees.
   - Maintains balance using color properties.

3. **B-Trees**:
   - Generalization of 2-3 trees for databases.

---

## Operations
| Operation | Search          | Insert          | Delete          |
|-----------|-----------------|-----------------|-----------------|
| 2-3 Tree  | \( O(\log n) \)  | \( O(\log n) \)  | \( O(\log n) \)  |
| Red-Black Tree | \( O(\log n) \)  | \( O(\log n) \)  | \( O(\log n) \)  |
| B-Tree    | \( O(\log n) \)  | \( O(\log n) \)  | \( O(\log n) \)  |

---

## Study Prompts
1. What are the advantages of balanced search trees over BSTs?
2. Describe the relationship between red-black trees and 2-3 trees.
3. How do B-trees handle large datasets efficiently?

---

# Hash Tables

## Overview
Hash tables use a hash function to map keys to indices in an array for efficient lookup.

---

## Collision Resolution
1. **Separate Chaining**:
   - Use linked lists to handle collisions.
2. **Linear Probing**:
   - Use the next available slot in the array.

---

## Performance
| Operation | Average Case   | Worst Case     |
|-----------|----------------|----------------|
| Search    | \( O(1) \)     | \( O(n) \)     |
| Insert    | \( O(1) \)     | \( O(n) \)     |
| Delete    | \( O(1) \)     | \( O(n) \)     |

---

## Study Prompts
1. How does a hash function affect performance?
2. Compare separate chaining and linear probing.
3. What are the potential downsides of hash tables?
