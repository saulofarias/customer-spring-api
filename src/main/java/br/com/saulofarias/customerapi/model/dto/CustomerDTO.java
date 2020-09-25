package br.com.saulofarias.customerapi.model.dto;

import java.text.ParseException;

import br.com.saulofarias.customerapi.model.City;
import br.com.saulofarias.customerapi.model.Customer;
import br.com.saulofarias.customerapi.util.DateUtil;

public class CustomerDTO {

	private String name;
	private Character sex;
	private String dateBirth;
	private Integer age;
	private Long city;

	public CustomerDTO() {

	}

	public CustomerDTO(String name, Character sex, String dateBirth, Integer age, Long city) {
		this.name = name;
		this.sex = sex;
		this.dateBirth = dateBirth;
		this.age = age;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public static CustomerDTO toDTO(Customer customer) throws ParseException {
		return new CustomerDTO(customer.getName(), customer.getSex(), DateUtil.dateToString(customer.getDateBirth()),
				customer.getAge(), customer.getCity().getId());
	}

	public Customer toEntity() throws ParseException {
		City city = new City();
		city.setId(this.city);
		return new Customer(null, this.name, this.sex, DateUtil.stringToDate(this.dateBirth), this.age, city);
	}
}
