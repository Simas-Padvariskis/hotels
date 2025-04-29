package com.hotels.hotels;

import com.hotels.hotels.security.services.UserDetailsImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class HotelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsApplication.class, args);
	}

}
