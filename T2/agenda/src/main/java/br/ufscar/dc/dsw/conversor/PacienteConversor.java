package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Component
public class PacienteConversor implements Converter<String, Paciente>{

	@Autowired
	private IPacienteService service;

	@Override
	public Paciente convert(String text) {

		if (text.isEmpty()) {
		 return null;
		}

		String cpf = String.valueOf(text);
		return service.buscarPorCpf(cpf);
	}
}
