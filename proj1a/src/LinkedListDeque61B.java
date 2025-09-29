import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

//    public static class Node {
    // 要使用Deque的T 声明 item 这里不能有static, static 属于切断链接
    public class Node {
        T item;
        Node prev;
        Node next;

        private Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    Node sentinel = new Node(null, null, null);
    //    public Node first;
    //    public Node last;
    int size = 0;


    public LinkedListDeque61B() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        size += 1;
        // Node p = sentinel.next;
        // first
        sentinel.next = new Node(x, sentinel.next, sentinel);
        // second
        sentinel.next.next.prev = sentinel.next;
        // p.prev = sentinel.next;
        // last
        // sentinel.prev = p.prev; // 这样写 sentinel.prev/last 只有在一直使用addFirst时会正常更新
        // 要找一个固定值, 固定的最后一个
        // 但不是1 = 1

    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        size += 1;
        // last
        sentinel.prev = new Node(x, sentinel, sentinel.prev);
        // second to last
        sentinel.prev.prev.next = sentinel.prev;

    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
//        while (p.next != sentinel.next) {
            // 这里其实只是正向检测
        while (p != sentinel) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size -= 1;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return sentinel.next.item;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        size -= 1;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return sentinel.prev.item;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index >= size() || size() == 0 || index < 0) {
            return null;
        }
        Node p = sentinel.next; // 第0个item
        int i = 0;
        while (i < index) {
            i++;
            p = p.next;
        }
        return p.item;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (size() == 0 || index >= size() || index < 0) {
            return null;
        }
        index -= 1;
        Node p = sentinel.prev; // 第0个item
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index);
    }
}
