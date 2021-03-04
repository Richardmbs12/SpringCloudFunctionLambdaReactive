package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class TestApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class);
    }

    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return stringFlux -> stringFlux.map(String::toUpperCase);
    }
}