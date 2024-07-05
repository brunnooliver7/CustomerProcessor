package com.bruno.customerprocessor.entity.dinamic

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "calculus")
data class Calculus(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val loanId: Long,
    val debt: BigDecimal,
    val feePercentage: Int,
    val fee: BigDecimal,
    val calculatedDebt: BigDecimal
)
