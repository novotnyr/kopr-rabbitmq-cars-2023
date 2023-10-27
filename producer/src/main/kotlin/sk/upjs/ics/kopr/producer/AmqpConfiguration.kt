package sk.upjs.ics.kopr.producer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpConfiguration {
    @Bean
    fun jsonMessageConverter(objectMapper: ObjectMapper)
        = Jackson2JsonMessageConverter(objectMapper)
}