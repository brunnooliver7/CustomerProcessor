package com.bruno.customerprocessor.entity.reference

import jakarta.persistence.*

@Entity
@Table(name = "[rule]")
data class Rule(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "percentage_id", nullable = false)
    var percentage: Percentage? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debt_delay_id", nullable = false)
    var debtDelay: DebtDelay? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risk_id", nullable = false)
    var risk: Risk? = null

    ) {
    constructor(percentageId: Long, debtDelayId: Long, riskId: Long) :
            this(0, null, null, null) {
        this.percentage = Percentage(id = percentageId)
        this.debtDelay = DebtDelay(id = debtDelayId)
        this.risk = Risk(id = riskId)
    }
}