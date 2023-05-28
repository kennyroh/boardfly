package io.github.kennyroh.boardfly.config

import nz.net.ultraq.thymeleaf.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

@Configuration
class WebConfig : WebMvcConfigurer {

  // Thymeleaf configuration
  @Bean
  fun templateResolver(): SpringResourceTemplateResolver {
    val templateResolver = SpringResourceTemplateResolver()
    templateResolver.prefix = "classpath:/templates/"
    templateResolver.suffix = ".html"
    templateResolver.templateMode = TemplateMode.HTML
    templateResolver.isCacheable = false
    return templateResolver
  }

  @Bean
  fun templateEngine(): SpringTemplateEngine {
    val templateEngine = SpringTemplateEngine()
    templateEngine.setTemplateResolver(templateResolver())
    templateEngine.enableSpringELCompiler = true
    templateEngine.addDialect(LayoutDialect()) // Add the Layout Dialect
    return templateEngine
  }

  @Bean
  fun viewResolver(): ThymeleafViewResolver {
    val viewResolver = ThymeleafViewResolver()
    viewResolver.templateEngine = templateEngine()
    viewResolver.characterEncoding = "UTF-8"
    return viewResolver
  }

  override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
    registry.addResourceHandler("/static/**")
      .addResourceLocations("classpath:/static/")
  }
}
