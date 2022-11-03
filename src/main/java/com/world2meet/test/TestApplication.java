package com.world2meet.test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableCaching
@EnableSwagger2
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	//Define all details for app info
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Super Hero Manage API")
				.description("Super Hero Manage API reference for developers, teh error handler is implement using \n"
						+ " The IETF devised RFC 7807 effor, which creates a generalized error-handling schema.\n"
						+ "https://tools.ietf.org/html/rfc7807")
				.termsOfServiceUrl("http://www.web.com/terms")
				.contact(new Contact("Alberto Martin", "", "https://contact.com"))
				.license("plexus License")
				.licenseUrl("https://plexus.biz/es/ada-enterprise-core#contactus")
				.version("1.0")
				.build();
	}

	//main Swagger config definition
	@Bean
	public Docket SuperHeroApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("digitalthinking-spis")
				.apiInfo(apiInfo())
				//set up JWT input
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.world2meet"))
				.paths(PathSelectors.any())
				.build()
				.tags(new Tag("Super Hero API", "All apis relating to super hero service") {});
	}

	//define API key to include the header
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	//configure JWT security with global Autorization Scope

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}


}
