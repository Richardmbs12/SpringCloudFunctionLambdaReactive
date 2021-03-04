package test;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ResolvableType;

import java.util.function.Function;

import reactor.core.publisher.Mono;

@SpringBootConfiguration
public class TestFunctionalApplication implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(String[] args) {
        FunctionalSpringApplication.run(TestFunctionalApplication.class, args);
    }

    public Function<Mono<String>, Mono<String>> uppercase() {
        return stringFlux -> stringFlux.map(String::toUpperCase);
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        context.registerBean("uppercase", FunctionRegistration.class,
            () -> {
                final ResolvableType type = ResolvableType.forClassWithGenerics(Mono.class, String.class);
                return new FunctionRegistration<>(uppercase())
                    .type(new FunctionType(ResolvableType
                        .forClassWithGenerics(Function.class, type, type).getType()));
            });
    }
}