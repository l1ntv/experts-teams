package ru.rsreu.lint.expertsandteams.Logic;

import ru.rsreu.lint.expertsandteams.Datalayer.DAOFactory;
import ru.rsreu.lint.expertsandteams.Datalayer.DBType;
import ru.rsreu.lint.expertsandteams.Datalayer.DAO.LogoutDataDAO;

import java.sql.SQLException;

public class LogoutLogic {
    public static void setOfflineStatusByUserId(int userId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        LogoutDataDAO logoutDataDAO = factory.getLogoutDataDAO();
        logoutDataDAO.setOfflineStatusByUserId(userId);
    }

}
