package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "revenue")
data class Revenue(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val customerId: Long,

    val bankAccountId: Long,

    @Column(nullable = false, precision = 10, scale = 2)
    val amount: BigDecimal = BigDecimal.ZERO

)