package ru.rsreu.lint.expertsandteams.Command.Commands.Administrator;

import ru.rsreu.lint.expertsandteams.Command.ActionCommand;
import ru.rsreu.lint.expertsandteams.Command.Page;
import ru.rsreu.lint.expertsandteams.Enums.AccountsTypesEnum;
import ru.rsreu.lint.expertsandteams.Enums.CommandEnum;
import ru.rsreu.lint.expertsandteams.Enums.DirectTypesEnum;
import ru.rsreu.lint.expertsandteams.Logic.Administrator.DeleteUserLogic;
import ru.rsreu.lint.expertsandteams.Resource.ConfigurationManager;
import ru.rsreu.lint.expertsandteams.Validation.DataValidator;
import ru.rsreu.lint.expertsandteams.Validation.ValidationData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class DeleteUserCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        String login = request.getParameter(ConfigurationManager.getProperty("LOGIN.PROPERTY.CONST"));
        String adminLogin = (String) session.getAttribute(ConfigurationManager.getProperty("USER_LOGIN.CONST"));
        ValidationData validationData = DataValidator.validateCreationUserData(login);
        if (validationData.getIsCorrectData()) {
            if (DeleteUserLogic.isUserExistsByLogin(login) && !adminLogin.equals(login)) {
                AccountsTypesEnum accountsTypesEnum = DeleteUserLogic.searchDeletedUserRoleByLogin(login);
                switch (accountsTypesEnum) {
                    case USER:
                        if (DeleteUserLogic.isUserJoinedInTeamByLogin(login)) {
                            if (DeleteUserLogic.isUserCaptainInTeamByLogin(login)) {
                                DeleteUserLogic.decreaseTeamCountsForExpert(login);
                                DeleteUserLogic.deleteTeamForOtherMembers(login);
                                DeleteUserLogic.deleteCaptainDataByLogin(login);
                                DeleteUserLogic.deleteUserFromTeamMembersByLogin(login);
                                DeleteUserLogic.deleteUserFromUserDataByLogin(login);
                            } else {
                                DeleteUserLogic.decreaseCountMembers(login);
                                DeleteUserLogic.deleteUserFromTeamMembersByLogin(login);
                                DeleteUserLogic.deleteUserFromUserDataByLogin(login);
                            }
                        } else {
                            DeleteUserLogic.deleteUserFromUserDataByLogin(login);
                        }
                        break;
                    case EXPERT:
                        DeleteUserLogic.deleteExpertDataByLogin(login);
                        DeleteUserLogic.deleteUserFromUserDataByLogin(login);
                        break;
                    case MODERATOR:
                        DeleteUserLogic.deleteUserFromUserDataByLogin(login);
                        break;
                    case ADMINISTRATOR:
                        DeleteUserLogic.deleteUserFromUserDataByLogin(login);
                        break;
                }
            }
            request.setAttribute(ConfigurationManager.getProperty("IS_EXISTS_FLAG.CONST"), false);
            return new Page(ConfigurationManager.getProperty("ADMINISTRATOR.MAIN.PAGE"), ConfigurationManager.getProperty("MAIN.URL"), DirectTypesEnum.FORWARD, CommandEnum.MAIN);
        }
        request.setAttribute(ConfigurationManager.getProperty("ERROR_MESSAGE.CONST"), validationData.getErrorMessage());
        return new Page(ConfigurationManager.getProperty("ADMINISTRATOR.MAIN.PAGE"), ConfigurationManager.getProperty("MAIN.URL"), DirectTypesEnum.FORWARD, CommandEnum.MAIN);
    }
}
