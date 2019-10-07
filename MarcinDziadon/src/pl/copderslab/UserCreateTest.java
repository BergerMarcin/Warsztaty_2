package pl.copderslab;

import org.mindrot.jbcrypt.BCrypt;

public class UserCreateTest {

    public static void main(String[] args) {

        // Stworzenie grupy
        Group group = new Group("Grupa 2");
        GroupDao groupDao = new GroupDao();
        groupDao.create(group);

        // Stworzenie obiektu user
        User user = new User("jkowalski", "jkowalski5@test.pl", "123456");
        user.setGroup(group);

        // Stworzenie obiektu dao
        UserDao userDao = new UserDao();
        // Wywołanie metody zapisującej użytkownika w tabeli useres
        userDao.create(user);
    }
}
