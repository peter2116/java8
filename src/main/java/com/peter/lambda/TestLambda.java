package com.peter.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author chen_wj
 * @Description:  lambda表达式(匿名函数)基础语法
 * @date 2020/2/8
 * @Description:
 * @modifier
 *
 * java8 引入新增操作符 -> 讲表达式拆分成左右两部分
 * 左侧：lambda 表达式参数列表
 * 右侧：lambda 表达式所需要执行的功能，即lambda体
 *
 * 语法格式一：无参数，无返回值
 * 		     () -> System.out.println("Hello lamda")
 *
 * 语法格式二：一个参数，无返回值
 * 			 (x) -> System.out.println(x)
 *
 * 语法格式三：一个参数，小括号可以省略
 *			 (x) -> System.out.println(x)
 *
 * 语法格式四：有多个参数，lambda体有多条语句，有返回值
 * 		     lambda体必须有大括号
 *
 * 语法格式五：lambda体中只有一条语句 大括号和return都可以省略不写
 *
 * 语法格式六：lamb表达式参数列表类型定义可以省略，JVM编译器可以通过上下文推断出来，即"类型推导"
 *
 *
 * Lambda表达式需要函数式接口的支持
 * 函数式接口：接口中只有一个抽象方法，可以使用@FunctionalInterace修饰，jvm可以检查是否是函数式接口
 */
public class TestLambda {

	/**
	 * 语法格式一：无参数，无返回值
	 * 		     () -> System.out.println("Hello lamda")
	 */
	@Test
	public void test1() {
		//java8 以后传入匿名内部类的参数默认final形式
		int i = 0;
		//原来的匿名内部类形式
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("匿名内部类形式" + i);
			}
		};

		System.out.println("--------------------------");

		//lambda形式
		Runnable r2 = () -> System.out.println("lambda形式" + i);

		r1.run();
		r2.run();
	}


	/**
	 * 语法格式二：一个参数，无返回值
	 * 			 (x) -> System.out.println(x)
	 */
	@Test
	public void test2() {
		Consumer<String> consumer = (x) -> System.out.println(x);
		consumer.accept("一个参数，无返回值");
	}


	/**
	 * 语法格式三：一个参数，小括号可以省略
	 * 			 (x) -> System.out.println(x)
	 */
	public void test3() {
		Consumer<String> consumer = x -> System.out.println(x);
		consumer.accept("一个参数，无返回值");

	}


	/**
	 * 语法格式四：有多个参数，lambda体有多条语句，有返回值
	 * 		     lambda体必须有大括号
	 */
	public void test4() {
		Comparator<Integer> comparator = (x,y) -> {
			System.out.println("语法格式四");
			return Integer.compare(x,y);
		};
	}

	/**
	 * 函数式接口
	 * 需求：对一个数进行运算
	 */
	@Test
	public void test5() {
		Func func1 = x -> x + 200;
		Func func2 = x -> x * 2;
		System.out.println(operation(100,func1));
		System.out.println(operation(100,func2));
	}

	public Integer operation(Integer i, Func func) {
		return func.getVal(i);
	}
}
