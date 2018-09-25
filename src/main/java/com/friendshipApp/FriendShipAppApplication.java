package com.friendshipApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.friendshipApp" })
//@ComponentScan(scanBasePackages = { "com.friendshipApp.service" , "com.friendshipApp.controller", "com.friendshipApp.dao"})
public class FriendShipAppApplication extends SpringBootServletInitializer {

	private static Class<FriendShipAppApplication> applicationStarter =
			FriendShipAppApplication.class;
	
	public static void main(String[] args) {
		SpringApplication.run(FriendShipAppApplication.class, args);
	}
	
	/**
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(applicationStarter);
    }
}
