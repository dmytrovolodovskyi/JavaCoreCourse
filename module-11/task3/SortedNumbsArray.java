package task3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedNumbsArray {
    public static void main(String[] args) {
        List<String> numbsArray = Arrays.asList("1, 2, 0", "4, 5");

        SortedArray sortedArray = array -> array.stream()
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(String::trim)
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println(sortedArray.sortArray(numbsArray));
    }
}