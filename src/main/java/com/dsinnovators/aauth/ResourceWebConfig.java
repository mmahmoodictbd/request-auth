package com.dsinnovators.aauth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.dsinnovators.aauth.controller" })
public class ResourceWebConfig extends WebMvcConfigurerAdapter {
}
