package ru.job4j

import io.ktor.server.application.*
import ru.job4j.controller.ContractController

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting(ContractController())
}
