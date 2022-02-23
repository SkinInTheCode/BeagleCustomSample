package br.com.sknc.beagle.bff

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BeagleBffApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun assertContextTrue() {
		assert(true)
	}

	@Test
	fun assertContextString() {
		Assertions.assertThat("teste").isEqualTo("teste")
	}

	@Test
	fun assertContextEmpty() {
		Assertions.assertThat("").isEmpty()
	}

}
