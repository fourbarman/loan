package ru.job4j.repository

import ru.job4j.domain.Contract
import ru.job4j.filter.ContractFilter

interface ContractRepository {
    fun createContract(contract: Contract): Contract
    fun getContracts(filter: ContractFilter): List<Contract>
    fun signContract(contractId: String): Contract?
}
