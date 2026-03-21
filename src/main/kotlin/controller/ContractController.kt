package ru.job4j.controller

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.job4j.domain.Contract
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class ContractController {
    private val mem = ConcurrentHashMap<String, CopyOnWriteArrayList<Contract>>()

    suspend fun createPersonalAgreementContract(call: RoutingCall) {
        val contract = call.receive<Contract>()

        if (!isValidUuid(contract.clientId)) {
            call.respond(HttpStatusCode.BadRequest, "Client id must be valid UUID")
        }

        val created = Contract(
            id = UUID.randomUUID().toString(),
            clientId = contract.clientId
        )

        val contracts = mem.getOrPut(contract.clientId) { CopyOnWriteArrayList() }

        contracts.add(created)

        call.respond(created)
    }

    suspend fun getContracts(call: RoutingCall) {
        val clientId = call.parameters["clientId"]

        if (clientId == null) {
            call.respond(HttpStatusCode.BadRequest, "Client id is required")
            return
        }

        if (!isValidUuid(clientId)) {
            call.respond(HttpStatusCode.BadRequest, "Client id must be valid uuid")
            return
        }

        val contracts = mem[clientId].orEmpty()
        call.respond(contracts)
    }

    private fun isValidUuid(value: String): Boolean {
        return try {
            UUID.fromString(value)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}
