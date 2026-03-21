package ru.job4j.service

import ru.job4j.domain.Contract
import ru.job4j.filter.ContractFilter

interface ContractService {
    fun createPersonalAgreementContract(req: Contract): Contract
    fun getContracts(filter: ContractFilter): List<Contract>
}
