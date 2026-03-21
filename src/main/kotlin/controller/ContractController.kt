package ru.job4j.controller

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
        val contracts = mem[clientId].orEmpty()
        call.respond(contracts)
    }
}