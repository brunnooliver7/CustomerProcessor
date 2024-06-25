package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "bank_account")
data class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer? = null,

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    val bank: Bank? = null,

    @Column(name = "account_number", nullable = false, length = 30)
    val accountNumber: String = "",

    @Column(nullable = false, precision = 10, scale = 2)
    val balance: BigDecimal = BigDecimal.ZERO
) {
    constructor() : this(
        0,
        null,
        null,
        "",
        BigDecimal.ZERO
    )
}