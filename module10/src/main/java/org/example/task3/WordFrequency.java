package org.example.task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordFrequency {
    public static void main(String[] args) {

        String filePath = "module10/src/main/java/org/example/task3/words.txt";
        wordFrequency(filePath);
    }
    public static void wordFrequency(String filePath) {

        Map<String, Integer> wordsCount = new HashMap<>();

        try(FileInputStream fis = new FileInputStream(filePath);
            Scanner scanner = new Scanner(fis)) {

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] words = nextLine.split("\\s+");

                for (String word : words) {
                    if (wordsCount.containsKey(word)) {
                        int count = wordsCount.get(word);
                        wordsCount.put(word, count + 1);
                    } else wordsCount.put(word, 1);
                }
            }

            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
