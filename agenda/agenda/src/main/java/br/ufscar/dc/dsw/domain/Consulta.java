package br.ufscar.dc.dsw.domain;

public class Consulta {

	private Long id;
	private String data;
	private Float valor;
	private Medico medico;
	private Paciente paciente;
        private Long medicoId;
        private Long pacienteId;

	public Consulta(Long id, String data, Float valor, Medico medico, Paciente paciente) {
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.medico = medico;
		this.paciente = paciente;
	}

	public Consulta(String data, Float valor, Medico medico, Paciente paciente) {
		super();
		this.data = data;
		this.valor = valor;
		this.medico = medico;
		this.paciente = paciente;
	}
        public Consulta(String data, Float valor) {
		super();
		this.data = data;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
        
        public Long getIdMedico() {
		return medicoId;
	}

	public void setIdMedico(Long medicoId) {
		this.medicoId = medicoId;
	}
        
        public Long getIdPaciente() {
		return pacienteId;
	}

	public void setIdPaciente(Long pacienteId) {
		this.pacienteId = pacienteId;
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

}
