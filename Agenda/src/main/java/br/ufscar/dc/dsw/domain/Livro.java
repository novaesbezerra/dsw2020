package br.ufscar.dc.dsw.domain;

public class Medico {

    private Long id;
    private String titulo;
    private String autor;
    private Integer ano;
    private Float preco;
    private Especialidade especialidade;

    public Medico(Long id) {
        this.id = id;
    }

    public Medico(String titulo, String autor, Integer ano, Float preco,
            Especialidade especialidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.preco = preco;
        this.especialidade = especialidade;
    }

    public Medico(Long id, String titulo, String autor, Integer ano, 
            Float preco, Especialidade especialidade) {
        this(titulo, autor, ano, preco, especialidade);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
    @Override
    public String toString() {
    	return titulo + ", " + autor + "(" + preco + ")"; 
    }
}
