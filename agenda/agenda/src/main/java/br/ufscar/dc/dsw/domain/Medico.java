package br.ufscar.dc.dsw.domain;

public class Medico {

	private Long id;
	private Long crm;
	private String nome;
	private String senha;
	private String especialidade;
	private String email;

    public Medico(Long id) {
        this.id = id;
    }

	public Medico(Long crm, String nome, String senha, String especialidade, String email) {
		this.crm = crm;
		this.nome = nome;
		this.especialidade = especialidade;
		this.email = email;
		this.senha = senha;
	}

    public Long getId() {
        return id;
    }

	public Long getcrm() {
		return crm;
	}
	public void setcrm(Long crm) {
		this.crm = crm;
	}

	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}


	public String getsenha() {
		return senha;
	}
	public void setsenha(String senha) {
		this.senha = senha;
	}

    public String getnome() {
        return nome;
    }
    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getespecialidade() {
        return especialidade;
    }

    public void setespecialidade(String especialidade) {
    	this.especialidade = especialidade;
    }
}