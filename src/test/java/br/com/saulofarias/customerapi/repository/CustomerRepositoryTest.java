package br.com.saulofarias.customerapi.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.saulofarias.customerapi.model.City;
import br.com.saulofarias.customerapi.model.Customer;
import br.com.saulofarias.customerapi.util.DateUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	public TestEntityManager entityManager;

	@Autowired
	@Lazy
	public CustomerRepository repository;

	@Autowired
	public CityRepository cityRepository;

	@Test
	public void shouldReturnCustomers() throws ParseException {
		City city = new City(null, "City", "PE");
		entityManager.persist(city);
		Customer customer1 = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12,
				city);

		List<Customer> customers = Arrays.asList(customer1);

		for (Customer customer : customers) {
			entityManager.persist(customer);
			entityManager.flush();
		}

		assertEquals(1, repository.findAll().size());
	}

	@Test
	public void shouldReturnCustomer() throws ParseException {
		City city = new City(null, "City", "PE");
		entityManager.persist(city);
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		entityManager.persist(customer);
		entityManager.flush();

		Customer customerFound = repository.findById(customer.getId()).get();

		assertEquals(customerFound.getId(), customer.getId());
	}

	@Test
	public void shouldReturnCustomerCreatedWithSucess() throws ParseException {
		City city = new City(null, "City", "PE");
		entityManager.persist(city);
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		Customer newCustomer = this.repository.save(customer);
		assertNotNull(newCustomer);
		assertNotNull(newCustomer.getCity());
		assertEquals(newCustomer.getId(), customer.getId());
		assertEquals(newCustomer.getName(), customer.getName());

	}

	@Test
	public void shouldPersistAndChangeDataWithSucess() throws ParseException {
		City city = new City(null, "City", "PE");
		entityManager.persist(city);
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		entityManager.persist(customer);
		entityManager.flush();

		String name = "Customer1";

		Customer customerFound = repository.findById(customer.getId()).get();
		customerFound.setName(name);

		repository.save(customerFound);
		customerFound = repository.getOne(customer.getId());

		assertNotNull(customerFound);
		assertNotNull(customerFound.getCity());
		assertEquals(customerFound.getName(), name);
	}

	@Test
	public void deleteShouldRemoveData() throws ParseException {
		City city = new City(null, "City", "PE");
		entityManager.persist(city);
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		entityManager.persist(customer);
		entityManager.flush();

		repository.deleteById(customer.getId());

		assertFalse(repository.findById(customer.getId()).isPresent());
	}

}
