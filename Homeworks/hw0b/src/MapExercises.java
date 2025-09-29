import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    static final int ALPHABET_SIZE = 26; // 代表字母表的大小
    static final int DEFAULT_CAPACITY = 96; // 代表默认容量
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 1; i <= ALPHABET_SIZE; i++) {
            int ascii = i + DEFAULT_CAPACITY;
            map.put((char) ascii, i);
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            map.put(nums.get(i), nums.get(i) * nums.get(i));
        }
        return map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            int count = 0;
            for (String elem : words) {
                if (words.get(i) == elem) {
                    count++;
                }
            }
            map.put(words.get(i), count);
        }
        return map;
    }
}
