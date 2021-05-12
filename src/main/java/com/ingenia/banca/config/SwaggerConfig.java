package com.ingenia.banca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @ApiOperation -- metodos del controlador
 * @ApiParam -- parametros metodos controlador
 * @ApiModelProperty -- atributos del modelo
 *
 * v3: http://localhost:8080/swagger-ui/
 * v2: http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ingenia.banca"))
                .build().apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("AWESOME API",
                "description",
                "1.0",
                "",
                new Contact("Adrian Nuñez Muñoz","","adriuma92@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }


}
