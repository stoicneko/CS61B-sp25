package lec2_intro2;

public class DogLauncher {
    public static void main(String[] args) {
        Dog maya = new Dog(9);
        //maya.makeNoise();

        Dog hugeGreg = new Dog(1500);
        Dog largerDog = maya.maxDog(hugeGreg);

        largerDog.makeNoise();
    }
} 