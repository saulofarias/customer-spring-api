package br.com.saulofarias.customerapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saulofarias.customerapi.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	public Optional<City> findByName(String name);

	public Optional<City> findByNameAndState(String name, String state);

}
