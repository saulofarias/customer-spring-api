package br.com.saulofarias.customerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saulofarias.customerapi.exception.CustomerNotFoundException;
import br.com.saulofarias.customerapi.model.Customer;
import br.com.saulofarias.customerapi.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAll();
	}

	public Customer getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	public Customer getByName(String name) {
		return repository.findByName(name).orElseThrow(() -> new CustomerNotFoundException(name));
	}

	public Customer save(Customer newCustomer) {
		return repository.save(newCustomer);
	}

	public Customer update(Long id, Customer customer) {
		if (!isPresent(id)) {
			new CustomerNotFoundException(id);
		}
		customer.setId(id);
		return repository.save(customer);
	}

	public void deleteById(Long id) {
		if (!isPresent(id)) {
			new CustomerNotFoundException(id);
		}
		repository.deleteById(id);
	}

	private Boolean isPresent(Long id) {
		return repository.findById(id).isPresent();
	}

}
