package ru.job4j.domain

import kotlinx.serialization.Serializable

@Serializable
data class Contract(
    val id: String,
    val clientId: String
)