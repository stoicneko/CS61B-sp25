package lec4_lists2;

/* List of numbers */
public class SLList {
    private static class IntNode {
        public int item;    // first number in the list
        public IntNode next; // rest of the list

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    // the sentinel node is the dummy node at th front of the list
    // and the real first item is sentinel.next (if it exists)
    private IntNode sentinel;
    private int size;

    public SLList() {
        size = 0;
        sentinel = new IntNode(12873, null);
    }

    public void addFirst(int x) {
        size += 1;
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;

        // advance p until it is the last item
        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    public int size() {
        return size;
    }
}
