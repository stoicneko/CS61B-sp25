import java.util.Iterator;
import java.util.Set;

/**
 * @author stoicneko
 * @version 1.0
 * @since 2025.10.26
 **/

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size = 0;
    private Node root = null;

    public class Node {
        K key;
        V value;
        Node left;
        Node right;

        // 构造函数需要构造left, right吗
        private Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    // n 要接回去
    private Node put(K key, V value, Node n) {
        if (n == null) {
            size += 1;
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        // 要插入的小于当前的, 去左边
        if (cmp < 0) {
            n.left = put(key, value, n.left);
        }
        else if (cmp > 0) {
            n.right = put(key, value, n.right);
        }
        else {
            n.value = value;
        }
        return n;
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    // 注意 " = " 在节点间作指针用
    private V get(K key, Node n){
        // 要插入的大于当前的
        if (n == null) {
            return null;
        }
        // 一定要放if 后面, 防止n为空指针
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return get(key, n.left);
        }
        else if (cmp > 0) {
            return get(key, n.right);
        }
        else {
            return n.value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node n){
        // 要插入的小于当前的
        if (n == null) {
            return false;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return containsKey(key, n.left);
        }
        else if (cmp > 0) {
            return containsKey(key, n.right);
        }
        else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 6. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
