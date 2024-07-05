package com.bruno.customerprocessor.entity.reference

import jakarta.persistence.*

@Entity
@Table(name = "debt_delay")
data class DebtDelay(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "min", nullable = false)
    val min: Int = 0,

    @Column(name = "max", nullable = false)
    val max: Int = 0

)