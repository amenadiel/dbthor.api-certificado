package com.dbthor.domain.certificado.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Crea la configuracion de la API Swagger
 *
 * Created by csattler on 11/04/2017.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Value("${swagger.info.groupName}")     private String swaggerGroupName;
    @Value("${swagger.info.title}")         private String swaggerInfoTitle;
    @Value("${swagger.info.description}")   private String swaggerInfoDescription;
    @Value("${swagger.info.version}")       private String swaggerInfoVersion;
    @Value("${swagger.info.license}")       private String swaggerInfoLicense;
    @Value("${swagger.pathMapping}")        private String swaggerPathMapping;
    @Value("${swagger.ui.webpage}")         private String swaggerUIWebpage;


    /**
     * Crea la configuracion
     *
     * @return Swagger Docket
     */
    @Bean
    public Docket documentoApi(){
        List<ResponseMessage> responseListPost = new ArrayList<>();
        responseListPost.add(new ResponseMessageBuilder()
                .code(201)
                .message("Creado")
                .build());
        responseListPost.add(new ResponseMessageBuilder()
                .code(400)
                .message("Solicitud incorrecta")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseListPost.add(new ResponseMessageBuilder()
                .code(401)
                .message("Sin Autorizacion")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseListPost.add(new ResponseMessageBuilder()
                .code(422)
                .message("Error controlado")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseListPost.add(new ResponseMessageBuilder()
                .code(500)
                .message("Error interno no controlado")
                //.responseModel(new ModelRef("Error"))
                .build());

        List<ResponseMessage> responseListGet = new ArrayList<>();
        responseListGet.add(new ResponseMessageBuilder()
                .code(200)
                .message("Aceptado")
                .build());
        responseListGet.add(new ResponseMessageBuilder()
                .code(400)
                .message("Solicitud incorrecta")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseListGet.add(new ResponseMessageBuilder()
                .code(401)
                .message("Sin Autorizacion")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseListGet.add(new ResponseMessageBuilder()
                .code(422)
                .message("Error controlado")
                //.responseModel(new ModelRef("Error"))
                .build());
        responseListGet.add(new ResponseMessageBuilder()
                .code(500)
                .message("Error interno no controlado")
                //.responseModel(new ModelRef("Error"))
                .build());

       return new Docket(DocumentationType.SWAGGER_2)
               .groupName(swaggerGroupName)
               .apiInfo(apiInfo())
               .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                    .paths(PathSelectors.any())
                    .build()
               .pathMapping(swaggerPathMapping)
               .genericModelSubstitutes(ResponseEntity.class)
               .useDefaultResponseMessages(false)
               .globalResponseMessage(RequestMethod.GET,responseListGet)
               .globalResponseMessage(RequestMethod.POST,responseListPost);
    }

    /**
     * Genera la Info de al API
     *
     * @return Swagger API Info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerInfoTitle)
                .description(swaggerInfoDescription)
                .version(swaggerInfoVersion)
                .license(swaggerInfoLicense)
                .build();
    }

    /**
     * Habilitacion de UI de Swagger
     *
     * @param registry  ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(swaggerUIWebpage)
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
