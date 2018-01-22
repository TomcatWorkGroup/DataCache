package com.itdreamworks.datacacheoutput;

import com.itdreamworks.datacacheoutput.listener.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.itdreamworks.datacacheoutput")
@EnableCaching
@EnableFeignClients
public class DatacacheoutputApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DatacacheoutputApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DatacacheoutputApplication.class);
		application.addListeners(new ApplicationStartup());
		application.run(args);
	}
}