package br.ufscar.dc.dsw.domain;

public class Paciente {

	private Long id;
	private Long cpf;
	private String nome;
	private int sexo;
	private String senha;
	private String email;
	private String telefone;
	private String nascimento;

	public Paciente(Long id) {
		this.id = id;
	}

	public Paciente(Long cpf, String nome, int sexo, String nascimento, String senha) {
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.senha = senha;
	}

    public Long getId() {
        return id;
    }

	public Long getcpf() {
		return cpf;
	}
	public void setcpf(Long cpf) {
		this.cpf = cpf;
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

    public String getnascimento() {
        return nascimento;
    }

    public void setnascimento(String nascimento) {
    	this.nascimento = nascimento;
    }
    public int getsexo() {
        return sexo;
    }

    public void setsexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String gettelefone() {
        return telefone;
    }

    public void settelefone(String telefone) {
        this.telefone = telefone;
    }

}