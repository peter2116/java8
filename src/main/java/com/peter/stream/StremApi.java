package com.peter.stream;

import com.peter.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chen_wj
 * @Description: StreamApi
 * @date 2020/2/10
 * @Description:
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作(终端操作)
 */
public class StremApi {

	//1.创建Stream
	@Test
	public void test1(){
		//1. Collection 提供了两个方法  stream() 与 parallelStream()
		Stream<String> strem = new ArrayList<String>().stream();
		Stream<String> strem2 = new ArrayList<String>().parallelStream();

		//2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] intArr = new Integer[10];
		Stream<Integer> stream2 = Arrays.stream(intArr);

		//3. 通过 Stream 类中静态方法 of()
		Stream<Integer> stream4 = Stream.of(1,2,3,4,5);

		//4. 创建无限流
		//迭代
		Stream<Integer> stream5 = Stream.iterate(0, x -> x + 2);
		stream5.limit(5)
				.forEach(System.out::println);

		//生成
		Stream<Double> stream6 = Stream.generate(Math::random);
		stream6.limit(5)
				.forEach(System.out::println);

	}

	List<Employee> employeeList = Arrays.asList(
			new Employee(101,"张三",18,5000.55),
			new Employee(101,"李四",20,8888.88),
			new Employee(101,"王五",25,6666.66),
			new Employee(101,"赵六",30,7777.77),
			new Employee(101,"田七",35,4444.44),
			new Employee(101,"冯八",50,3333.33),
			new Employee(101,"陈九",55,10000.00),
			new Employee(101,"陈九",55,10000.00)
	);

	/**
	 * 中间操作
	 *
	 * 筛选与切片
	 * filter——接收 Lambda ， 从流中排除某些元素。
	 * limit——截断流，使其元素不超过给定数量。
	 * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，
	 * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
	@Test
	public void test2() {
		employeeList.stream()
				.filter(e -> e.getAge() > 30)
				.forEach(System.out::println);
		System.out.println("------------------------------------");

		employeeList.stream()
				.limit(3)
				.forEach(System.out::println);
		System.out.println("------------------------------------");

		employeeList.stream()
				.skip(2)
				.forEach(System.out::println);
		System.out.println("------------------------------------");

		employeeList.stream()
				.distinct()
				.forEach(System.out::println);
	}


	/**
	 * 中间操作二
	 * 映射
	 * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应
	 * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
	@Test
	public void test3() {
		employeeList.stream()
				.map(e -> e.getName())
				.forEach(System.out::println);

		System.out.println("------------------------------------");
		
		employeeList.stream()
				.map(e -> e.getName())
				.flatMap(str -> {
					List list = new ArrayList();
					for(char c : str.toCharArray()) {
						list.add(c);
					}
					return list.stream();
				})
				.forEach(System.out::println);
	}


	/**
	 * 	sorted()——自然排序
	 * 	sorted(Comparator com)——定制排序
	 */
	@Test
	public void test4() {
		employeeList.stream()
				.map(e -> e.getName())
				.sorted()
				.forEach(System.out::println);

		System.out.println("------------------------------------");

		employeeList.stream()
				.map(e -> e.getSalary())
				.sorted(Double::compare)
				.forEach(System.out::println);
	}


	/**
	 * 3. 终止操作
	 * allMatch——检查是否匹配
	 * anyMatch——检查是否至少
	 * noneMatch——检查是否没
	 * findFirst——返回第一个
	 * findAny——返回当前流中
	 * count——返回流中元素的
	 * max——返回流中最大值
	 * min——返回流中最小值
	 */
	@Test
	public void test5() {
		boolean b1 = employeeList.stream()
								.allMatch(e -> e.getAge() > 30);
		System.out.println(b1);

		boolean b2 = employeeList.stream()
				.anyMatch(e -> e.getName().equals("张三"));
		System.out.println(b2);

		boolean b3 = employeeList.stream()
				.noneMatch(e -> e.getSalary() > 9999999);
		System.out.println(b3);

		Optional<Employee> op = employeeList.stream()
				.findFirst();
		System.out.println(op.get());

		Optional<Employee> op2 = employeeList.stream()
				.findAny();
		System.out.println(op2.get());

		long count = employeeList.stream()
				.filter(e -> e.getSalary() > 9000)
				.count();
		System.out.println(count);

		Optional<Integer> maxOp = employeeList.stream()
				.map(Employee::getAge)
				.max(Integer::compareTo);
		System.out.println(maxOp.get());

		Optional<Integer> minOP = employeeList.stream()
				.map(Employee::getAge)
				.min(Integer::compareTo);
		System.out.println(minOP.get());
	}


	/**
	 * 3. 终止操作
	 * 归约
	 * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值
	 */
	@Test
	public void test6() {
		List<Integer> numList = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9});
		int sum = numList.stream()
				.reduce(0,(x,y) -> x + y);
		System.out.println(sum);

		Optional<Integer> op = numList.stream()
				.reduce(Integer::sum);
		System.out.println(op.get());
	}


	/**
	 * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test7() {
		List list = employeeList.stream()
				.filter(e -> e.getSalary() > 5000)
				.collect(Collectors.toList());
		list.forEach(System.out::println);

		System.out.println("------------------------------------");

		Optional<Double> op = employeeList.stream()
				.map(e -> e.getSalary())
				.collect(Collectors.maxBy(Double::compare));
		System.out.println(op.get());
	}


	/**
	 * 分组groupingBy
	 */
	@Test
	public void test8() {
		Map<String, List<Employee>> collect = employeeList.stream()
				.collect(Collectors.groupingBy(e -> {
					return e.getSalary() > 5000 ?
							"high salary" :
							"normal salary";
				}));
		System.out.println(collect);
	}


	/**
	 * 分区partitioningBy
	 */
	@Test
	public void test9() {
		Map<Boolean, List<Employee>> collect = employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
		System.out.println(collect);
	}


	/**
	 * 连接 join
	 */
	@Test
	public void test10() {
		String collect = employeeList.stream()
				.map(e -> e.getName())
				.collect(Collectors.joining(","));
		System.out.println(collect);
	}



}
