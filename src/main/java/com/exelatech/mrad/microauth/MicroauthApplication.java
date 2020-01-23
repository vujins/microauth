package com.exelatech.mrad.microauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.exelatech.mrad" })
public class MicroauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroauthApplication.class, args);
	}

}
