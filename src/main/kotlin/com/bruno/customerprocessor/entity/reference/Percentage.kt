package com.bruno.customerprocessor.entity.reference

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "percentage")
data class Percentage(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(precision = 10, scale = 2)
    val percentage: BigDecimal = BigDecimal.ZERO,

)