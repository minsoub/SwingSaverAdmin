package kr.co.swingsaver.config;

import java.util.ArrayList;
import java.util.Collections;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.val;


@OpenAPIDefinition(
        info = @Info(
                title = "SwingSaver Admin Api",
                version = "v1",
                description = "SwingSaver Admin api",
                contact = @Contact(name = "TECH",
                        email = "minsoub@gmail.com",
                        url = "https://github.com/minsoub"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "local-server"),
                @Server(url = "https://www.swingsaver.co.kr:7000/api", description = "remote-server"),
        },
        security = {
                @SecurityRequirement(name = "bearerToken"),
                @SecurityRequirement(name = "x-api-Key"),
        }
)

@SecuritySchemes({
        @SecurityScheme(
                name = "bearerToken",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        ),
        @SecurityScheme(
                name = "x-api-Key",
                type = SecuritySchemeType.APIKEY,
                in = SecuritySchemeIn.HEADER,
                paramName = "x-api-Key"
        )
})
@Configuration
public class OpenApiConfig {
    String[] mobilePaths = {"/api/mobile/**", "/api/lab/**"};
    String[] loginPaths = {"/api/auth/**"};
    String[] allPaths = {"/api/**"};
//    @Bean
//    public GroupedOpenApi mobileOpenApi() {
//
//        return GroupedOpenApi.builder().group("loggined-API").pathsToMatch(mobilePaths).build();
//    }

    @Bean
    public GroupedOpenApi authOpenApi() {

        return GroupedOpenApi.builder().group("auth-API").pathsToMatch(allPaths).build();
    }

//    @Bean
//    public GroupedOpenApi adminOpenApi() {
//        val list = new ArrayList<String>();
//        Collections.addAll(list, mobilePaths);
//        Collections.addAll(list, loginPaths);
//        val excludeList = list.toArray(new String[list.size()]);
//        return GroupedOpenApi.builder().group("admin-API").pathsToExclude(excludeList).build();
//    }
//
//    @Bean
//    public GroupedOpenApi allOpenApi() {
//        return GroupedOpenApi.builder().group("all-API").pathsToMatch(allPaths).build();
//    }

}

//@OpenAPIDefinition(
//	    info = @Info(title = "스윙세이버 Admin API 명세서",
//	        description = "API 명세서",
//	        version = "v1",
//	        contact = @Contact(name = "JMS", email = "minsoub@gmail.com"),
//	        license = @License(name = "Apache 2.0",
//	            url = "http://www.apache.org/licenses/LICENSE-2.0.html")
//	    )
//	)
//@Configuration
////@EnableSwagger2
//public class OpenApiConfig {
//	
////	@Bean
////	public GroupedOpenApi customGameOpenApi() {
////	    String[] paths = {"/api/v1/admin/**"};
////	    return GroupedOpenApi.builder().group("Admin 처리 API").pathsToMatch(paths)
////	        .build();
////	}
//	 
//	@Bean
//	public OpenAPI swingSaverOpenAPI() {
//	      return new OpenAPI()
//	              .info(new io.swagger.v3.oas.models.info.Info().title("스윙세이버 API Spec")
//	              .description("스윙세이버 Admin application")
//	              .version("v0.0.1")
//	              .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org")))
//	              .externalDocs(new ExternalDocumentation()
//	              .description("스윙세이버 Homepage")
//	              .url("https://www.swingsaver.co.kr"));
//	}
//    String[] allPaths = {"/api/**"};
//    @Bean
//    public GroupedOpenApi mobileOpenApi() {
//
//        return GroupedOpenApi.builder().group("loggined-API").pathsToMatch(allPaths).build();
//    }
//	 // @Bean
//	  // public GroupedOpenApi adminApi() {
//	  //     return GroupedOpenApi.builder()
//	  //             .group("springshop-admin")
//	  //             .pathsToMatch("/admin/**")
//	  //             .build();
//	  // }
//	
////	@Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
//	
//}
