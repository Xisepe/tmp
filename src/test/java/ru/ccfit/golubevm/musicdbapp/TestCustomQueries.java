package ru.ccfit.golubevm.musicdbapp;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.ccfit.golubevm.musicdbapp.core.repository.UserRepository;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestCustomQueries {
	@Autowired
	PostgreSQLContainer<?> container;
	@Autowired
	UserRepository repository;

	@BeforeAll
	void setup(){
		container.start();
	}

	@AfterAll
	void shutdown() {
		container.stop();
	}

	@BeforeEach
	void setData() {
		repository.deleteAll();
	}

	@Test
	void startupTest(){

	}

}
