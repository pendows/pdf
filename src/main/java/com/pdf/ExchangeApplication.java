package com.pdf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.pdf.dao")
public class ExchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

}
