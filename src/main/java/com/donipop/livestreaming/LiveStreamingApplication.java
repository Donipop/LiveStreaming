package com.donipop.livestreaming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@MapperScan(basePackages = "com.donipop.livestreaming")
public class LiveStreamingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiveStreamingApplication.class, args);
    }

}
