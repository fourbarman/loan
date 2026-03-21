package ru.job4j

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.job4j.controller.ContractController

//fun Application.configureRouting() {
//    routing {
//        get("/") {
//            call.respondText("Hello World!")
//        }
//    }
//}
fun Application.configureRouting(
    contractController: ContractController
) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        post("/contract/") {
            contractController.createPersonalAgreementContract(call)
        }
        get("/contract/{clientId}") {
            contractController.getContracts(call)
        }
    }
}
