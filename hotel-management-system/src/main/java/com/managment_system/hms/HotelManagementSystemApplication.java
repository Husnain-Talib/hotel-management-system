package com.managment_system.hms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagementSystemApplication {

    private static final Logger log = LoggerFactory.getLogger(HotelManagementSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);
        log.info("Application Starts");
	}

}
