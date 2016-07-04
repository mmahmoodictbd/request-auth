package com.dsinnovators.aauth;

import javafx.application.Application;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.dsinnovators.aauth.filter.OTPFilter;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	public FilterRegistrationBean optFilterRegistration() {

		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setName("OTPFilter");
		filter.setFilter(optFilter());
		filter.addUrlPatterns("/*");

		return filter;
	}

	@Bean(name = "optFilter")
	public Filter optFilter() {
		return new OTPFilter();
	}
}
