package com.izorai.pfa;

import com.izorai.pfa.module1.security.UserAuth;
import com.izorai.pfa.module1.security.UserAuthRep;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class PfaApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(PfaApplication.class, args);
	}
}


