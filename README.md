# CS 2341
## Assignment 4
* **@author: Yash Shah 49309058**
* **@author: Dani Jerez 49272827**
* **@duedate: December 6, 2024**

------

## Objective
Write a program that reads in a string from the command line and a dictionary of words (given below) and checks whether it is a "strong" password. 
Assume "strong" means that it: 
1. is at least 8 characters long,  
2. is not a word in the dictionary,  
3. is not a word in the dictionary followed by a digit 0-9 (e.g., hello5)

------

## Tasks
### Task 1
Insert the words into hash table using separate chaining and use below hash functions. Assume M=10v00 (fixed size).

First consider the following hashCode() implementation for String, which was used in early versions of Java:
```Java
public int hashCode() {
    int hash = 0;
    int skip = Math.max(1, length() / 8);
    
    for (int i = 0; i < length(); i += skip) {
        hash = (hash * 37) + charAt(i);
    }
    return hash;
}
```

Then, consider the current implementation:
```Java
public int hashCode() {
    int hash = 0;
    
    for (int i = 0; i < length(); i++) {
        hash = (hash * 31) + charAt(i);
    }
    return hash;
}
```

### Task 2
Insert the words into hash table using linear probing and report the results for above hash functions. Assume M=20000 (fixed size).

------

## Output

**account8:**
```bash
Password is not strong: It is a common dictionary word followed by a digit.
```


**accountablity**
```bash
Password is not strong: It is a common dictionary word.
```


**9a$D#qW7!uX&Lv3zT**
```bash
Password is strong.
Separate Chaining HashTable (hashCode1) comparisons: 12
Separate Chaining HashTable (hashCode2) comparisons: 10
Linear Probing HashTable (hashCode1) comparisons: 1
Linear Probing HashTable (hashCode2) comparisons: 8
```


**B@k45*W!c$Y7#zR9P**
```bash
Password is strong.
Separate Chaining HashTable (hashCode1) comparisons: 8
Separate Chaining HashTable (hashCode2) comparisons: 11
Linear Probing HashTable (hashCode1) comparisons: 0
Linear Probing HashTable (hashCode2) comparisons: 0
```


**X$8vQ!mW#3Dz&Yr4K5**
```bash
Password is strong.
Separate Chaining HashTable (hashCode1) comparisons: 16
Separate Chaining HashTable (hashCode2) comparisons: 7
Linear Probing HashTable (hashCode1) comparisons: 0
Linear Probing HashTable (hashCode2) comparisons: 0
```