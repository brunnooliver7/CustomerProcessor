package com.bruno.customerprocessor.entity.dinamic

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "external_data")
data class ExternalData (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val ssn: String,
    val bankName: String,
    val bankAccountNumber: String,
    val bankAccountBalance: BigDecimal = BigDecimal.ZERO,
    val revenueAmount: BigDecimal = BigDecimal.ZERO,
    val loanId: Long,
    val loan: BigDecimal = BigDecimal.ZERO,
    val payed: BigDecimal = BigDecimal.ZERO,
    val debt: BigDecimal = BigDecimal.ZERO,
    val delay: Int = 0,
    val createdAt: LocalDate = LocalDate.now()
)