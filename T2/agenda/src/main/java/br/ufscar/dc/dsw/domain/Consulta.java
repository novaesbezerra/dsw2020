package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {

	@NotBlank
	@Size(min = 0, max = 18)
	@Column(nullable = false, length = 30)
	private String hora;

	@NotBlank
	@Size(min = 0, max = 60)
	@Column(nullable = false, length = 30)
	private String data;

	@ManyToOne(mappedBy = "medico")
	private List<Medico> medicos;

	@ManyToOne(mappedBy = "paciente")
	private List<Paciente> pacientes;

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

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}


	@Override
	public String toString() {
		return "[Hora = " + hora + ", Data = " + data + "]";
	}
}
