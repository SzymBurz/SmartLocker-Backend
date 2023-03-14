package com.smartlockerbackend;

import com.smartlockerbackend.generator.LockerGenerator;
import com.smartlockerbackend.generator.ReservationGenerator;
import com.smartlockerbackend.repository.LockerRepo;
import com.smartlockerbackend.repository.ReservationRepo;
import com.smartlockerbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartLockerBackend {

	@Autowired
    ReservationGenerator reservationGenerator;
	@Autowired
    LockerGenerator lockerGenerator;
	@Autowired
    LockerRepo lockerRepo;
	@Autowired
    ReservationRepo reservationRepo;
	@Autowired
    UserRepo userRepo;


	public static void main(String[] args) {
		SpringApplication.run(SmartLockerBackend.class, args);
	}

	@Bean
	public CommandLineRunner loadData(){
		return (args) -> {
			lockerRepo.deleteAll();
			reservationRepo.deleteAll();
			userRepo.deleteAll();
			lockerGenerator.generateExampleData();
			reservationGenerator.generateExampleReservationData();;
		};
	}

}
