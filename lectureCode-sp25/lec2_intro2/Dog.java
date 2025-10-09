package lec2_intro2;

public class Dog {
    public int size;
    public static String binomen = "canis familiaris";

    /* This is a constructor, it tells us how to make dogs. */
    public Dog(int s) {
        size = s;
    }

    public void makeNoise() {
        if (size > 20) {
            System.out.println("arooooo!");
        } else if (size > 10) {
            System.out.println("bark");
        } else {
            System.out.println("yipyipyipyip");
        }
    }

    public static Dog maxDog(Dog a, Dog b) {
        if (a.size > b.size) {
            return a;
        }
        return b;
    }

    /* Compare myself to another dog */
    public Dog maxDog(Dog otherDog) {
        System.out.println("We used the non-static one");
        if (this.size > otherDog.size) {
            return this;
        }
        return otherDog;
    }
} 