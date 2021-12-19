package com.peter.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author chen_wj
 * @Description:
 * @date 2021/12/19
 * @Description:
 * @modifier
 */
public class SpiDemo {


	@Test
	public void animalSpi() {

		ServiceLoader<Animal> serviceLoader = ServiceLoader.load(Animal.class);
		serviceLoader.forEach(Animal::say);

	}


}
