package sk.upjs.ics.kopr.producer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@SpringBootApplication
@EnableScheduling
class ProducerApplication {
    private val logger: Logger = LoggerFactory.getLogger(ProducerApplication::class.java)

    @Scheduled(fixedDelay = 2000)
    fun produceCar() {
        logger.info("Producing a car")
    }
}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
