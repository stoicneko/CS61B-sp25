package lec9_inheritance2;

public class RandomPickerDemo {
    public static void main(String[] args) {
        String[] x = {"hi", "little", "cat"};
        // does not work: int[] x = {1, 2, 3, 4};
        System.out.println(RandomPicker.pickRandom(x));
    }
}
