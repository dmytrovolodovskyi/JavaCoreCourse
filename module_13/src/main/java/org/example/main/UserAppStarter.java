package org.example.main;

import static org.example.httpconnection.UserApp.*;

public class UserAppStarter {
    public static void main(String[] args) {
        System.out.println(createUser());
        System.out.println(updateUser(10));
        System.out.println(deleteUser(10));
        System.out.println(getAllUsers());
        System.out.println(findUserById(1));
        System.out.println(findUserByUsername("Leanne Graham"));
        System.out.println(getAllUsersComments(1));
        System.out.println(getOpenTaskForUser(1));

    }
}
