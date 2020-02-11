package com.peter.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/11
 * @Description: Stream 并行流改进Fork Jion 框架
 * @modifier
 */
public class Parallel {

	/**
	 * 需求计算 0 ~ 100000 0000 00 数字的和
	 */

	/**
	 * 循环方式
	 */
	@Test
	public void test1() {
		Instant begin = Instant.now();
		long sum = 0;
		for(int i=0; i<= 10000000000L; i++) {
			sum += i;
		}
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println(Duration.between(begin,end).toMillis()); //
	}

	/**
	 * Fork join 方式
	 */
	@Test
	public void test2() {
		Instant begin = Instant.now();
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCaculator(0L,10000000000L);
		Long sum = task.invoke();
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println(Duration.between(begin,end).toMillis());  //39876
	}


	/**
	 * 并行流方式
	 */
	@Test
	public void test3() {
		Instant begin = Instant.now();
		long sum = LongStream.rangeClosed(0L, 10000000000L)
				.reduce(0,Long::sum);
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println(Duration.between(begin,end).toMillis()); //6248
	}

}
