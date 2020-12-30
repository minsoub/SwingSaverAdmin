package kr.co.swingsaver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;

//@OpenAPIDefinition(
//	    info = @Info(title = "스윙세이버 Admin API 명세서",
//	        description = "API 명세서",
//	        version = "v1",
//	        contact = @Contact(name = "JMS", email = "minsoub@gmail.com"),
//	        license = @License(name = "Apache 2.0",
//	            url = "http://www.apache.org/licenses/LICENSE-2.0.html")
//	    )
//	)
@Configuration
//@EnableSwagger2
public class OpenApiConfig {
	
//	@Bean
//	public GroupedOpenApi customGameOpenApi() {
//	    String[] paths = {"/api/v1/admin/**"};
//	    return GroupedOpenApi.builder().group("Admin 처리 API").pathsToMatch(paths)
//	        .build();
//	}
	 
	@Bean
	public OpenAPI swingSaverOpenAPI() {
	      return new OpenAPI()
	              .info(new io.swagger.v3.oas.models.info.Info().title("스윙세이버 API Spec")
	              .description("스윙세이버 Admin application")
	              .version("v0.0.1")
	              .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("스윙세이버 Homepage")
	              .url("https://www.swingsaver.co.kr"));
	}
	
	 // @Bean
	  // public GroupedOpenApi adminApi() {
	  //     return GroupedOpenApi.builder()
	  //             .group("springshop-admin")
	  //             .pathsToMatch("/admin/**")
	  //             .build();
	  // }
	
//	@Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
	
}
