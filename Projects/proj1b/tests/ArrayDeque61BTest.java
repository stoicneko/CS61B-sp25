import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new ArrayDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

     }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(0);      // [0]
        lld1.addLast(1);      // [0, 1]
        lld1.addFirst(-1);    // [-1, 0, 1]
        lld1.addFirst(-1);    // [-1, -1, 0, 1]
        lld1.addLast(2);      // [-1, -1, 0, 1, 2]
        lld1.addFirst(-2);    // [-2, -1, -1, 0, 1, 2]
        lld1.addFirst(-1001); // [-1001, -2, -1, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-1001, -2, -1, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void getTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        // empty Deque
        assertThat(lld1.get(0)).isNull();
        assertThat(lld1.get(1)).isNull();
        assertThat(lld1.get(-1)).isNull();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.get(0)).isEqualTo("back");

        lld1.addFirst("middle");
        assertThat(lld1.get(0)).isEqualTo("middle");
        assertThat(lld1.get(1)).isEqualTo("back");

        lld1.addFirst("front");
        assertThat(lld1.get(0)).isEqualTo("front");
        assertThat(lld1.get(1)).isEqualTo("middle");
        assertThat(lld1.get(2)).isEqualTo("back");

        // i < -1
        assertThat(lld1.get(-1)).isNull();

        // get out of bounds
        assertThat(lld1.get(3)).isNull();
        assertThat(lld1.get(13134)).isNull();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        lld1.removeFirst();   // [-1, 0, 1, 2]
        assertThat(lld1.toList()).containsExactly(-1, 0, 1, 2).inOrder();
        lld1.removeFirst();   // [0, 1, 2]
        lld1.removeFirst();   // [1, 2]
        lld1.removeFirst();   // [2]
        assertThat(lld1.toList()).containsExactly(2).inOrder();
        lld1.removeFirst();   // []

        assertThat(lld1.removeFirst()).isNull();
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        lld1.removeLast(); // ["front", "middle"]
        assertThat(lld1.toList()).containsExactly("front", "middle").inOrder();

        lld1.removeLast(); // ["front"]
        assertThat(lld1.toList()).containsExactly("front").inOrder();

        lld1.removeLast(); // []
        assertThat(lld1.removeLast()).isNull();
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        lld1.removeLast();     // [-2, -1, 0, 1]
        lld1.removeFirst();    // [-1, 0, 1]
        lld1.removeLast();     // [-1, 0]
        lld1.removeFirst();    // [0]
        assertThat(lld1.toList()).containsExactly(0).inOrder();

        lld1.removeFirst();    // []
        assertThat(lld1.removeFirst()).isNull();    // []

    }

     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

}
