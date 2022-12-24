package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService us = new UserServiceImpl();

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING);
        System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "WARNING");

        us.createUsersTable();

        us.saveUser("Anna", "Brown", (byte) 25);
        us.saveUser("Boris", "Crotov", (byte) 29);
        us.saveUser("Celin", "Dion", (byte) 31);
        us.saveUser("Djoui", "Elington", (byte) 21);

        us.removeUserById(4);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
