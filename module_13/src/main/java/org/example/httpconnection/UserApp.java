package org.example.httpconnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.entities.PostEntity;
import org.example.entities.TaskEntity;
import org.example.entities.UserEntity;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;


public class UserApp {

    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String URL = "https://jsonplaceholder.typicode.com";

    private static final Gson gson = new Gson();

    public static UserEntity createUser(){
        try {
            URL url = new URL(USERS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(Files.readAllBytes(
                        new File("module_13/src/main/java/org/example/files/user.json").toPath()));
                output.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);

            if (HttpURLConnection.HTTP_CREATED == responseCode) {
                return getUserEntity(connection);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static UserEntity updateUser(int id){
        try {
            URL url = new URL(USERS_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(Files.readAllBytes(
                        new File("module_13/src/main/java/org/example/files/user.json").toPath()));
                output.flush();
            }
            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode){
                return getUserEntity(connection);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static boolean deleteUser(int id){
        try {
            URL url = new URL(USERS_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            connection.disconnect();

            return responseCode >= HttpURLConnection.HTTP_OK &&
                    responseCode < HttpURLConnection.HTTP_MULT_CHOICE;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<UserEntity> getAllUsers(){
        try {
            URL url = new URL(USERS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null ) {
                    response.append(line);
                }
                Type userList = new TypeToken<List<UserEntity>>(){
                }.getType();

                return gson.fromJson(response.toString(), userList);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static Optional<UserEntity> findUserById(int id){
        try {
            URL url = new URL(USERS_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                UserEntity user = gson.fromJson(response.toString(), UserEntity.class);
                return Optional.ofNullable(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public static Optional<UserEntity> findUserByUsername(String username){
        try {
            URL url = new URL(USERS_URL + "?username" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                Type userList = new TypeToken<List<UserEntity>>(){
                }.getType();
                List<UserEntity> users = gson.fromJson(response.toString(), userList);
                if (!users.isEmpty()){
                    return Optional.of(users.get(0));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public static List<PostEntity> getAllUsersComments(int postsId){
        try {
            URL url = new URL(URL + "/posts/" + postsId + "/comments");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));

                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }

                boolean isWrittenToFile = writeCommentsToFile(response);
                System.out.println("Written to file: " + isWrittenToFile);

                Type commentsList = new TypeToken<List<TaskEntity>>(){
                }.getType();
                return gson.fromJson(response.toString(), commentsList);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static List<TaskEntity> getOpenTaskForUser(int id){
        try {
            URL url = new URL(URL + "/users/" + id + "/todos?completed=false");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));

                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    response.append(line);
                }
                Type taskList = new TypeToken<List<TaskEntity>>(){
                }.getType();
                return gson.fromJson(response.toString(), taskList);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static boolean writeCommentsToFile(StringBuilder response) {
        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter("module_13/src/main/java/org/example/files/user-X-post-Y-comments.json"))) {
            writer.write(response.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private static UserEntity getUserEntity(HttpURLConnection connection){
        try {
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            return gson.fromJson(response.toString(), UserEntity.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
