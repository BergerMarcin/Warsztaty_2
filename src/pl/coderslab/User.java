package pl.coderslab;

/**
 * Class User containing data from table users of database
 * Class User cooperating with Class UserDAO (DAO - Data Access Object; UserDAO is implementing CRUD functionality for database)
 */

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id = 0;
    private String userName = null;
    private String email = null;
    private String password = null;
    private int user_group_id = 0;

// MOVE to User_group Class
    public static boolean isGroupId(int groupID) {
// TODO iterate User_group table to check if group exists
        return true;
    }

    // Metod available for other Classes & Tests
    public static boolean isEmailUnique(String email) {
// TODO iterate User table to check if email is unique
        return true;
    }

    public User() {
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        setEmail(email);            // before set email setEmail checks if email is unique
        setPassword(password);      // setPassword hashes password
    }

    public User(String userName, String email, String password, int group) {
        this.userName = userName;
        setEmail(email);
        setPassword(password);
        setUserGroupId(group);
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserGroupId() {
        return user_group_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        if (isEmailUnique(email)) {
            this.email = email;
        }
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setUserGroupId(int userGroupId) {
//        if (user.getGroup() != null && user.getGroup().getId() > 0) {
//            statement.setInt(4, user.getGroup().getId());
//        } else {
//            statement.setString(4, null);
//        }
        if (isGroupId(userGroupId)) {
            this.user_group_id = userGroupId;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", group=" + user_group_id +
                '}';
    }

}
