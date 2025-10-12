package deque;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    int size;
    T[] items;
    int nextFirst;
    int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextLast = 0;
        nextFirst = -1;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        nextFirst = -1;
        nextLast = size;
        items = a;
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
        if (isEmpty()) {
            return null;
        }
        // 记住 get获取的永远是conceptual的值 永远是表数组的值
        T itemToReturn = get(0);
        size -= 1;
        nextFirst += 1;
        // 对于长度为 16 或更长的数组，你的使用率应该始终至少为 25%
        // 对于长度为 15 或更短的数组，你的使用率可以任意低。
        if (items.length >= 16 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return itemToReturn;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T itemToReturn = get(size);
        size -= 1;
        nextLast -= 1;
        // resize 会重置指针(nextLast)
        // nextLast 不要放在下面这个if语句里面
        // 一个好的设计应该将核心逻辑（移动指针）和辅助逻辑（检查是否需要缩容）分开。
        // 代码的意图应该是“移除一个元素，然后检查是否需要缩容”
        // 而不是“检查是否需要缩容，如果不需要，就移除一个元素”。
        if (items.length >= 16 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return itemToReturn;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int start = nextFirst + 1; // conceptual first
        return items[Math.floorMod(start + index, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        // 认真阅读要求!!!!!!!!
        // READ THE REQUIREMENTS CAREFULLY!!!
        // You weren't asked to complete this method.
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @VisibleForTesting
    public int getArrayLen() {
        return items.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
            /* 就一个符号!!!! 加号写成减号导致出现一连串的错误, 然后又排查错误 */
            wizPos = 0;
        }

        public boolean hasNext() {
            if (wizPos < size) {
                return true;
            }
            return false;
        }

        public T next() {
            /* 就是这里, 还是要从0, 表面上的第一个开始遍历
            /* get(wizPos)是第一个吗, 自己的get()没有实现负数
            /* 所以原本令wizPos = nextFirst + 1 如果就不对
            /* 那应该实现负数吗? 不应该, 因为get()获取的永远是conceptual order
            /* 表面上数组的顺序, 所以我们这里需要修改的是wizPos */
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // 指向地址相同
            return true;
        }
        if (other instanceof ArrayDeque61B otherDeque) {
            if (this.size() != otherDeque.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                // if (this.get(i) != otherDeque.get(i)) {
                if (!this.get(i).equals(otherDeque.get(i))) {
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

