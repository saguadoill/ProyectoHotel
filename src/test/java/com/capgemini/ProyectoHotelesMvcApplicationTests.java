package com.capgemini;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.services.impls.IHotelService;


@RunWith(SpringRunner.class)
@SpringBootTest
@SuiteClasses({
    TestConsumoController.class,
    TestHabitacionController.class,
    TestHotelController.class
})
public class ProyectoHotelesMvcApplicationTests {
	
	@Autowired
	IHotelService hotelService;

	@Test
	public void contextLoads() {
		
		
	}
}
