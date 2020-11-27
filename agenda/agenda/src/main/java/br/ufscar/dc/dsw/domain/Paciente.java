package br.ufscar.dc.dsw.domain;

public class Paciente {

	private long cpf;
	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private int sexo;
	private String nascimento;
	
	public Paciente(Long cpf) {
		this.cpf = cpf;
	}
	
	public Paciente(Long cpf, String nome, int sexo, String nascimento, String senha) {
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.nascimento = nascimento;
		this.senha = senha;
		
	}
	
	public Long getcpf() {
		return cpf;
	}
	public void setcpf(Long cpf) {
		this.cpf = cpf;
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