package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	@Override
	public String toString() {
		return "[Hora = " + hora + ", Data = " + data + "]";
	}
}
