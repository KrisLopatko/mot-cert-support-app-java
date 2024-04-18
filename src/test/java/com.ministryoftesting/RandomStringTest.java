package com.ministryoftesting;

import com.ministryoftesting.service.RandomString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomStringTest {

	@Test
	public void testRandomStringCanBeCreated() {
		RandomString randomString = new RandomString(10);

		String createdString = randomString.nextString();

		System.out.println(createdString);

		assertEquals(10, createdString.length());

	}

}
