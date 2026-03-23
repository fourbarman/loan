package ru.job4j.controller

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.job4j.domain.Contract
import ru.job4j.domain.ContractSignRequest
import ru.job4j.filter.ContractFilter
import ru.job4j.service.ContractService
import java.util.*

class ContractController(
    private val contractService: ContractService
) {
    suspend fun createPersonalAgreementContract(call: RoutingCall) {
        val req = call.receive<Contract>()

        if (!isValidUuid(req.clientId)) {
            call.respond(HttpStatusCode.BadRequest, "Client id must be valid UUID")
        }

        val resp = contractService.createPersonalAgreementContract(req)

        call.respond(resp)
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

        val resp = contractService.getContracts(ContractFilter( clientId = clientId))
        call.respond(resp)
    }

    suspend fun signPersonalAgreementContract(call: RoutingCall) {
        val req = call.receive<ContractSignRequest>()

        if (!isValidUuid(req.id)) {
            call.respond(HttpStatusCode.BadRequest, "Contract id must be valid UUID")
            return
        }

        val resp = contractService.signPersonalAgreementContract(req.id)

        if (resp == null) {
            call.respond(HttpStatusCode.NotFound, "Contract not found")
            return
        }

        call.respond(resp)
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
