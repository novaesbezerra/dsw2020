package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Component
public class MedicoConversor implements Converter<String, Medico>{

	@Autowired
	private IMedicoService service;

	@Override
	public Medico convert(String text) {

		if (text.isEmpty()) {
		 return null;
		}

		String crm = String.valueOf(text);
		return service.buscarPorCrm(crm);
	}
}
