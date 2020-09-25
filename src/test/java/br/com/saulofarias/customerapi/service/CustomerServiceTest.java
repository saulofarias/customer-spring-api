package br.com.saulofarias.customerapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.saulofarias.customerapi.model.Customer;
import br.com.saulofarias.customerapi.model.City;
import br.com.saulofarias.customerapi.repository.CustomerRepository;
import br.com.saulofarias.customerapi.util.DateUtil;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private CustomerService customerService;

	@Test
	public void shouldReturnsAllCustomers() throws Exception {
		City city = new City(null,"City", "PE");
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		List<Customer> expectedCustomers = Arrays.asList(customer);
		doReturn(expectedCustomers).when(repository).findAll();

		List<Customer> actualCustomers = customerService.findAll();

		assertThat(actualCustomers).isEqualTo(expectedCustomers);
	}

	@Test
	public void shouldReturnCustomerById() throws Exception {
		City city = new City(null,"City", "PE");
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

		Customer actualCustomer = customerService.getById(customer.getId());

		assertEquals(customer, actualCustomer);
	}

	@Test
	public void shouldReturnCustomerCreatedWithSucess() throws Exception {
		City city = new City(null,"City", "PE");
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		doReturn(customer).when(repository).save(customer);

		Customer actualCustomer = customerService.save(customer);

		assertThat(actualCustomer).isNotNull();

	}

	@Test
	public void shouldPersistAndChangeDataWithSucess() throws Exception {
		City city = new City(null,"City", "PE");
		Customer customer = new Customer(null, "Customer1", 'M', DateUtil.stringToDate("2010-09-09"), 12, city);

		when(repository.save(customer)).thenReturn(customer);
		when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

		Customer newCustomer = customerService.getById(customer.getId());
		newCustomer.setName("Customer1");

		Customer foundCustomer = customerService.update(newCustomer.getId(), newCustomer);

		assertEquals(foundCustomer.getName(), newCustomer.getName());

	}
}
