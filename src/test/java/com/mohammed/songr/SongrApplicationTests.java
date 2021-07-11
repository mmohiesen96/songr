package com.mohammed.songr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SongrApplicationTests {
Album album ;
/*
Test initializing with constructor
 */
	@Test
	void contextLoads() {
		album = new Album("Hi", "test", 15, 15000, "fusses.com");
		Assertions.assertNotNull(album);

	}

	/*
	Test getter and setter
	 */

	@Test
	void testGetSet() {
		album = new Album("test", "test", 15, 15000, "fusses.com");
		Assertions.assertEquals(15000 , album.getLength());
		album.setLength(10000);
		Assertions.assertEquals(10000 , album.getLength());
	}
}
