# Sorting Algorithms: Detailed Notes

## 1. Elementary Sorts

### Key Topics:

1. **Selection Sort**
2. **Insertion Sort**
3. **Shellsort**
4. **Shuffling**

### **Selection Sort**

- **Algorithm Overview**:
  - Iterate through the array to find the smallest element.
  - Swap the smallest element with the first element of the unsorted section.
  - Repeat for the rest of the array.
- **Pseudocode**:
  ```java
  public static void sort(Comparable[] a) {
      int N = a.length;
      for (int i = 0; i < N; i++) {
          int min = i;
          for (int j = i + 1; j < N; j++) {
              if (less(a[j], a[min])) min = j;
          }
          exch(a, i, min);
      }
  }
  ```
- **Complexity**:
  - Comparisons: \( \frac{N(N-1)}{2} \approx \mathcal{O}(N^2) \)
  - Swaps: \( N \)
- **Characteristics**:
  - Simple implementation.
  - Inefficient for large datasets.
  - Insensitive to input order.

### **Insertion Sort**

- **Algorithm Overview**:
  - Build the sorted array one element at a time.
  - Insert each new element into its correct position in the sorted portion.
- **Pseudocode**:
  ```java
  public static void sort(Comparable[] a) {
      int N = a.length;
      for (int i = 1; i < N; i++) {
          for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
              exch(a, j, j-1);
          }
      }
  }
  ```
- **Complexity**:
  - Best Case: \( \mathcal{O}(N) \) (Already sorted array).
  - Worst Case: \( \mathcal{O}(N^2) \) (Reverse order).
- **Characteristics**:
  - Stable sorting algorithm.
  - Efficient for small or nearly sorted datasets.

### **Shellsort**

- **Algorithm Overview**:
  - Generalization of insertion sort.
  - Use increments to reduce the distance between compared elements.
  - Sort elements separated by the gap and progressively reduce the gap.
- **Complexity**:
  - Best Case: \( \mathcal{O}(N \log N) \).
  - Worst Case: Depends on gap sequence.
- **Characteristics**:
  - Faster than selection and insertion sorts for larger datasets.

### **Shuffling**

- **Algorithm Overview**:
  - Randomly rearrange elements in an array.
  - **Fisher-Yates Shuffle Algorithm**:
    ```java
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + StdRandom.uniform(N - i);
            exch(a, i, r);
        }
    }
    ```

---

## 2. Mergesort

### Key Topics:

1. **Top-Down Mergesort**
2. **Bottom-Up Mergesort**
3. **Complexity Analysis**

### **Top-Down Mergesort**

- **Algorithm Overview**:
  - Divide array into two halves.
  - Recursively sort each half.
  - Merge the two sorted halves.
- **Pseudocode**:
  ```java
  public static void sort(Comparable[] a) {
      Comparable[] aux = new Comparable[a.length];
      sort(a, aux, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
      if (hi <= lo) return;
      int mid = lo + (hi - lo) / 2;
      sort(a, aux, lo, mid);
      sort(a, aux, mid + 1, hi);
      merge(a, aux, lo, mid, hi);
  }
  ```
- **Merge Operation**:
  ```java
  private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
      for (int k = lo; k <= hi; k++) {
          aux[k] = a[k];
      }
      int i = lo, j = mid + 1;
      for (int k = lo; k <= hi; k++) {
          if (i > mid) a[k] = aux[j++];
          else if (j > hi) a[k] = aux[i++];
          else if (less(aux[j], aux[i])) a[k] = aux[j++];
          else a[k] = aux[i++];
      }
  }
  ```
- **Complexity**:
  - Comparisons: \( \mathcal{O}(N \log N) \).
  - Space: \( \mathcal{O}(N) \).

### **Bottom-Up Mergesort**

- **Algorithm Overview**:
  - Iterative version of mergesort.
  - Start with small subarrays and merge iteratively into larger subarrays.

### **Stability**:

- Mergesort is stable as it preserves the relative order of equal elements.

---

## 3. Quicksort

### Key Topics:

