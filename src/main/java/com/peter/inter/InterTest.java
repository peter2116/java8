package com.peter.inter;

import org.junit.Test;

/**
 * @author chen_wj
 * @Description:
 * @date 2020/2/12
 * @Description:
 * @modifier
 */
public class InterTest {

	@Test
	public void test1() {
		new Sub().sayHi();
		BooInter.show();
	}

}
