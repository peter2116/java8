package com.peter.spi;

/**
 * @author chen_wj
 * @Description:
 * @date 2021/12/19
 * @Description:
 * @modifier
 */
public class Cat implements Animal{

	@Override
	public void say() {
		System.out.println("Cat: miao!");
	}
}
