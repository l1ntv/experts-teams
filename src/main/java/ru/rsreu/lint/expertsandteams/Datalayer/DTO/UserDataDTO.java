package ru.rsreu.lint.expertsandteams.Datalayer.DTO;

public class UserDataDTO {
	private String login;

	private String password;

	public UserDataDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
