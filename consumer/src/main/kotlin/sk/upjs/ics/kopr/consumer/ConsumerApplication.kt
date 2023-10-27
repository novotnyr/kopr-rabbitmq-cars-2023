package sk.upjs.ics.kopr.consumer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import sk.upjs.ics.kopr.Car

@SpringBootApplication
class ConsumerApplication {
    private val logger: Logger = LoggerFactory.getLogger(ConsumerApplication::class.java)

    @RabbitListener(queues = [ALL_CARS_QUEUE])
    fun consumeAnyCar(car: Car) {
        logger.info("Consuming car from '$ALL_CARS_QUEUE' queue: {}", car)
    }
}

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}
