package ru.job4j.repository

import ru.job4j.domain.Contract
import ru.job4j.filter.ContractFilter
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class ContractRepositoryPgSql : ContractRepository {
    val mem = ConcurrentHashMap<String, Contract>()

    override fun createContract(contract: Contract): Contract {
        val created = contract.copy(
            id = UUID.randomUUID().toString(),
        )
        val ok = mem.putIfAbsent(created.id, created) == null

        require(ok) { "Previous value associated"}

        return created
    }

    override fun getContracts(filter: ContractFilter): List<Contract> {
        return mem.values.filter {
            it.clientId == filter.clientId
        }
    }
}
