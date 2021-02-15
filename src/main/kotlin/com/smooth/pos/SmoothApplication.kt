package com.smooth.pos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmoothApplication

fun main(args: Array<String>) {
    runApplication<SmoothApplication>(*args)
}
