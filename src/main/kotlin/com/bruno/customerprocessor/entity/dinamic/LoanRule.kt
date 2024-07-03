package com.bruno.customerprocessor.entity.dinamic

import jakarta.persistence.*

@Entity
@Table(name = "loan_rule")
data class LoanRule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val loanId: Long,

    val calculusRuleId: Long
)
