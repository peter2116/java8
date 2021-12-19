package com.peter.spi;

/**
 * @author chen_wj
 * @Description:
 * @date 2021/12/19
 * @Description:
 * @modifier
 */
public class Dog implements Animal{

	@Override
	public void say() {
		System.out.println("Dog: wang!");
	}
}
