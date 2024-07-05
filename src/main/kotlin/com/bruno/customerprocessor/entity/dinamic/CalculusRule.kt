package com.bruno.customerprocessor.entity.dinamic

import jakarta.persistence.*

@Entity
@Table(name = "calculus_rule")
data class CalculusRule(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val loanId: Long,

    val ruleId: Long
)
