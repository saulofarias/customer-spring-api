package br.com.saulofarias.customerapi.exception;

import br.com.saulofarias.customerapi.constants.MessagesConst;

public class CityNotFoundException extends RuntimeException {

	public CityNotFoundException(Long id) {
		super(MessagesConst.CITY_NOT_FOUND + id);
	}

	public CityNotFoundException(String name) {
		super(MessagesConst.CITY_NOT_FOUND + name);
	}
}
