package map;

import map.exception.ElementNotFoundException;
import map.exception.NoUsableKeyException;
import java.util.Arrays;

public class HashMap {
    public static final int INITIAL_CAPACITY = 16;
    public static final double LOAD_FACTOR = 0.75;
    private int[] keys;
    private long[] values;
    private int size;
    private double threshold;

    public HashMap() {
        this.keys = new int[INITIAL_CAPACITY];
        this.values = new long[INITIAL_CAPACITY];
        this.size = 0;
        threshold = INITIAL_CAPACITY * LOAD_FACTOR;
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            keys[i] = -1;
        }
    }

    public HashMap(int capacity) {
        this.keys = new int[capacity];
        this.values = new long[capacity];
        this.size = 0;
        threshold = capacity * LOAD_FACTOR;
        Arrays.fill(keys, -1);
    }

    public void put(int key, long value) {
        if (key == -1) {
            throw new NoUsableKeyException("You can't use key -1");
        }
        if (size >= threshold) {
            resize();
        }
        int index = hash(key);
        while (keys[index] != -1 && keys[index] != key) {
            index = (index + 1) % keys.length;
        }
        if (keys[index] == -1) {
            size++;
        }
        keys[index] = key;
        values[index] = value;
    }

    public long get(int key) {
        if (key == -1) {
            throw new NoUsableKeyException("You can't use key -1");
        }
        int index = hash(key);
        int count = 0;
        while (keys[index] != -1 && count < keys.length) {
            if (keys[index] == key) {
                return values[index];
            }
            index = (index + 1) % keys.length;
            count++;
        }
        throw new ElementNotFoundException("Cant find element with key " + key);
    }

    public int size() {
        return size;
    }

    private int hash(int key) {
        return Math.abs(key % keys.length);
    }

    private void resize() {
        int newCapacity = keys.length * 2;
        threshold = newCapacity * LOAD_FACTOR;
        int[] newKeys = new int[newCapacity];
        long[] newValues = new long[newCapacity];
        Arrays.fill(newKeys, -1);
        int[] oldKeys = keys;
        long[] oldValues = values;
        keys = newKeys;
        values = newValues;
        size = 0;
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != -1) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}
