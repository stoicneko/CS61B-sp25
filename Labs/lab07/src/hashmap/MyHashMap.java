package hashmap;

import java.util.*;

/**
 * @author stoicneko
 * @version 1.0
 * @since 2025.10.26
 *  A hash table-backed Map implementation.
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 **/

public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size = 0;
    private double loadFactor;
    private int initialCapacity;
    private int threshold;
    private int index;
    private int capacity;

    // 为什么需要三个构造函数?
    // 三个状态, 三种不同输入

    /** Constructors */
    // 最固定的, 默认的
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity,0.75);

    }

    // 最自由的
    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity <= 0 || loadFactor <= 0) {
            throw new IllegalArgumentException();
        }
        // 检测错误都放在最前面
        // 验证逻辑优先；
        // 对象状态只在合法情况下改变。
        this.loadFactor = loadFactor;
        this.initialCapacity = initialCapacity;

        // threshold(临界点) 可能为零
        threshold = Math.max(1, (int) (loadFactor * initialCapacity));

        buckets = new Collection[initialCapacity];

    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    // Your code won't compile until you do so!

    @Override
    public void put(K key, V value) {
        // 模哪个值? 模buckets的length
        index = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[index] == null) {
            buckets[index] = createBucket();
        }
        for (Node node : buckets[index]) {
            // 表中存在相同键, 仅更新值, size不变
            // 注意使用equals
            if (Objects.equals(key, node.key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node(key, value));
        size += 1;
        // 放最后判断, 和放最开始判断有什么区别?
        // 避免不必要扩容
        // 同时这里使用 > 而不是>= 也是同样的原因
        if (size > threshold) {
            resize();
        }
    }

    private void resize() {
        // 新的桶也需要判断null, 重新分配
        int newCapacity = 2 * buckets.length;
        Collection<Node>[] newBuckets = new Collection[newCapacity];
        for (int i = 0; i < buckets.length; i++) {
            // 不遍历空桶
            if (buckets[i] == null) {
                continue;
            }
            for (Node node : buckets[i]) {
                int idx = Math.floorMod(node.key.hashCode(), newCapacity);
                if (newBuckets[idx] == null) {
                    newBuckets[idx] = createBucket();
                }
                newBuckets[idx].add(node);
            }
        }
        // 最后让buckets指向新桶, 更新指针
        buckets = newBuckets;
        threshold = (int) (newCapacity * loadFactor);
    }


    @Override
    public V get(K key) {
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[idx] == null) {
            return null;
        }
        for (Node node : buckets[idx]) {
            // 笨比了, 一定要用equals
            if (Objects.equals(node.key, key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null)
            throw new IllegalArgumentException("null key not supported");
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[idx] == null) {
            return false;
        }
        for (Node node : buckets[idx]) {
            if (Objects.equals(node.key, key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = new Collection[initialCapacity];
        // 记得更新临界点, 这个总容易忘
        threshold = (int) (initialCapacity * loadFactor);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
