package com.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class LaunchConfigServerApplication {
    public static final void main(final String[] args) {
        SpringApplication.run(LaunchConfigServerApplication.class, args);
    }
}
