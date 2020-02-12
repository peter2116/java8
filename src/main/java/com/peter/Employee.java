package com.peter;

import java.util.Objects;

public class Employee {

	private int id;
	private String name;
	private int age;
	private Double salary;



	public Employee(String name) {
		this.name = name;
	}

	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee(int id, int age, String name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public Employee(int id, String name, int age, Double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Employee() {

	}

	public int getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", salary=" + salary +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return id == employee.id &&
				age == employee.age &&
				Objects.equals(name, employee.name) &&
				Objects.equals(salary, employee.salary);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, age, salary);
	}
}
