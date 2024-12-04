import java.util.LinkedList;

class HashTableSeparateChaining {
    private final LinkedList<String>[] table;
    private final int M;

    @SuppressWarnings("unchecked")
    public HashTableSeparateChaining(int size) {
        M = size;
        table = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public int hashCode1(String key) {
        int hash = 0;
        int skip = Math.max(1, key.length() / 8);
        for (int i = 0; i < key.length(); i += skip) {
            hash = (hash * 37) + key.charAt(i);
        }
        return Math.abs(hash % M);
    }

    public int hashCode2(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31) + key.charAt(i);
        }
        return Math.abs(hash % M);
    }

    public void insert(String key, int lineNumber, boolean useHashCode1) {
        int hash = useHashCode1 ? hashCode1(key) : hashCode2(key);
        table[hash].add(key);
    }

    public boolean contains(String key, boolean useHashCode1) {
        int hash = useHashCode1 ? hashCode1(key) : hashCode2(key);
        return table[hash].contains(key);
    }
}
