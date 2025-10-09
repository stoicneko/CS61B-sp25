package lec9_inheritance2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDogDemo {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Grigometh", 200));
        dogs.add(new Dog("Pelusa", 5));
        dogs.add(new Dog("Clifford", 9000));

        Dog maxDog = Collections.max(dogs);
        Dog maxNameDog = Collections.max(dogs, new Dog.NameComparator());
        //Comparator<Dog> dc = (d1, d2) -> d1.name.compareTo(d2.name);

    }
}