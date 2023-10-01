package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJDBCImpl implements UserDao {
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users" +
            " (id BIGINT NOT NULL AUTO_INCREMENT," +
            "name VARCHAR(45) NOT NULL, " +
            "lastName VARCHAR(45) NOT NULL," +
            "age TINYINT NOT NULL, PRIMARY KEY (id))";
    String DROP_TABLE = "DROP TABLE IF EXISTS users";
    String USER_SAVE = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    String USER_REMOVE = "DELETE FROM users WHERE id = ?";
    String USER_GET_ALL = "SELECT * FROM users";
    String USER_CLEAN = "TRUNCATE TABLE users";



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE);) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(DROP_TABLE);) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_SAVE);) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_REMOVE);) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(USER_GET_ALL);
            ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()){
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));


                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_CLEAN);) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
