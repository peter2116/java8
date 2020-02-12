package com.peter.inter;

import org.junit.Test;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/12
 * @Description:
 * @modifier
 */
public interface BooInter {

	default void sayHi() {
		System.out.println("Hello World!");
	}


	public static void show() {
		System.out.println("static method in interface!");
	}
}
