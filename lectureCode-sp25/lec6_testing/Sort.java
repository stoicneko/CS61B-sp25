package lec6_testing;

public class Sort {
    public static void sort(String[] x) {
        sort(x, 0);
    }

    /** if we want recursion but we're not recursive yet
     *  we can make a helper method, similar to get
     *  in SLList
     *
     *  This method sorts x starting from index s
     */
    private static void sort(String[] x, int s) {
        if (s == x.length) {
            return;
        }
        // YOU SHOULD ONLY LOOK AT THE GREEN ITEMS
        int smallest = findSmallest(x, s);
        swap(x, s, smallest);
        sort(x, s + 1);
    }

    // cat, bob, luka, c++
    // bob, cat, luka, c++
    // cat, bob, luka, c++

    public static int findSmallest(String[] x, int s) {
        int smallestIndex = s;
        for (int i = s; i < x.length; i += 1) {
            int cmp = x[i].compareTo(x[smallestIndex]);
            if (cmp < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
