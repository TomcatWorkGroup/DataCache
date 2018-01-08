package com.itdreamworks.datacacheoutput;

import com.itdreamworks.datacacheoutput.listener.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.itdreamworks.datacacheoutput")
@EnableCaching
@EnableFeignClients
public class DatacacheoutputApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DatacacheoutputApplication.class);
		application.addListeners(new ApplicationStartup());
		application.run(args);
	}
}