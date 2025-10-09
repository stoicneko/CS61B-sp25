package lec7_lists4;

public class AList<Glorp> {
    private Glorp[] items;
    private int size;

    public AList() {
        size = 0;
        items = (Glorp[]) new Object[100];
    }
    // 0, 0, 0, 0, 0, 0, 0, ...
    // 3, 0, 0, 0, 0, 0, 0,
    // 3, 7, 0, 0, 0, 0, 0,
    //       ^ size = 2
    //
    // Observation: Next new item will go in position size
    //              The last item is in position size - 1
    //              Size is the number of items.

    // resize the underlying array to the desired capacity
    private void resize(int capacity) {
        Glorp[] a = (Glorp[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addLast(Glorp x) {
        if (size == items.length) {
            resize(size + 1);
        }
        items[size] = x;
        size += 1;
    }

    public Glorp getLast() {
        return items[size - 1];
    }

    public Glorp get(int i) {
        if (i >= size) {
            throw new IllegalArgumentException("We don't have that much stuff");
        }
        return items[i];
    }

    public Glorp removeLast() {
        Glorp itemToReturn = getLast();
        size -= 1;
        return itemToReturn;
    }
}
