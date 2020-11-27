package br.ufscar.dc.dsw.domain;

public class Especialidade {

    private Long id;
    private String CNPJ;
    private String nome;
    private int qtdeMedicos;

    public Especialidade(Long id) {
        this.id = id;
    }

    public Especialidade(String CNPJ, String nome) {
        this.CNPJ = CNPJ;
        this.nome = nome;
    }

    public Especialidade(Long id, String CNPJ, String nome) {
        this(CNPJ, nome);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdeMedicos() {
        return qtdeMedicos;
    }

    public void setQtdeMedicos(int qtdeMedicos) {
        this.qtdeMedicos = qtdeMedicos;
    }
}