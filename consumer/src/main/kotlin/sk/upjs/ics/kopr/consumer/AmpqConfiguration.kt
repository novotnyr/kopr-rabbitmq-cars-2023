package sk.upjs.ics.kopr.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder.bind
import org.springframework.amqp.core.ExchangeBuilder.topicExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder.durable
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val ALL_CARS_QUEUE = "allCars"
const val COMPACT_CARS_QUEUE = "compactCars"
const val SMALL_CARS_QUEUE = "smallCars"
const val COMPACT_CARS_ROUTING_KEY = "car.C"
const val SMALL_CARS_ROUTING_KEY = "car.A"

@Configuration
class AmpqConfiguration {
    @Bean
    fun jsonMessageConverter(objectMapper: ObjectMapper)
            = Jackson2JsonMessageConverter(objectMapper)

    @Bean
    fun carExchange(): TopicExchange = topicExchange("car").build()

    @Bean
    fun allCarsQueue(): Queue = durable(ALL_CARS_QUEUE).build()

    @Bean
    fun allCarsBinding(): Binding {
        return bind(allCarsQueue()).to(carExchange()).with("car.#")
    }

    @Bean
    fun smallCarsQueue(): Queue = durable(SMALL_CARS_QUEUE).build()

    @Bean
    fun smallCarsBinding(): Binding = bind(smallCarsQueue()).to(carExchange()).with(SMALL_CARS_ROUTING_KEY)
}