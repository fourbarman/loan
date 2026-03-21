package ru.job4j

import io.ktor.server.application.*
import ru.job4j.controller.ContractController
import ru.job4j.repository.ContractRepositoryPgSql
import ru.job4j.service.ContractServiceImpl

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting(
        ContractController(
            contractService = ContractServiceImpl(
                contractRepository = ContractRepositoryPgSql()
            )
        )
    )
}
