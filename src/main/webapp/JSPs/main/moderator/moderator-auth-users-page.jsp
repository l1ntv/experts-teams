<%@ page import="ru.rsreu.lint.expertsandteams.Datalayer.DTO.Administrator.UserDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизованные пользователи</title>
    <link href="styles/authentication/authentication-styles.css" rel="stylesheet" type="text/css">
    <link href="styles/main/administrator-auth-user-styles.css" rel="stylesheet" type="text/css">
    <link href="styles/main/user-main-styles.css" rel="stylesheet" type="text/css">
    <link href="styles/main/authUsers.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="D:\tournament.png" type="image/png">
</head>
<body>
<div class="header">
    <form action="controller?command=main" method="GET">
        <input type="hidden" name="command" value="main">
        <button type="submit" class="button login-button rounded-button">Действия с пользователем</button>
    </form>
    <form action="controller?command=messages_users" method="GET">
        <input type="hidden" name="command" value="messages_users">
        <button type="submit" class="button login-button rounded-button">Просмотр сообщений пользователей</button>
    </form>
    <form action="controller?command=auth_users_moderator" method="GET">
        <input type="hidden" name="command" value="auth_users_moderator">
        <button type="submit" class="button login-button rounded-button">Авторизованные пользователи</button>
    </form>
    <form action="controller?command=banned_users" method="GET">
        <input type="hidden" name="command" value="banned_users">
        <button type="submit" class="button login-button rounded-button">Заблокированные пользователи</button>
    </form>
    <form action="controller?command=logout" method="GET">
        <input type="hidden" name="command" value="logout">
        <button type="submit" class="button login-button rounded-button">Выйти</button>
    </form>
</div>
<div class="title">Список авторизованных пользователей</div>
<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Логин</th>
            <th>Тип аккаунта</th>
        </tr>
        </thead>
        <tbody>
        <% if (request.getAttribute("authUsers") != null) { %>
        <% List<UserDTO> list = (ArrayList) request.getAttribute("authUsers");%>
        <% for (int i = 0; i < list.size(); i++) { %>
        <tr>
            <td><%= list.get(i).getLogin() %>
            </td>
            <td><%= list.get(i).getAccountType().toString() %>
            </td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>