package ru.rsreu.lint.expertsandteams.Logic.Common;

import ru.rsreu.lint.expertsandteams.Datalayer.DTO.Common.UserDataDTO;
import ru.rsreu.lint.expertsandteams.Datalayer.DAO.Common.AuthenticationDataDAO;
import ru.rsreu.lint.expertsandteams.Datalayer.DAOFactory;
import ru.rsreu.lint.expertsandteams.Datalayer.DBType;
import ru.rsreu.lint.expertsandteams.Mapper.PasswordMapper;

import java.sql.SQLException;

public class LoginLogic {

    public static boolean isBannedUser(String login) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AuthenticationDataDAO authenticationDataDAO = factory.getAuthenticationDataDAO();
        return authenticationDataDAO.isBannedUser(login);
    }

    public static boolean isCorrectUserData(String login, String password) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AuthenticationDataDAO authenticationDataDAO = factory.getAuthenticationDataDAO();
        return (authenticationDataDAO.isCorrectUserDataByLoginAndPassword(new UserDataDTO(login, PasswordMapper.mapPassword(password))));
    }

    public static boolean isCaptainByUserId(int userId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AuthenticationDataDAO authenticationDataDAO = factory.getAuthenticationDataDAO();
        return (authenticationDataDAO.isUserCaptainByUserId(userId));
    }

    public static int getUserIdByLogin(String login) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AuthenticationDataDAO authenticationDataDAO = factory.getAuthenticationDataDAO();
        return (authenticationDataDAO.getUserIdByLogin(login));
    }

    public static int getUserGroupTypeIdByUserId(int userId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AuthenticationDataDAO authenticationDataDAO = factory.getAuthenticationDataDAO();
        return (authenticationDataDAO.getUserGroupTypeIdByUserId(userId));
    }

    public static void setOnlineStatusByUserId(int userId) throws SQLException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        AuthenticationDataDAO authenticationDataDAO = factory.getAuthenticationDataDAO();
        authenticationDataDAO.setOnlineStatusByUserId(userId);
    }
}