import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for (int i : L) {
            sum += i;
        }
//        for (int i = 0; i < L.size(); i++) {
//            sum += L.get(i);
//        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> evenL = new ArrayList<>();
        for (int i = 0; i < L.size(); i++) {
            if (L.get(i) % 2 == 0){
                evenL.add(L.get(i));
            }
        }

        return evenL;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> commonL = new ArrayList<>();
        for (int i = 0; i < L1.size(); i++) {
            for (int j = 0; j < L2.size(); j++) {
                if (L1.get(i) == L2.get(j)) {
                    commonL.add(L1.get(i));
                }
            }
        }
        return commonL;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        for (String elem : words) {
//            if (elem.indexOf(c) != -1) {
//                num++;
//            }
            for (int i = 0; i < elem.length(); i++) {
                if (elem.charAt(i) == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
