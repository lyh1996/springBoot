package com.good;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages= {"com.good.dao"})
public class GoodApplication {

	public static void main(String[] args) {

		SpringApplication.run(GoodApplication.class, args);
	}
}
