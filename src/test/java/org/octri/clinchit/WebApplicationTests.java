package org.octri.clinchit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = { "classpath:application.properties", "classpath:test-application.properties" })
@SpringBootTest
public class WebApplicationTests {

	@Test
	public void canary() {
		assertThat("Canary test with full application context", true, is(equalTo(true)));
	}

}
