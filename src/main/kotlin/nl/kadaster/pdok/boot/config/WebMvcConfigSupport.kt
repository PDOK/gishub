package nl.kadaster.pdok.boot.config

import org.springframework.cache.annotation.CacheConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.format.support.FormattingConversionService
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@EnableWebMvc
@Import(StaticResourceConfig::class)
@Configuration
class WebMvcConfigSupport : WebMvcConfigurationSupport()
