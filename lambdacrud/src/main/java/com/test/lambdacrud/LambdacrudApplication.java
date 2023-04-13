package com.test.lambdacrud;

import com.test.lambdacrud.entity.User;
import com.test.lambdacrud.repository.UserRepository;
import com.test.lambdacrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Supplier;

@SpringBootApplication
public class LambdacrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(LambdacrudApplication.class, args);
	}

}
