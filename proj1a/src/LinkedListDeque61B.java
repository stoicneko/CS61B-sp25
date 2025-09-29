import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public static class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        private Node(T i, Node<T> n, Node<T> p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public Node<T> sentinel = new Node<>(null, null, null);
//    public Node<T> first;
//    public Node<T> last;
    public int size = 0;


    // TODO: Implement the constructor
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
        Node<T> p = sentinel.next;
        // first
        sentinel.next = new Node<>(x, p, sentinel);
        // second
        p.prev = sentinel.next;
        // last
//        sentinel.prev = p.prev; // 这样写 sentinel.prev/last 只有在一直使用addFirst时会正常更新
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
        Node<T> p = sentinel.prev;
        // last
        sentinel.prev = new Node<>(x, sentinel, p);
       // second to last

        p.next = sentinel.prev;

    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> p = sentinel.next;
        while (p.next != sentinel.next) {
            // 这里其实只是正向检测
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
        return null;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        return null;
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
        Node<T> p = sentinel.next; // 第0个item
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
        Node<T> p = sentinel.next; // 第0个item
        if (p = p.next)
    }
}
