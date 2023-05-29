package org.example.task1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String filePath = "module10/src/main/java/org/example/task1/file.txt";
        getValidNumbers(filePath);
    }
    public static void getValidNumbers(String filePath) {

        try (InputStream fis = new FileInputStream(filePath);
             Scanner scanner = new Scanner(fis)) {

            Pattern pattern = Pattern.compile("(\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})");

            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                Matcher matcher = pattern.matcher(nextLine);

                if (matcher.matches()) System.out.println(nextLine);

            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}


