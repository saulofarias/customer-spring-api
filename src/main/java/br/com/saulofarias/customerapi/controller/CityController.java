package br.com.saulofarias.customerapi.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.saulofarias.customerapi.model.City;
import br.com.saulofarias.customerapi.service.CityService;

@RestController
@RequestMapping(value = "/api/cities")
public class CityController {

	private final CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping
	public ResponseEntity<List<City>> findAll() {
		return ResponseEntity.ok().body(cityService.findAll());
	}

	@GetMapping("/city-states")
	public ResponseEntity<City> getByNameAndState(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "state", required = false) String state) {
		if (state == null) {
			return ResponseEntity.ok().body(cityService.getByName(name));
		}
		return ResponseEntity.ok().body(cityService.getByNameAndState(name, state));
	}

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody City customer) {
		City savedCity = cityService.save(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCity.getId()).toUri();
		return ResponseEntity.created(location).body(savedCity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody City customer) {
		cityService.update(id, customer);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		cityService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
