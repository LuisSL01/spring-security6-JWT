package com.cursos.springsecuritycourse;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringSecurityCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityCourseApplication.class, args);
	}

	/*
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner createPasswords(){
		return args -> {
			System.out.println(passwordEncoder.encode("clave123"));
			System.out.println(passwordEncoder.encode("clave456"));


		};
	}
	*/

}
