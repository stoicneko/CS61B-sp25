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
         Deque61B<String> ad1 = new ArrayDeque61B<>();

         ad1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(ad1.toList()).containsExactly("back").inOrder();

         ad1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(ad1.toList()).containsExactly("middle", "back").inOrder();

         ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();

     }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest1() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        ad1.addLast(0);     // [0]
        ad1.addLast(1);     // [0, 1]
        ad1.addFirst(-1);   // [-1, 0, 1]
        ad1.addFirst(-1);   // [-1, -1, 0, 1]
        ad1.addLast(2);     // [-1, -1, 0, 1, 2]
        ad1.addFirst(-2);   // [-2, -1, -1, 0, 1, 2]
        ad1.addFirst(-101); // [-101, -2, -1, -1, 0, 1, 2]
        ad1.addFirst(-102); // [-102, -101, -2, -1, -1, 0, 1, 2]
        ad1.addFirst(-103); // [-103, -102, -101, -2, -1, -1, 0, 1, 2]
        ad1.addLast(-104);  // [-103, -102, -101, -2, -1, -1, 0, 1, 2, -104]
        ad1.addLast(-105);  // [-103, -102, -101, -2, -1, -1, 0, 1, 2, -104, -105]
        ad1.addLast(-106);  // [-103, -102, -101, -2, -1, -1, 0, 1, 2, -104, -105, -106]

        assertThat(ad1.size()).isEqualTo(12);
        assertThat(ad1.toList()).containsExactly(-103, -102, -101, -2, -1, -1, 0, 1, 2, -104, -105, -106).inOrder();
    }

    @Test
    public void addFirstAndAddLastTest2() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addFirst("");
        ad1.addFirst("2");
        ad1.addFirst("fury road");

        assertThat(ad1.toList()).containsExactly("fury road", "2", "").inOrder();

    }
    @Test
    public void getTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        // empty Deque
        assertThat(ad1.get(0)).isNull();
        assertThat(ad1.get(1)).isNull();
        assertThat(ad1.get(-1)).isNull();

//        ad1.addFirst("back"); // after this call we expect: ["back"]
//        assertThat(ad1.get(0)).isEqualTo("back");
//
//        ad1.addFirst("middle");
//        assertThat(ad1.get(0)).isEqualTo("middle");
//        assertThat(ad1.get(1)).isEqualTo("back");
//
//        ad1.addFirst("front");
//        assertThat(ad1.get(0)).isEqualTo("front");
//        assertThat(ad1.get(1)).isEqualTo("middle");
//        assertThat(ad1.get(2)).isEqualTo("back");

        for (int i = 3; i < 100; i++) {
            ad1.addFirst("Test");
        }

        ad1.addLast("Test1");
//        assertThat(ad1.get(0)).isEqualTo("front");
//        assertThat(ad1.get(1)).isEqualTo("middle");
//        assertThat(ad1.get(2)).isEqualTo("back");

        assertThat(ad1.get(1)).isEqualTo("Test");

        // i < -1
        assertThat(ad1.get(-1)).isNull();

        // get out of bounds
        assertThat(ad1.get(103)).isNull();
        assertThat(ad1.get(13134)).isNull();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.removeFirst()).isNull();

        for (int i = 0; i < 100; i++) {
            ad1.addFirst(i);
            ad1.addFirst(100 - i);
        }
        assertThat(ad1.size()).isEqualTo(200);

        for (int i = 0; i < 100; i++) {
            ad1.removeFirst();
            ad1.removeLast();
        }

        assertThat(ad1.removeFirst()).isNull();
        assertThat(ad1.toList()).containsExactly().inOrder();
        assertThat(ad1.size()).isEqualTo(0);

    }

    @Test
    public void removeLastTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

    }


    @Test
    public void resizeUpAndDownTest() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<>();
        // Assuming initial capacity is 8
        // 1. Resize Up
        for (int i = 0; i < 9; i++) { // Fill to 9 to trigger resize
            ad1.addLast(i);
        }
        int capacityAfterUp = ad1.getArrayLen();
        assertWithMessage("Array should resize up").that(capacityAfterUp).isAtLeast(16);

        // 2. Resize Down
        // Remove until usage factor is low. 4 items in a 16-length array.
        for (int i = 0; i < 5; i++) {
            ad1.removeLast();
        }
        assertThat(ad1.size()).isEqualTo(4);

        // Next remove should trigger resize down
        ad1.removeLast();
        int capacityAfterDown = ad1.getArrayLen();

        assertWithMessage("Array should resize down after resizing up").that(capacityAfterDown).isLessThan(capacityAfterUp);
        assertThat(ad1.size()).isEqualTo(3);
        assertThat(ad1.toList()).containsExactly(0, 1, 2).inOrder();
}




 @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
 void noNonTrivialFields() {
     List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
             .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
             .toList();

     assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
 }

}
