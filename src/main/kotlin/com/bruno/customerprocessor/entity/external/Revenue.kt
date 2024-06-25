package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "revenue")
data class Revenue(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer? = null,

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    val bankAccount: BankAccount? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    val amount: BigDecimal = BigDecimal.ZERO,

) {
    constructor() : this(0,
        null,
        null,
        BigDecimal.ZERO,
    )
}