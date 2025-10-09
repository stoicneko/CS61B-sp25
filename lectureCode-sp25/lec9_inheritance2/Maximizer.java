package lec9_inheritance2;

public class Maximizer {
    public static <T extends Comparable<? super T>> T max(T[] items) {
        T maxItem = items[0];
        for (int i = 0; i < items.length; i += 1) {
            int cmp = items[i].compareTo(maxItem);
            if (cmp > 0) {
                maxItem = items[i];
            }
        }
        return maxItem;
    }
}
