package sk.upjs.ics.kopr.producer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val CAR_EXCHANGE = "car"

@Configuration
class AmqpConfiguration {
    @Bean
    fun jsonMessageConverter(objectMapper: ObjectMapper)
        = Jackson2JsonMessageConverter(objectMapper)

    @Bean
    fun carExchange(): TopicExchange = ExchangeBuilder.topicExchange(CAR_EXCHANGE).build()
}