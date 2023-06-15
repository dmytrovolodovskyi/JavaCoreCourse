package task2;


import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ValuesReplace {
    private static volatile int n;
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public  static void fizz(){
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) queue.add("fizz");
        }
    }

    public  static void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0) queue.add("buzz");
        }
    }

    public  static void fizzbuzz(){
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                queue.add("fizzbuzz");
            }
        }
    }

    public static void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 || i % 5 != 0) queue.add(String.valueOf(i));
        }

        for (String q : queue) {
            System.out.println(q);
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value n: ");
        n = scanner.nextInt();

        Thread threadA = new Thread(ValuesReplace::fizz);

        Thread threadB = new Thread(ValuesReplace::buzz);

        Thread threadC = new Thread(ValuesReplace::fizzbuzz);

        Thread threadD = new Thread(ValuesReplace::number);


        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();


    }
}
