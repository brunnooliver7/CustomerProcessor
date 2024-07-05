package com.bruno.customerprocessor.entity.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "risk")
data class Risk(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "risk", nullable = false)
    val risk: String,

    @Column(precision = 16, scale = 2)
    val minRevenue: BigDecimal = BigDecimal.ZERO,

    @Column(precision = 16, scale = 2)
    val maxRevenue: BigDecimal = BigDecimal.ZERO

) {
    constructor(id: Long): this(0, "", BigDecimal.ZERO, BigDecimal.ZERO) {
        this.id = id
    }
}