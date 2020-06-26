package map;

import map.exception.ElementNotFoundException;

public class HashMap {
    public static final int INITIAL_CAPACITY = 16;
    public static final double LOAD_FACTOR = 0.75;
    private Node[] nodes;
    private int size;
    private double threshold;

    public HashMap() {
        this.nodes = new Node[INITIAL_CAPACITY];
        this.size = 0;
        threshold = INITIAL_CAPACITY * LOAD_FACTOR;
        for (int i = 0; i < INITIAL_CAPACITY; i ++) {
            nodes[i] = null;
        }
    }

    public HashMap(int capacity) {
        this.nodes = new Node[capacity];
        this.size = 0;
        threshold = capacity * LOAD_FACTOR;
        for (int i = 0; i < capacity; i ++) {
            nodes[i] = null;
        }
    }

    public void put(int key, long value) {
        if(size >= threshold) {
            resize();
        }
        int index = hash(key);
        while (nodes[index] != null && nodes[index].key != key) {
            index = (index + 1) % nodes.length;
        }
        if (nodes[index] == null) {
            size++;
        }
        nodes[index] = new Node(key, value);
    }

    public long get(int key) {
        int index = hash(key);
        int count = 0;
        while (nodes[index] != null && count < nodes.length) {
            if (nodes[index].key == key) {
                return nodes[index].value;
            }
            index = (index + 1) % nodes.length;
            count++;
        }
        throw new ElementNotFoundException("Cant find element with key " + key);
    }

    public int size() {
        return size;
    }

    private int hash(int kay) {
        return Math.abs(kay % nodes.length);
    }

    private void resize() {
        int newCapacity = nodes.length * 2;
        threshold = newCapacity * LOAD_FACTOR;
        Node[] newNodes = new Node[newCapacity];
        Node[] oldNodes = nodes;
        nodes = newNodes;
        size = 0;
        for (Node oldNode : oldNodes) {
            if (oldNode != null) {
                put(oldNode.key, oldNode.value);
            }
        }
    }

    private static class Node {
        private int key;
        private long value;

        public Node(int key, long value) {
            this.key = key;
            this.value = value;
        }
    }
}
