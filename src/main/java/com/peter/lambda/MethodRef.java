package com.peter.lambda;

import com.peter.Employee;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/10
 * @Description:
 * @modifier
/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 *
 */
public class MethodRef {

	/**
	 * 对象引用 :: 实例方法名
	 */
	@Test
	public void test1() {
		Employee employee = new Employee(1,20,"小李");
		Supplier<String> supplier = () -> employee.getName();
		Supplier<String> supplier2 = employee::getName;
		System.out.println(supplier.get());
		System.out.println(supplier2.get());
	}


	/**
	 * 类名 :: 静态方法名
	 */
	@Test
	public void test2() {
		BiFunction<Integer,Integer,Integer> func = Math::max;
		Integer max= func.apply(1,2);
		System.out.println(max);
	}


	/**
	 * 类名::实例方法名
	 */
	@Test
	public void test3() {
		BiPredicate<String,String> eqFunc = (x,y) -> x.equals(y);
		System.out.println(eqFunc.test("abc","abc"));

		BiPredicate<String,String> eqFunc2 = String::equals;
		System.out.println(eqFunc2.test("abc","abc"));
	}

	/**
	 * 构造器引用
	 */
	@Test
	public void test4() {
		Function<String,Employee> func1 = Employee::new;
		System.out.println(func1.apply("小王"));

		BiFunction<Integer,String,Employee> func2 = Employee::new;
		System.out.println(func2.apply(1,"赵云"));
	}


	/**
	 * 数组引用
	 */
	@Test
	public void test5() {
		Function<Integer,String[]> func1 = (x) -> new String[x];
		System.out.println(func1.apply(5).length);

		Function<Integer,String[]> func2 = String[]::new;
		System.out.println(func2.apply(5).length);
	}



}
