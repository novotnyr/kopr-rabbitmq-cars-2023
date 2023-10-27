package sk.upjs.ics.kopr.producer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import sk.upjs.ics.kopr.CarRandomizer

@SpringBootApplication
@EnableScheduling
class ProducerApplication(val amqpTemplate: AmqpTemplate) {
    private val logger: Logger = LoggerFactory.getLogger(ProducerApplication::class.java)

    private val carRandomizer = CarRandomizer()

    @Scheduled(fixedDelay = 2000)
    fun produceCar() {
        val car = carRandomizer.randomCar()
        logger.info("Producing a car: {}", car)
        amqpTemplate.convertAndSend(CAR_EXCHANGE, "car.${car.segment}", car)
    }
}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
