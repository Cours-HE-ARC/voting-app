package ch.hearc.votingappadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class VotingAppAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingAppAdminApplication.class, args);
	}

}
