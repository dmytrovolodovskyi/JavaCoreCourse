package task4;

import java.util.stream.Stream;
@FunctionalInterface
public interface NumbersGenerator {
    Stream<Long> randomNumb(long seed, long a, long b, long m);
}
