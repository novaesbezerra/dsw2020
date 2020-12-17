package br.ufscar.dc.dsw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String hora;

	@Column(nullable = false, length = 5)
	private String data;

	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	

	public Consulta() {	
	}
	
	public Consulta(String hora, String data, Medico medico, Paciente paciente) {
		this.hora = hora;
		this.data = data;
		this.medico = medico;
		this.paciente = paciente;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	/*

	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	*/
	/*
	
	public void addAluno(Aluno a) {
		this.alunos.add(a);
	}
*/
	@Override
	public String toString() {
		return "[Hora = " + hora + ", Data = " + data + "]";
	}
}