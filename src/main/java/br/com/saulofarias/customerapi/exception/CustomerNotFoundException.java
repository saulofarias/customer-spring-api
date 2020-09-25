package br.com.saulofarias.customerapi.exception;

import br.com.saulofarias.customerapi.constants.MessagesConst;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(Long id) {
		super(MessagesConst.CUSTOMER_NOT_FOUND + id);
	}

	public CustomerNotFoundException(String name) {
		super(MessagesConst.CUSTOMER_NOT_FOUND + name);
	}
}
