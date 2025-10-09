package lec5_lists3;

public class SLListUser {
    public static void main(String[] args) {
        SLList<String> L = new SLList<>();
        L.addFirst("mustard");
        L.addFirst("mustard2");
        L.addLast("mustard");
        L.addLast("ketchup");
        L.addLast("dijon");

        System.out.println(L.size());
        int[] z = {9, 10, 11, 12, 13};
    }
}
