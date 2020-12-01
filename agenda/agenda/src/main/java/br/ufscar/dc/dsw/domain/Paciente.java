package br.ufscar.dc.dsw.domain;

public class Paciente {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Long cpf;
	private String telefone;
	private String sexo;
	private String nascimento;

	public Paciente(Long id) {
		this.id = id;
	}

	public Paciente(String nome, String email, String senha, Long cpf, String telefone, String sexo, String nascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.senha = senha;
	}

    public Paciente(Long id, String nome, String email, String senha, Long cpf, String telefone, String sexo, String nascimento) {
        this(nome, email, senha, cpf, telefone, sexo, nascimento); //construtor recursivo
        this.id = id;
    }

    public Long getId() {
        return id;
    }

	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
    	this.nascimento = nascimento;
    }
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}