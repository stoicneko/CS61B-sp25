import deque.Maximizer61B;
import deque.ArrayDeque61B;
import deque.LinkedListDeque61B;

import org.junit.jupiter.api.*;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class Maximizer61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class IntergerComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    @Test
    public void basicTest1() {
        LinkedListDeque61B<String> ad = new LinkedListDeque61B<>();
        ad.addFirst("cold");
        ad.addFirst("2");
        ad.addFirst("fury road");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("fury road");
    }

    @Test
    public void basicTest2() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("cold");
        ad.addFirst("2");
        ad.addFirst("fury road");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("fury road");
    }

    @Test
    public void basicTest3() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(5);
        ad.addFirst(12);
        ad.addFirst(17);
        ad.addFirst(23);
        assertThat(Maximizer61B.max(ad, new IntergerComparator())).isEqualTo(17);
    }
}
