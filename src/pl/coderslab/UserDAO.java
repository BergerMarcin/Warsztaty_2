package pl.coderslab;

/**
 * Class UserDAO (DAO - Data Access Object) is implementing CRUD functionality for database as:
 *  - adding new user
 *  - reading user data of given user id
 *  - reading user data of given user email (data for log in)
 *  - changing user all data/attributes
 *  - removing user
 *  - reading all users data
 * Class UserDAO cooperating with Class User (containing data from table users of database)
 */

import java.sql.*;
import java.util.Arrays;

public class UserDAO {

    // CRUD QUERIES FOR FOLLOWING METHODS (acc. input data):
    //  - create(int userId):
    private static final String CREATE_USER_OF_ID_QUERY =
            "INSERT INTO users(use  rname, email, password, user_group_id) VALUES (?, ?, ?, ?)";
    //  - read(int userId):
    private static final String READ_USER_OF_ID_QUERY =
            "SELECT * FROM users where id = ?";
    //  - read(String userId):
    private static final String READ_USER_OF_EMAIL_QUERY =
            "SELECT * FROM users where email = ?";
    //  - update(int userId):
    private static final String UPDATE_USER_OF_ID_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? , user_group_id = ? where id = ?";
    //  - delete(int userId):
    private static final String DELETE_USER_OF_ID_QUERY =
            "DELETE FROM users WHERE id = ?";
    //  - findAll(none):
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";


    private final String URL = Main_Workshop2_CodersLab.DB_URL;
    private final String USER = Main_Workshop2_CodersLab.DB_USER;
    private final String PASSWORD = Main_Workshop2_CodersLab.DB_PASSWORD;


    public User create(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_OF_ID_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            System.err.println("");
            return null;
        }
    }


    public User read(int userId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_OF_ID_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPassword(resultSet.getString("user_group_id"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User read(String userEmail) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_OF_EMAIL_QUERY);
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPassword(resultSet.getString("user_group_id"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_OF_ID_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int userId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_OF_ID_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }


    public User[] findAll() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPassword(resultSet.getString("user_group_id"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }
}
