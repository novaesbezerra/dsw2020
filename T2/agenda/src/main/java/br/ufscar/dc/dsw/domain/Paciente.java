package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Paciente extends Usuario {

	private static final String ROLE_NAME = "PACIENTE";

	@Column(nullable = false, unique = true, length = 16)
	private String cpf;

	@Column(nullable = true, length = 20)
	private String telefone;

	@Column(nullable = true, length = 12)
	private String sexo;

	@Column(nullable = false, length = 12)
	private String nascimento;

	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas;

	public Paciente(String nome, String email, String senha, String cpf, String telefone, String sexo, String nascimento) {
		this.cpf = cpf;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nascimento = nascimento;
        setEmail(email);
        setSenha(senha);
        setNome(nome);
	}

    public Paciente() {
        super(ROLE_NAME);
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public String toString() {
		return "[Nome = " + this.getNome() + ", CPF = " + cpf + "]";
	}
}
