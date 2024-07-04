package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "bank_account")
data class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val customerId: Long,

    var bankId: Long,

    @Column(name = "account_number", nullable = false, length = 100)
    val accountNumber: String = "",

    @Column(nullable = false, precision = 10, scale = 2)
    val balance: BigDecimal = BigDecimal.ZERO
)