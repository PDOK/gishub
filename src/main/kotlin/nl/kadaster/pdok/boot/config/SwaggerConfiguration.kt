package nl.kadaster.pdok.boot.config

import nl.kadaster.pdok.boot.controller.RouteConstants.BASE
import nl.kadaster.pdok.boot.controller.RouteConstants.MOUNT
import nl.kadaster.pdok.boot.controller.RouteConstants.VERSION

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.web.bind.annotation.RequestMapping


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    companion object {
        private val LOG = LoggerFactory.getLogger(SwaggerConfiguration::class.java)
    }

    init {
        LOG.info("Loading SwaggerConfiguration...")
    }


    @Bean
    fun gisHubApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(
                        RequestHandlerSelectors
                        .basePackage("nl.kadaster.pdok.boot.controller"))
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Kadaster")
                .description("GisHub kadaster")
                .termsOfServiceUrl("TODO")
                .contact(Contact("Gishub", "http://www.gishub.eu",
                        "gishub@gishub.nl"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version(VERSION.toString())
                .build()
    }

    @Controller
     class SwaggerRedirections {

        @RequestMapping(BASE, MOUNT)
        fun home(): String {
            return "redirect:/swagger-ui.html"
        }
    }

}
