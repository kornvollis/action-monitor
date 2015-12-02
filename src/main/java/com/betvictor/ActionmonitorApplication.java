package com.betvictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@SpringBootApplication
@EnableWebSocket
public class ActionmonitorApplication extends SpringBootServletInitializer
        implements WebSocketConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ActionmonitorApplication.class, args);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/echo").withSockJS();
    }

    @Bean
    public EchoService echoService() {
        return new DefaultEchoService("Did you say \"%s\"?");
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(echoService());
    }
}
