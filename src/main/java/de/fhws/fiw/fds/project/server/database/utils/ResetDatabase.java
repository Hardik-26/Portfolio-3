package de.fhws.fiw.fds.project.server.database.utils;

import de.fhws.fiw.fds.project.server.database.DaoFactory;

public class ResetDatabase {

    public static void resetAll() {
        DaoFactory.getInstance().getPersonDao().resetDatabase();
    }

}
