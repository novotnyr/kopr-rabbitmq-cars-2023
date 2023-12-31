package sk.upjs.ics.kopr.consumer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.core.ExchangeTypes.TOPIC
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import sk.upjs.ics.kopr.Car
import kotlin.random.Random

@SpringBootApplication
class ConsumerApplication {
    private val logger: Logger = LoggerFactory.getLogger(ConsumerApplication::class.java)

    @RabbitListener(queues = [ALL_CARS_QUEUE])
    fun consumeAnyCar(car: Car) {
        logger.info("Consuming car from '$ALL_CARS_QUEUE' queue: {}", car)
    }

    @RabbitListener(
        bindings = [QueueBinding(
            value = Queue(COMPACT_CARS_QUEUE),
            exchange = Exchange("car", type = TOPIC),
            key = [COMPACT_CARS_ROUTING_KEY]
        )]
    )
    fun consumeCompactCar(car: Car) {
        logger.info("Consuming compact car from '{}' queue: {}", COMPACT_CARS_QUEUE, car)
    }

    @RabbitListener(queues = [SMALL_CARS_QUEUE])
    fun unreliableSmallCarConsumer(car: Car) {
        if (car.make.contains("Kia")) {
            throw AmqpRejectAndDontRequeueException("Will not process $car, message is dropped")
        }
        if (car.make.contains("Fiat") && Random.nextFloat() < 0.5) {
            throw IllegalStateException("Will not process $car, message is returned to the queue")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}
