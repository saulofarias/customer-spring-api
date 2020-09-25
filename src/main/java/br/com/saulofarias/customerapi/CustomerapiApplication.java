package br.com.saulofarias.customerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.saulofarias.customerapi.model.City;
import br.com.saulofarias.customerapi.model.Customer;
import br.com.saulofarias.customerapi.repository.CityRepository;
import br.com.saulofarias.customerapi.repository.CustomerRepository;
import br.com.saulofarias.customerapi.util.DateUtil;

@SpringBootApplication
public class CustomerapiApplication implements ApplicationRunner {

	private CityRepository cityRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerapiApplication(CityRepository cityRepository, CustomerRepository customerRepository) {
		this.cityRepository = cityRepository;
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerapiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		City city = cityRepository.save(new City(null, "Cidade 1", "PA"));
		customerRepository.save(new Customer(null, "Teste", 'M', DateUtil.stringToDate("2010-01-10"), 1, city));
	}

}
