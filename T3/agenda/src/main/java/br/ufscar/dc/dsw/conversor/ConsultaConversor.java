package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@Component
public class ConsultaConversor implements Converter<String, Consulta>{

	@Autowired
	private IConsultaService service;

	@Override
	public Consulta convert(String text) {

		if (text.isEmpty()) {
		 return null;
		}

		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}
}
