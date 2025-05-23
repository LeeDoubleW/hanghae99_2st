package kr.hhplus.be.server.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
        		.info(new Info()
                .title("e-커머스 Mock API")
                .description("Swagger Api Documentation")
                .version("v1.0.0"));
    }
}