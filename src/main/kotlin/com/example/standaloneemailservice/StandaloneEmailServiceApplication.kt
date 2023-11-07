package com.example.standaloneemailservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StandaloneEmailServiceApplication

fun main(args: Array<String>) {
	runApplication<StandaloneEmailServiceApplication>(*args)
}
