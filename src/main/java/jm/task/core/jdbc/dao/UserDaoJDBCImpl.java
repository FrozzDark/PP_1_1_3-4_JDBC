package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS mydbtest.USERS"
                + "(id INTEGER AUTO_INCREMENT,"
                + "name VARCHAR(30),"
                + "lastName VARCHAR(30),"
                + "age TINYINT(3),"
                + "PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        String sql = "DROP TABLE IF EXISTS USERS";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        String sql = "INSERT INTO USERS(name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        String sql = "DELETE FROM USERS WHERE ID=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        String sql = "TRUNCATE TABLE USERS";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
