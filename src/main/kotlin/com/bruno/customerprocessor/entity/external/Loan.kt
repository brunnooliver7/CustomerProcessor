package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "loan")
data class Loan(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer? = null,

    @Column(precision = 10, scale = 2)
    val loan: BigDecimal = BigDecimal.ZERO,

    @Column(precision = 10, scale = 2)
    val payed: BigDecimal = BigDecimal.ZERO,

    @Column(precision = 10, scale = 2)
    val debt: BigDecimal = BigDecimal.ZERO,

    val delay: Int? = null
) {
    constructor() : this(
        id = 0,
        customer = null,
        loan = BigDecimal.ZERO,
        payed = BigDecimal.ZERO,
        debt = BigDecimal.ZERO,
        delay = null
    )
}
