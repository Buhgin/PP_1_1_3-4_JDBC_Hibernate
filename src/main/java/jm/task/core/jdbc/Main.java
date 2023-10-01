package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = Util.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database successfully!");

            }
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database!");
            ex.printStackTrace();
        }
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 5);
        userService.saveUser("Petr", "Petrov", (byte) 10);
        userService.saveUser("Sidorov", "Sidorov", (byte) 15);
        userService.saveUser("Vasiliy", "Vasiliev", (byte) 20);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }



}
