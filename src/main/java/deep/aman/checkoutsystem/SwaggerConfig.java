package deep.aman.checkoutsystem;

import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("deep.aman.checkoutsystem.controller"))
                .paths(PathSelectors.any())
                .build();
    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "checkout-system",
//                "A simple checkout system for Store Owner to control products and manage user baskets.",
//                "1.0",
//                "Terms of service",
//                new Contact("Aman Middha", "www.amanmiddha.com", "aman@gmail.com"),
//                "License of API", "API license URL", Collections.emptyList());
//    }
}