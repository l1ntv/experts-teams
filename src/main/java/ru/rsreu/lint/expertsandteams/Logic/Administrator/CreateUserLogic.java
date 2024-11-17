package ru.rsreu.lint.expertsandteams.Logic.Administrator;

import ru.rsreu.lint.expertsandteams.Datalayer.DAO.AdministratorDataDAO;
import ru.rsreu.lint.expertsandteams.Datalayer.DAOFactory;
import ru.rsreu.lint.expertsandteams.Datalayer.DBType;

import java.sql.SQLException;

public class CreateUserLogic {
    public static boolean isUserExistsByLogin(String login) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AdministratorDataDAO administratorDataDAO = factory.getAdministratorDataDAO();
        return administratorDataDAO.isUserExistsByLogin(login);
    }

    public static boolean createUserByLogin(String login) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AdministratorDataDAO administratorDataDAO = factory.getAdministratorDataDAO();
        return administratorDataDAO.createUserByLogin(login);
    }
}