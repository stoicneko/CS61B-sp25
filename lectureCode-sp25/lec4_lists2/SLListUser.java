package lec4_lists2;

public class SLListUser {
    public static void main(String[] args) {
        SLList L = new SLList();
        L.addFirst(20);
        L.addFirst(10);
        L.addLast(300);
        L.addLast(400);

        System.out.println(L.size());
    }
}
