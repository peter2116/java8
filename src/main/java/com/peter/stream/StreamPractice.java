package com.peter.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/11
 * @Description:
 * @modifier
 */
public class StreamPractice {

	/**
	 * 给定一个数字列表，返回由数字平方构成的列表
	 */
	@Test
	public void test1() {
		List<Integer> numList = Arrays.asList(new Integer[]{1,2,3,4,5});
		Object poList = numList.stream()
				.map(x -> x * x)
				.collect(Collectors.toList());
		System.out.println(poList);
	}

	/**
	 * 用map和reduce获取 Employee 的个数
	 */
	@Test
	public void test2() {
		Optional<Integer> op = StremApi.employeeList.stream()
				.map(e -> 1)
				.reduce(Integer::sum);
		System.out.println(op.get());
	}

}
