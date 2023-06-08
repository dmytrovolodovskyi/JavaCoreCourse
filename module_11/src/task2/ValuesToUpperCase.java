package task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ValuesToUpperCase {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("James", "Arthur", "Daniel", "David", "John", "Jack", "Thomas");

        UpperCase namesToUpperCase = name -> name.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()).toString();

        System.out.println(namesToUpperCase.valuesToUpperCase(names));


    }
}
