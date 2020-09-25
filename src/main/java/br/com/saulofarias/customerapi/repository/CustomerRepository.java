package br.com.saulofarias.customerapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saulofarias.customerapi.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  public Optional<Customer> findByName(String name);

}
