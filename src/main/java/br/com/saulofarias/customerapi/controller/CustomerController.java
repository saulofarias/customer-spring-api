package br.com.saulofarias.customerapi.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.saulofarias.customerapi.model.Customer;
import br.com.saulofarias.customerapi.model.dto.CustomerDTO;
import br.com.saulofarias.customerapi.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

	private final CustomerService customerService;
	 
	@Autowired
	public CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok().body(customerService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(customerService.getById(id));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Customer> getByName(@PathVariable String name) {
		return ResponseEntity.ok().body(customerService.getByName(name));
	}

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody CustomerDTO customer) throws ParseException {
		Customer savedCustomer = customerService.save(customer.toEntity());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCustomer.getId()).toUri();
		return ResponseEntity.created(location).body(CustomerDTO.toDTO(savedCustomer));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Customer customer) {
		customerService.update(id, customer);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		customerService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
