package com.peter.optional;

import com.peter.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/12
 * @Description:
 * @modifier
/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */

public class OptionalTest {


	/**
	 *
	 */
	@Test
	public void test1() {
		Optional<Employee> optionalEmployee = Optional.of(new Employee());
		Employee employee = optionalEmployee.get();
		System.out.println(employee);

		System.out.println("------------------------");

		Optional<Employee> optionalEmployee1 = Optional.empty();
//		System.out.println(optionalEmployee1.get()); //异常

		System.out.println("------------------------");

		Optional<Employee> optionalEmployee2 = Optional.ofNullable(null);
//		System.out.println(optionalEmployee2.get()); //异常

		System.out.println("------------------------");

		Optional<Employee> optionalEmployee3 = Optional.empty();
		System.out.println(optionalEmployee3.isPresent());
		Optional<Employee> optionalEmployee4 = Optional.ofNullable(new Employee("张三"));
		System.out.println(optionalEmployee4.isPresent());

		System.out.println("------------------------");

		Employee employee1 = optionalEmployee3.orElse(new Employee("默认"));
		System.out.println(employee1);

		System.out.println("------------------------");

		Employee employee2 = optionalEmployee3.orElseGet(() ->
		{
			Employee e = new Employee();
			e.setId(1);
			e.setName("suplier");
			return e;
		});
		System.out.println(employee2);

		System.out.println("------------------------");

		Optional<String> opName = optionalEmployee4.map(e -> e.getName());
		System.out.println(opName.get());

		System.out.println("------------------------");

		Optional<String> opName2 = optionalEmployee4.flatMap(e -> Optional.ofNullable(e.getName()));
		System.out.println(opName2.get());


	}

}
