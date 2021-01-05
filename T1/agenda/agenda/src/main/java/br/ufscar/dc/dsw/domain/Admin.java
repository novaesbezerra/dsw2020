package br.ufscar.dc.dsw.domain;

public class Admin {

	private Long id;
	private String login;
	private String senha;
	
	public Admin(Long id) {
		this.id = id;
	}

	public Admin(Long id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

    public Long getId() {
        return id;
    }

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}