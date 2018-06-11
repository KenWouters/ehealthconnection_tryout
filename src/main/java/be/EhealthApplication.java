package be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "be")
@EntityScan(basePackages = "be.vsb.emma")
public class EhealthApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(EhealthApplication.class);
    }

    public static void main(final String[] args) {
        SpringApplication.run(EhealthApplication.class, args);
    }
}