package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "loan")
data class Loan(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val customerId: Long,

    @Column(precision = 10, scale = 2)
    val loan: BigDecimal = BigDecimal.ZERO,

    @Column(precision = 10, scale = 2)
    val payed: BigDecimal = BigDecimal.ZERO,

    @Column(precision = 10, scale = 2)
    val debt: BigDecimal = BigDecimal.ZERO,

    val delay: Int? = null
)