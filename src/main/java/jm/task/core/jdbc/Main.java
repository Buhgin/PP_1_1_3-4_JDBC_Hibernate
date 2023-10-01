package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

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
    }

}
