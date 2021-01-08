package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @NotBlank
    @Column(nullable = false, length = 45)
    private String role;

    public Usuario() {
    	this.role = "USER";
    }

    public Usuario(String role) {
        this.role = role;
    }

	public String getRole() {
		return role;
	}

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
