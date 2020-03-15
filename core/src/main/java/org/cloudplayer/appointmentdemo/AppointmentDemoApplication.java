package org.cloudplayer.appointmentdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.cloudplayer.appointmentdemo.mapper")
public class AppointmentDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentDemoApplication.class, args);
	}

}
