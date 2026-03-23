package ru.job4j.domain

import kotlinx.serialization.Serializable

@Serializable
data class Contract(
    val id: String,
    val clientId: String,
    val status: String = ContractStatus.CREATED.name,
    val signedAt: String? = null,
)

@Serializable
data class ContractSignRequest(
    val id: String
)
