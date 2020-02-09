package com.peter.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/9
 * @Description: java8 内置四大函数式接口
 * @modifier
 *
 * Consumer<T> 消费型接口
 *     void accept(T t)
 *
 * Supplier<T> 供给型接口
 * 	   void get()
 *
 * Function<R,T> 函数型接口
 *     R apply(T)
 *
 * Predicate<T> 断言型接口
 *     boolean test(T t)
 */
public class LambdaInnerInterFace {

	/**
	 * Consumer 消费型接口
	 */
	@Test
	public void test1() {
		happy(100,(x) -> System.out.println("大保健消费：" + x));
		happy(200,x -> System.out.println("澡堂子消费" + x));
	}

	public void happy(int money, Consumer<Integer> consumer) {
		consumer.accept(money);
	}

	/**
	 * Supply 供给型接口
	 */
	@Test
	public void test2() {
		String origin = "origin";
		String result1 = getStr(origin,() -> " ==> supply one!");
		String result2 = getStr(origin,() -> " ==> supply two!");
		System.out.println(result1);
		System.out.println(result2);
	}

	public String getStr(String origin, Supplier<String> supplier) {
		return origin + supplier.get();
	}


	/**
	 * Function 函数型接口
	 */
	@Test
	public void test3() {
		String origin = "origin";
		String result1 = StrHandler(origin,(x) -> x.toUpperCase());
		String result2 = StrHandler(origin,x -> x + " len:" + x.length());
		System.out.println(result1);
		System.out.println(result2);
	}

	public String StrHandler(String str,Function<String,String> function) {
		return function.apply(str);
	}

	/**
	 * Predicate 断言型接口
	 */
	@Test
	public void test4() {
		List<String> originList = Arrays.asList(new String[] {
				"This",
				"is",
				"a",
				"predicate",
				"demo"
		});

		//过滤lenth<3的
		List<String> rtList1 = filter(originList,(x) -> x.length() > 3);
		//过滤包含字母a的
		List<String> rtList2 = filter(originList,x -> x.indexOf("a") > -1);

		System.out.println(rtList1);
		System.out.println(rtList2);
	}

	public List<String> filter(List<String> originList, Predicate<String> predicate) {
		List<String> resultList = new ArrayList<>();
		for(String item : originList) {
			if(predicate.test(item)) {
				resultList.add(item);
			}
		}
		return resultList;
	}

}
