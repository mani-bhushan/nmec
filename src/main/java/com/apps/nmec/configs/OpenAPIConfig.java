package com.apps.nmec.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:openapi.properties")
public class OpenAPIConfig {

	@Value("${openapi.base.package}")
	private String basePackage;

	@Value("${openapi.title}")
	private String title;

	@Value("${openapi.description}")
	private String description;

	@Value("${openapi.organizationName}")
	private String contactOrganizationName;

	@Value("${openapi.organization.site}")
	private String contactOrganizationSite;

	@Value("${openapi.organization.email}")
	private String contactOrganizationEmail;

	
	  @Bean
	  public OpenAPI springShopOpenAPI() {
		 
	      return new OpenAPI()
	              .info(new Info().title(title)
	              .description(description)
	              .version("1.0.0").contact(new Contact().name(contactOrganizationName).url(contactOrganizationSite).email(contactOrganizationEmail))
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SpringShop Wiki Documentation")
	              .url("https://springshop.wiki.github.org/docs"));
	  }
}
