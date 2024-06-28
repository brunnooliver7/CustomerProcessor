package com.bruno.customerprocessor.entity.external

import jakarta.persistence.*

@Entity
@Table(name = "customer")
data class Customer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "first_name", length = 50)
    val firstName: String = "",

    @Column(name = "last_name", length = 50)
    val lastName: String = "",

    val age: Int = 0,

    @Column(name = "ssn", length = 11, unique = true)
    var ssn: String = ""

)