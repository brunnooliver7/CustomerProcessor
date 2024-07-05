package com.bruno.customerprocessor.model

data class CustomerRead (
    var firstName: String = "",
    var lastName: String = "",
    var ssn: String = "",
    var age: Int = 0
)