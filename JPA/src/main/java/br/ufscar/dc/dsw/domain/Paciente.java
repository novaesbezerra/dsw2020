package br.ufscar.dc.dsw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Paciente")
public class Paciente extends Usuario {

	@Column(nullable = false, unique = true, length = 12)
	private String cpf;
	
	@Column(nullable = true, length = 20)
	private String telefone;
	
	@Column(nullable = true, length = 12)
	private String sexo;
	
	@Column(nullable = false, length = 12)
	private String nascimento;

	@ManyToMany(targetEntity = Consulta.class)
	private Set<Consulta> consultas;

	public Paciente() {
	}
	
	public Paciente(String cpf, String telefone, String sexo, String nascimento) {
		super(cpf);
		//this.cpf = cpf;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nascimento = nascimento;
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

	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public String toString() {
		return "[Nome = " + this.getNome() + ", CPF = " + cpf + "]";
	}
}
