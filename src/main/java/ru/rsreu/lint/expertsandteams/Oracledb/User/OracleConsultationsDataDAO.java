package ru.rsreu.lint.expertsandteams.Oracledb.User;

import ru.rsreu.lint.expertsandteams.Datalayer.DAO.User.ConsultationsDataDAO;
import ru.rsreu.lint.expertsandteams.Datalayer.DTO.Administrator.ExpertsStatisticsDTO;
import ru.rsreu.lint.expertsandteams.Datalayer.DTO.User.QuestionAnswerDTO;
import ru.rsreu.lint.expertsandteams.Resource.SQLQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleConsultationsDataDAO implements ConsultationsDataDAO {
    private Connection connection;

    public OracleConsultationsDataDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean isUserJoinedInTeamByUserId(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueryManager.getProperty("ConsultationsDataDAO.IS_USER_JOINED_IN_TEAM_BY_ID.SQL.QUERY"));
        preparedStatement.setInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST")), id);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = false;
        while (resultSet.next()) {
            result = resultSet.getInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST"))) > Integer.parseInt(SQLQueryManager.getProperty("GENERAL.EMPTY_RESULT_SET.SQL.CONST"));
        }
        resultSet.close();
        preparedStatement.close();
        return result;
    }

    @Override
    public int findTeamIdByUserId(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueryManager.getProperty("ConsultationsDataDAO.FIND_TEAM_ID_BY_USER_ID.SQL.QUERY"));
        preparedStatement.setInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST")), userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int result = -1;
        while (resultSet.next()) {
            result = resultSet.getInt(SQLQueryManager.getProperty("GENERAL.TEAM_ID.SQL.CONST"));
        }
        resultSet.close();
        preparedStatement.close();
        return result;
    }

    @Override
    public boolean isUserCaptainByUserId(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueryManager.getProperty("ConsultationsDataDAO.IS_USER_CAPTAIN_BY_USER_ID.SQL.QUERY"));
        preparedStatement.setInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST")), id);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = false;
        while (resultSet.next()) {
            result = resultSet.getInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST"))) > Integer.parseInt(SQLQueryManager.getProperty("GENERAL.EMPTY_RESULT_SET.SQL.CONST"));
        }
        resultSet.close();
        preparedStatement.close();
        return result;
    }

    @Override
    public boolean isTeamHasExpertByTeamId(int teamId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueryManager.getProperty("ConsultationsDataDAO.IS_TEAM_HAS_EXPERT_BY_TEAM_ID.SQL.QUERY"));
        preparedStatement.setInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST")), teamId);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = false;
        while (resultSet.next()) {
            result = resultSet.getInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST"))) > Integer.parseInt(SQLQueryManager.getProperty("GENERAL.EMPTY_RESULT_SET.SQL.CONST"));
        }
        resultSet.close();
        preparedStatement.close();
        return result;
    }

    @Override
    public ExpertsStatisticsDTO findTeamExpertByTeamId(int teamId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueryManager.getProperty("ConsultationsDataDAO.FIND_TEAM_EXPERT_BY_TEAM_ID.SQL.QUERY"));
        preparedStatement.setInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST")), teamId);
        ResultSet resultSet = preparedStatement.executeQuery();
        String login = "";
        int countTeams = -1;
        int maxCountTeams = -1;
        while (resultSet.next()) {
            login = resultSet.getString(SQLQueryManager.getProperty("GENERAL.LOGIN.SQL.CONST"));
            countTeams = resultSet.getInt(SQLQueryManager.getProperty("GENERAL.COUNT_TEAMS.SQL.CONST"));
            maxCountTeams = resultSet.getInt(SQLQueryManager.getProperty("GENERAL.MAX_COUNT_TEAMS.SQL.CONST"));
        }
        ExpertsStatisticsDTO expertsStatisticsDTO = new ExpertsStatisticsDTO(login, countTeams, maxCountTeams);
        resultSet.close();
        preparedStatement.close();
        return expertsStatisticsDTO;
    }

    @Override
    public List<ExpertsStatisticsDTO> findListAvailableExperts() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLQueryManager.getProperty("ConsultationsDataDAO.FIND_LIST_AVAILABLE_EXPERTS.SQL.QUERY"));
        List<ExpertsStatisticsDTO> list = new ArrayList<>();
        while (resultSet.next()) {
            String login = resultSet.getString(SQLQueryManager.getProperty("GENERAL.LOGIN.SQL.CONST"));
            int countTeams = resultSet.getInt(SQLQueryManager.getProperty("GENERAL.COUNT_TEAMS.SQL.CONST"));
            int maxCountTeams = resultSet.getInt(SQLQueryManager.getProperty("GENERAL.MAX_COUNT_TEAMS.SQL.CONST"));
            ExpertsStatisticsDTO expertsStatisticsDTO = new ExpertsStatisticsDTO(login, countTeams, maxCountTeams);
            list.add(expertsStatisticsDTO);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    @Override
    public List<QuestionAnswerDTO> findQuestionsAndAnswersByConsultationId(int consultationId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueryManager.getProperty("ConsultationsDataDAO.FIND_QUESTIONS_AND_ANSWERS_BY_CONSULTATION_ID.SQL.QUERY"));
        preparedStatement.setInt(Integer.parseInt(SQLQueryManager.getProperty("GENERAL.FIRST_COLUMN_INDEX.SQL.CONST")), consultationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int consId = consultationId;
        String question = "";
        String answer = "";
        List<QuestionAnswerDTO> list = new ArrayList<>();
        while (resultSet.next()) {
            question = resultSet.getString(SQLQueryManager.getProperty("GENERAL.QUESTION.SQL.CONST"));
            answer = resultSet.getString(SQLQueryManager.getProperty("GENERAL.ANSWER.SQL.CONST"));
            QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(consId, question, answer);
            list.add(questionAnswerDTO);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }
}