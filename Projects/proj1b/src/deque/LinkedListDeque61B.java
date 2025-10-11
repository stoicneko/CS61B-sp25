package deque;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public void addLast(T x) {
        size += 1;
        // last
        sentinel.prev = new Node(x, sentinel, sentinel.prev);
        // second to last
        sentinel.prev.prev.next = sentinel.prev;

    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

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

    @Override
    public T get(int index) {
        if (index >= size() || index < 0) { // 已经包含size == 0的情况
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

    public T getRecursive(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node p, int idx) { // index/idx
        if (idx == 0) {
            return p.item;
        }
        // return getRecursiveHelper(p.next, idx--); // 这里得是 idx - 1 不改变当前层的值
        return getRecursiveHelper(p.next, idx - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizPos;

        public LinkedListDequeIterator() {
            wizPos = sentinel.next;
        }

        public boolean hasNext() {
            if (wizPos.next != sentinel) {
                return true;
            }
            return false;
        }

        public T next() {
            T returnItem = wizPos.item;
            wizPos = wizPos.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // 指向地址相同
            return true;
        }
        if (other instanceof LinkedListDeque61B otherDeque) {
            if (this.size() != otherDeque.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) != otherDeque.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}

