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

@SuppressWarnings("serial")
@Entity
@Table(name = "Medico")
public class Medico extends Usuario {

	@Column(nullable = false, unique = true, length = 12)
	private String crm;

	@Column(nullable = false, length = 100)
	private String especialidade;

	@OneToMany(mappedBy = "medico")
	private List<Consulta> consultas;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public String toString() {
		return "[Nome = " + this.getNome() + ", CRM = " + crm + ", Especialidade = " + especialidade + "]";
	}
}
