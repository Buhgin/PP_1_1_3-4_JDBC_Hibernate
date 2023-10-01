package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users" +
            " (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) " +
            "NOT NULL, lastName VARCHAR(45) NOT NULL," +
            "age TINYINT NOT NULL, PRIMARY KEY (id))";
    String DROP_TABLE = "DROP TABLE IF EXISTS users";
    String USER_SAVE = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    String USER_REMOVE = "DELETE FROM users WHERE id = ?";
    String USER_GET_ALL = "SELECT * FROM users";
    String USER_CLEAN = "TRUNCATE TABLE users";
    public UserDaoHibernateImpl() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            users = session.createSQLQuery(USER_GET_ALL).addEntity(User.class).list();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(USER_CLEAN).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
