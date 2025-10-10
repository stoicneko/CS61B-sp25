package deque;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    int size;
    T[] items;
    int nextFirst;
    int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextLast = 1;
        nextFirst = 0;
    }

    private void resize(int capacity) {

    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[Math.floorMod(nextFirst, items.length)] = x;
        nextFirst -= 1;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[Math.floorMod(nextLast, items.length)] = x;
        nextLast += 1;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
//        for (int i = nextFirst + 1; i < nextLast; i++) {
//            returnList.add(items[Math.floorMod(i, items.length)]);
//        }
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
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
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextFirst += 1;
        return get(nextFirst);
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextLast -= 1;
        return get(nextLast);
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int start = nextFirst + 1;
        return items[Math.floorMod(start + index, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        // 认真阅读要求!!!!!!!!
        // READ THE REQUIREMENTS CAREFULLY!!!
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
