package lec9_inheritance2;

import java.util.Random;

public class RandomPicker {
    public static <T> T pickRandom(T[] x) {
        Random random = new Random();
        int randomIndex = random.nextInt(x.length);
        return x[randomIndex];
    }
}
