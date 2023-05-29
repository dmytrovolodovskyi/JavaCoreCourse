package org.example.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//
public class Main {
    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();

        try(FileReader fileReader = new FileReader("module10/src/main/java/org/example/task2/file.txt");
            Scanner scanner = new Scanner(fileReader)) {

            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();

                String[] nextLineWords = nextLine.split(" ");
                String name = nextLineWords[0];
                int age = Integer.parseInt(nextLineWords[1]);

                User user = new User(name, age);
                userList.add(user);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(user);
                System.out.println(json);
            }

        } catch(IOException e){
            System.out.println("Error: " + e);
        }
    }
}

class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
