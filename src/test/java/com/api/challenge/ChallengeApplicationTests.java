package com.api.challenge;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Ignorado para no cargar el ApplicationContext sin la BD")
class ChallengeApplicationTests {

	@Test
	void contextLoads() {
	}
}
