package ru.rsreu.lint.expertsandteams.Command.Commands.User;

import ru.rsreu.lint.expertsandteams.Command.ActionCommand;
import ru.rsreu.lint.expertsandteams.Command.Page;
import ru.rsreu.lint.expertsandteams.Enums.CommandEnum;
import ru.rsreu.lint.expertsandteams.Enums.DirectTypesEnum;
import ru.rsreu.lint.expertsandteams.Logic.User.LeaveTeamLogic;
import ru.rsreu.lint.expertsandteams.Resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LeaveTeamCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute(ConfigurationManager.getProperty("USER_ID.CONST"));
        boolean isCaptainLeaving = false;
        if (LeaveTeamLogic.isUserCaptainByUserId(userId)) {
            isCaptainLeaving = true;
        } else {
            int teamId = LeaveTeamLogic.findTeamIdByUserId(userId);
            LeaveTeamLogic.deleteUserFromTeamMembersTableByUserId(userId);
            LeaveTeamLogic.decreaseCountMembersFromTeamsTableByTeamId(teamId);
            LeaveTeamLogic.updateTeamIdFromUserDataTableByUserId(userId);
        }
        session.setAttribute(ConfigurationManager.getProperty("IS_CAPTAIN_LEAVING_FLAG.CONST"), isCaptainLeaving);
        return new Page(ConfigurationManager.getProperty("USER.MAIN.PAGE"), ConfigurationManager.getProperty("MAIN.URL"), DirectTypesEnum.REDIRECT, CommandEnum.MAIN);
    }
}