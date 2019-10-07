package pl.coderslab;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main_Workshop2_CodersLab {

    // Main data of database (used also @ DAO classes)
    protected static final String DB_URL =
            "jdbc:mysql://localhost:3306/workshop2CodersLab?useSSL=false&characterEncoding=utf8";
    protected static final String DB_USER = "root";
    protected static final String DB_PASSWORD = "coderslab";


    // QUERYies for creating tables of database
    private static final String QUERY1 = "CREATE TABLE users " +
            "(id int AUTO_INCREMENT, " +
            "username varchar(60), " +
            "email varchar(60) UNIQUE, " +
            "password varchar(60), " +
            "PRIMARY KEY(id), " +
            "FOREIGN KEY(user_group_id) REFERENCES user_group(id));";
    private static final String QUERY2 = "CREATE TABLE user_group " +
            "(id int AUTO_INCREMENT, " +
            "name varchar(255), " +
            "PRIMARY KEY(id));";
    private static final String QUERY3 = "CREATE TABLE solution " +
            "(id int AUTO_INCREMENT, " +
            "created datetime, " +
            "updated datetime, " +
            "description text, " +
            "PRIMARY KEY(id), " +
            "FOREIGN KEY(excercise_id) REFERENCES exercise_id(id), " +
            "FOREIGN KEY(user_id) REFERENCES users_id(id));";
    private static final String QUERY4 = "CREATE TABLE exercise " +
            "(id int AUTO_INCREMENT, " +
            "title varchar(255), " +
            "description text, " +
            "PRIMARY KEY(id));";


    public static void main(String[] args) {

        //Creating database
        creatingDatabase();

        //Fill-in data
        fillInData();


    }


    public static void creatingDatabase() {
        try (Connection connection = DBUtils.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(QUERY1);
            statement.executeUpdate(QUERY2);
            statement.executeUpdate(QUERY3);
            statement.executeUpdate(QUERY4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillInData() {
        User user = new User();
        UserDAO userDAO = new UserDAO();

        user = fillInDataUser("Marcin Berger", "marcin.berger@wp.pl", "stefan", 0);
        userDAO.create(user);
        user = fillInDataUser("Stafan Paprota", "spaprota@o2.pl", "jozef", 0);
        userDAO.create(user);
        user = fillInDataUser("Józef Franciszek", "jfranciszek@gmail.com", "marian", 0);
        userDAO.create(user);
        user = fillInDataUser("Marin Kartofel", "marcin.kartofel@vp.com", "marcin", 0);
        userDAO.create(user);
    }

    public static User fillInDataUser(String username, String email, String password, int user_group_id) {
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        if (user_group_id != 0) {
            user.setUserGroupId(user_group_id);
        }
        return user;
    }

}
