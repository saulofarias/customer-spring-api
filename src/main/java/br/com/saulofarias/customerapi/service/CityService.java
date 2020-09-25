package br.com.saulofarias.customerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saulofarias.customerapi.exception.CityNotFoundException;
import br.com.saulofarias.customerapi.model.City;
import br.com.saulofarias.customerapi.repository.CityRepository;

@Service
public class CityService {

	private final CityRepository repository;

	@Autowired
	public CityService(CityRepository repository) {
		this.repository = repository;
	}

	public List<City> findAll() {
		return repository.findAll();
	}

	public City getByName(String name) {
		return repository.findByName(name).orElseThrow(() -> new CityNotFoundException(name));
	}

	public City getByNameAndState(String name, String state) {
		return repository.findByNameAndState(name, state).orElseThrow(() -> new CityNotFoundException(state));
	}

	public City save(City newCity) {
		return repository.save(newCity);
	}

	public City update(Long id, City customer) {
		if (!isPresent(id)) {
			new CityNotFoundException(id);
		}
		customer.setId(id);
		return repository.save(customer);
	}

	public void deleteById(Long id) {
		if (!isPresent(id)) {
			new CityNotFoundException(id);
		}
		repository.deleteById(id);
	}

	private Boolean isPresent(Long id) {
		return repository.findById(id).isPresent();
	}


}
