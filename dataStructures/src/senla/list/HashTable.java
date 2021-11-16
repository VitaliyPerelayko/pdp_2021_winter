package senla.list;

import java.util.Arrays;

public class HashTable<K, V> {

    private int capacity;
    private int size;
    private Entry<? extends K, ? extends V>[] table;


    public HashTable() {
        capacity = 31;
        table = new Entry[capacity];
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = findIndex(key);
        return (index  != -1) ? table[index].value : null;
    }

    public void put(K key, V value) {
        if (size == capacity) grow();
        int index = getIndex(key);
        table[index] = new Entry<>(key, value);
        size++;
    }

    public void remove(K key) {
        int index = findIndex(key);
        if (index != -1){
            table[index] = null;
            size--;
        }
    }

    public void printTable(){
        for (int i = 0; i< capacity; i++){
            System.out.println("INDEX: " + i + " ---- " + table[i]);
        }
    }

    private int findIndex(K key){
        int hash1 = hash1(key);
        int hash2 = hash2(key);
        int counter = 0;
        while (table[hash1] != null && !table[hash1].key.equals(key) && counter < capacity) {
            hash1 += hash2;
            hash1 %= capacity;
            counter++;
        }
        System.out.println("INDEX " + hash1);
        return  (table[hash1] != null && counter < capacity) ? hash1 : -1;
    }

    private int getIndex(K key){
        int hash1 = hash1(key);
        int hash2 = hash2(key);
        int counter = 0;
        while (table[hash1] != null && counter < capacity) {
            hash1 += hash2;
            hash1 %= capacity;
            counter++;
        }
        if (table[hash1] == null) return hash1;
        grow();
        return getIndex(key);
    }

    private int hash1(K key) {
        int myHash = key.hashCode();
        myHash %= capacity;
        while (myHash <= 0) {
            myHash += capacity;
        }
        return myHash;
    }

    private int hash2(K key) {
        int myHash = key.hashCode()*11;
        myHash %= capacity;
        while (myHash <= 0) {
            myHash += capacity;
        }
        return myHash;
    }

    private void grow() {
        capacity = getNextPrime(capacity);
        System.out.println("New Capacity: " + capacity);
        Entry<? extends K, ? extends V>[] newTable = new Entry[capacity];
        Entry<? extends K, ? extends V>[] data = toArray();
        System.out.println(Arrays.toString(data));
        table = newTable;
        for (Entry<? extends K, ? extends V> e: data){
            put(e.key, e.value);
            size--;
        }
    }

    private Entry<? extends K, ? extends V>[] toArray(){
        Entry<? extends K, ? extends V>[] newTable = new Entry[size];
        int i = 0;
        for (Entry<? extends K, ? extends V> e : table){
            if (e != null) newTable[i++] = e;
        }
        return newTable;
    }

    private int getNextPrime(int prev) {
        int nextStep = prev + (size >> 1);
        for (int i = (nextStep % 2 == 0) ? nextStep + 1 : nextStep; i < Integer.MAX_VALUE; i += 2) {
            if (isPrime(i)) return i;
        }
        throw new OutOfMemoryError("To Large table");
    }

    private boolean isPrime(int num) {
        for (int i = 3; i < num / i; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
