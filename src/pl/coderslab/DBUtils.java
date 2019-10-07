package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static pl.coderslab.Main_Workshop2_CodersLab.DB_URL;
import static pl.coderslab.Main_Workshop2_CodersLab.DB_USER;
import static pl.coderslab.Main_Workshop2_CodersLab.DB_PASSWORD;

public class DBUtils {

    /**
     * Return object of class Connection connected to MySQL database resource
     * @param db_url                - url address of MySQL database (jdbc:mysql://localhost:3306/NAME_DB?...)
     * @param db_user               - user of MySQL database (most cases: "root")
     * @param db_pass               - password to MySQL database
     * @return
     * @throws SQLException         - in case problem with database open/connection
     */
    public static Connection getConnection(String db_url, String db_user, String db_pass) throws SQLException {
        return DriverManager.getConnection(db_url, db_user, db_pass);
    }

    /**
     * Return object of class Connection connected to MySQL database resource
     * @return
     * @throws SQLException         - in case problem with database open/connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}
