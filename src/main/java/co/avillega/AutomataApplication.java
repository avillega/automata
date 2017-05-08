package co.avillega;

import co.avillega.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AutomataApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AutomataApplication.class);
    @Value("${arduino}")
    String val;
    @Autowired
    private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AutomataApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {

        logger.info(val);
        userService.createUser("icesi", "icesi");
        userService.createUser("test", "test");
    }
}
