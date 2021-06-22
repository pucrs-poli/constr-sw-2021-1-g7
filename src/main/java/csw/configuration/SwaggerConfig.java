package csw.configuration;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules( AlternateTypeRules.newRule(
                        new TypeResolver().resolve(Collection.class, Instant.class),
                        new TypeResolver().resolve(Collection.class, Date.class), Ordered.HIGHEST_PRECEDENCE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("csw.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Students/Subscriptions API")
            .description("Implementation of routes that manage students and subscriptions. Maded by Grupo 7 - CSW 2021/1 - PUCRS")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("", "", "eduardo.dornelles@edu.pucrs.br"))
            .build();
    }

}