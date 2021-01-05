package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {

	@NotBlank(message = "{NotBlank.usuario.nome}")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String nome;

	@NotBlank(message = "{NotBlank.usuario.email}")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String email;

	@NotBlank(message = "{NotBlank.usuario.senha}")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
}
