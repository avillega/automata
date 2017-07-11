package co.avillega;

import co.avillega.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AutomataApplication implements CommandLineRunner {


    @Autowired
    private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AutomataApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {


        userService.createUser("icesi", "icesi");
        userService.createUser("test", "test");
    }
}
