package lec6_testing;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class TestSort {
    @Test
    public void testSort() {
        String[] input = {"cat", "bob", "luka", "c++ is ..."};
        String[] expected = {"bob", "c++ is ...", "cat", "luka"};
        Sort.sort(input);

        assertThat(input).isEqualTo(expected);
    }

    @Test
    public void testFindSmallest() {
        String[] input = {"cat", "bob", "luka", "c++ is ..."};
        int expected = 1;

        int actual = Sort.findSmallest(input, 0);
        assertThat(actual).isEqualTo(expected);

        int expected2 = 3;
        int actual2 = Sort.findSmallest(input, 2);
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void testSwap() {
        String[] input = {"cat", "bob", "luka", "c++ is ..."};
        String[] expected = {"bob", "cat", "luka", "c++ is ..."};

        Sort.swap(input, 0, 1);
        assertThat(input).isEqualTo(expected);

    }
}
