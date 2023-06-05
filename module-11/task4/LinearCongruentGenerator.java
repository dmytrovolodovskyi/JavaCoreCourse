package task4;

import java.util.stream.Stream;

public class LinearCongruentGenerator {
    public static void main(String[] args) {

        long seed1 = 1L;
        long a1 = 25214903917L;
        int b1 = 11;
        long m1 = 281474976710656L;

        NumbersGenerator ng = (seed, a, b, m) -> Stream
                .iterate(seed, x -> (a * x + m) % m);

        ng.randomNumb(seed1, a1, b1, m1);
    }
}