1. **Partitioning**
2. **Recursive Sorting**
3. **Complexity Analysis**

### **Partitioning**

- **Algorithm Overview**:
  - Choose a pivot.
  - Rearrange array such that elements less than pivot are on the left and greater are on the right.
- **Pseudocode**:
  ```java
  private static int partition(Comparable[] a, int lo, int hi) {
      int i = lo, j = hi + 1;
      Comparable pivot = a[lo];
      while (true) {
          while (less(a[++i], pivot)) if (i == hi) break;
          while (less(pivot, a[--j])) if (j == lo) break;
          if (i >= j) break;
          exch(a, i, j);
      }
      exch(a, lo, j);
      return j;
  }
  ```

### **Recursive Sorting**

- **Pseudocode**:
  ```java
  public static void sort(Comparable[] a) {
      StdRandom.shuffle(a); // Eliminate dependence on input order
      sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable[] a, int lo, int hi) {
      if (hi <= lo) return;
      int j = partition(a, lo, hi);
      sort(a, lo, j - 1);
      sort(a, j + 1, hi);
  }
  ```
- **Complexity**:
  - Best Case: \( \mathcal{O}(N \log N) \).
  - Worst Case: \( \mathcal{O}(N^2) \).

### **Applications**:

- Efficient for in-place sorting.

---

## 4. Stacks and Queues

### Key Topics:

1. **Stacks**
2. **Queues**
3. **Resizing Arrays**
4. **Linked Lists**

### **Stacks**

- **Definition**: LIFO (Last In, First Out) data structure.
- **Operations**:
  - `push`: Add an item to the stack.
  - `pop`: Remove the most recently added item.
  - `peek`: View the most recently added item without removing it.
- **Array Implementation**:
  ```java
  public class FixedCapacityStack {
      private String[] s;
      private int N = 0;

      public FixedCapacityStack(int capacity) {
          s = new String[capacity];
      }

      public void push(String item) {
          s[N++] = item;
      }

      public String pop() {
          return s[--N];
      }
  }
  ```
- **Linked List Implementation**:
  ```java
  public class LinkedStack {
      private Node first = null;

      private class Node {
          String item;
          Node next;
      }

      public void push(String item) {
          Node oldFirst = first;
          first = new Node();
          first.item = item;
          first.next = oldFirst;
      }

      public String pop() {
          String item = first.item;
          first = first.next;
          return item;
      }
  }
  ```

### **Queues**

- **Definition**: FIFO (First In, First Out) data structure.
- **Operations**:
  - `enqueue`: Add an item to the queue.
  - `dequeue`: Remove the least recently added item.
- **Array Implementation**:
  ```java
  public class FixedCapacityQueue {
      private String[] q;
      private int N = 0, first = 0, last = 0;

      public FixedCapacityQueue(int capacity) {
          q = new String[capacity];
      }

      public void enqueue(String item) {
          q[last++] = item;
          if (last == q.length) last = 0;
          N++;
      }

      public String dequeue() {
          String item = q[first];
          q[first] = null;
          first++;
          if (first == q.length) first = 0;
          N--;
          return item;
      }
  }
  ```
- **Linked List Implementation**:
  ```java
  public class LinkedQueue {
      private Node first, last;

      private class Node {
          String item;
          Node next;
      }

      public void enqueue(String item) {
          Node oldLast = last;
          last = new Node();
          last.item = item;
          if (isEmpty()) first = last;
          else oldLast.next = last;
      }

      public String dequeue() {
          String item = first.item;
          first = first.next;
          if (isEmpty()) last = null;
          return item;
      }
  }
  ```

---

## Study Questions

1. What are the differences between selection, insertion, and shellsort?
2. Why is mergesort considered stable?
3. How does quicksort achieve in-place sorting?
4. Compare the advantages of stack and queue implementations using arrays vs linked lists.

---

## Diagrams

1. Selection Sort trace with example.
2. Mergesort divide-and-conquer process.
3. Quicksort partitioning example.
4. Stack and Queue operations illustrated.

Let me know if further refinement or additional sections are needed!
