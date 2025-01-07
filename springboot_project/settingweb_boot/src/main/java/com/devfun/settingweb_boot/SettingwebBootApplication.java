package com.devfun.settingweb_boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.devfun.settingweb_boot.dao")
public class SettingwebBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingwebBootApplication.class, args);
	}

}
