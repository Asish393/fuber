package com.taxi.fuber.config;

import com.google.common.collect.Multimap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

import lombok.Data;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfig.ApiProperties.class)
public class SwaggerConfig {

	@Bean
	public static ApiProperties apiProperties() {
		return new ApiProperties();
	}

	@Bean
	public static Docket swaggerSpringfoxDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.taxi.fuber"))
				.build()
				.pathMapping("/")
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.directModelSubstitute(Instant.class, String.class)
				.directModelSubstitute(ZonedDateTime.class, String.class)
				.directModelSubstitute(LocalDate.class, String.class)
				.directModelSubstitute(LocalDateTime.class, String.class)
				.directModelSubstitute(Multimap.class, Map.class)
				.ignoredParameterTypes(Pageable.class, Sort.class)
				.apiInfo(buildApiInfo());
	}

	private static ApiInfo buildApiInfo() {
		final ApiProperties apiProperties = apiProperties();
		return new ApiInfoBuilder()
				.title(apiProperties.getTitle())
				.description(apiProperties.getDescription())
				.version(apiProperties.getVersion())
				.termsOfServiceUrl(apiProperties.getTermsOfServiceUrl())
				.contact(new Contact(apiProperties.getName(), apiProperties.getUrl(), apiProperties.getEmail()))
				.license(apiProperties.getLicense())
				.licenseUrl(apiProperties.getLicenseUrl())
				.build();
	}

	@ConfigurationProperties(prefix = "api.info")
	@Data
	public static class ApiProperties {
		private String title;
		private String description;
		private String version;
		private String termsOfServiceUrl;
		private String email;
		private String name;
		private String url;
		private String license;
		private String licenseUrl;
	}
}