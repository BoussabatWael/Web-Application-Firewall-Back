package com.gcs.firewall.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.gcs.firewall.interceptors.BasicAuthInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new BasicAuthInterceptor());
	}


}
