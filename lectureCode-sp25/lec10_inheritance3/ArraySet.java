package lec10_inheritance3;

import java.util.Iterator;

// Saying implements Iterable<T>
// means that you have a method that returns an Iterator<T> and this method
//     is called .iterator

// and in turn, that Iterator has a hasNext and next method.

public class ArraySet<T> implements Iterable<T>  {
    private T[] items;
    private int size; // the next item to be added will be at position size

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* EXTRA VIDEO CODE
    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return "{" + String.join(", ", listOfItems) + "}";
    } */

    /* EXTRA VIDEO CODE
    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<Glerp>();
        for (Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    } */

    // an ArraySetIterator is the seer, it is the wizard
    private class ArraySetIterator implements Iterator<T> {
        // the position of the wizard
        private int wizPos;

        ArraySetIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            if (wizPos < size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            // returns the item
            T itemToReturn = items[wizPos];
            // advances
            wizPos += 1;

            return itemToReturn;
        }
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringToReturn = new StringBuilder("{");
        for (T x : this) {
            stringToReturn.append(x);
            stringToReturn.append(", ");
        }
        stringToReturn.append("}");
        return stringToReturn.toString();
    }

    @Override
    public boolean equals(Object o) {
        // check if o is an ArraySet!
        if (o instanceof ArraySet uddaSet) {

            // if o is actually an ArraySet, now I have uddaset
            // which is of type ArraySet
            if (this.size != uddaSet.size()) {
                return false;
            }
            for (T x : this) {
                if (!uddaSet.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        /* won't work yet: */
        for (int i : aset) {
            System.out.println(i);
        }

        Iterator<Integer> aseer = aset.iterator();
        while (aseer.hasNext()) {
            int i = aseer.next();
            System.out.println(i);
        }

        System.out.println(aset);

        //EXTRA VIDEO CODE
        //ArraySet<String> asetOfStrings = ArraySet.of("hi", "I'm", "here");
        //System.out.println(asetOfStrings);
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
