package ru.job4j.service

import ru.job4j.domain.Contract
import ru.job4j.filter.ContractFilter
import ru.job4j.repository.ContractRepository

class ContractServiceImpl(private val contractRepository: ContractRepository) : ContractService {

    override fun createPersonalAgreementContract(req: Contract): Contract {
        return contractRepository.createContract(req)
    }

    override fun getContracts(filter: ContractFilter): List<Contract> {
        return contractRepository.getContracts(filter)
    }

    override fun signPersonalAgreementContract(req: String): Contract? {
        return contractRepository.signContract(req)
    }
}
