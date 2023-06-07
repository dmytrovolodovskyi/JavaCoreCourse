package task5;

import java.util.Iterator;
import java.util.stream.Stream;

public class ArraysMixing {
    public static void main(String[] args) {

        Iterator<Integer> firstIterator = Stream.of(1, 2, 3, 4, 5).iterator();
        Iterator<Integer> secondIterator = Stream.of(6, 7, 8, 9, 10, 11).iterator();

        StringBuilder stringBuilder = new StringBuilder();

        while(firstIterator.hasNext() && secondIterator.hasNext())
            stringBuilder
                    .append(firstIterator.next())
                    .append(", ")
                    .append(secondIterator.next())
                    .append(", ");


        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length());

        System.out.println(stringBuilder);
    }
}
