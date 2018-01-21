package com.mabanque.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.mabanque.MaBanqueApplication;

public class WebInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(MaBanqueApplication.class); // cette servlet sera demarrée en 1er  et notre application sera demarée en 1er. 
	}
}
