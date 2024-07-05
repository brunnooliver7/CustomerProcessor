package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*

@Entity
@Table(name = "bank")
data class Bank(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "bank_name", nullable = false, length = 100)
    val bankName: String = ""
)