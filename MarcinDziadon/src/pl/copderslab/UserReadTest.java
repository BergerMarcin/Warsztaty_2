package pl.copderslab;

import org.mindrot.jbcrypt.BCrypt;

public class UserReadTest {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.read(7);

        System.out.println(user);

        boolean isCorrect = BCrypt.checkpw("123456", user.getPassword());
        System.out.println(isCorrect);
    }
}
