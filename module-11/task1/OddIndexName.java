package task1;


import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class OddIndexName {
    public static void main(String[] args) {
        String[] names = {"Arthur", "John", "Daniel", "David", "James", "Jack", "Thomas"};

        OddValues name = names1 -> IntStream.range(0, names1.length)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> (i + 1) + ". " + names1[i])
                .collect(Collectors.joining(", "));


        System.out.println(name.oddValue(names));
    }
}

