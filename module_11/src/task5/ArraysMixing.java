package task5;

import java.util.Iterator;
import java.util.stream.Stream;

public class ArraysMixing {
    public static void main(String[] args) {

        Stream<Integer> firstStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> secondStream = Stream.of(6, 7, 8, 9, 10, 11);

        Stream<Integer> zippedStream = zip(firstStream, secondStream);
        zippedStream.forEach(System.out::print);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){

        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while(firstIterator.hasNext() && secondIterator.hasNext()){
            builder.accept(firstIterator.next());
            builder.accept(secondIterator.next());
        }

        return builder.build();
    }

}
