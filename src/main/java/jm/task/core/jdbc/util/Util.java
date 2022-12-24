package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_new";
    private static final String DB_USERNAME = "user";
    private static final String DB_PASSWORD = "user";

    private static final String DB_HIB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_HIB_URL = "jdbc:mysql://localhost:3306/test_new?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    public static SessionFactory factory = null;

    public static SessionFactory getHibernateConnection() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DB_HIB_DRIVER)
                    .setProperty("hibernate.connection.url", DB_HIB_URL)
                    .setProperty("hibernate.connection.username", DB_USERNAME)
                    .setProperty("hibernate.connection.password", DB_PASSWORD)
                    .setProperty("hibernate.dialect", DIALECT)
                    .setProperty("hibernate.c3p0.min_size", "5")
                    .setProperty("hibernate.c3p0.max_size", "20")
                    .setProperty("hibernate.c3p0.timeout", "1800")
                    .setProperty("hibernate.c3p0.max_statements", "50")
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return factory;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
