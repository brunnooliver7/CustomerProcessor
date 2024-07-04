package com.bruno.customerprocessor.model

import java.math.BigDecimal

data class BankAccountData(
    var customerId: Long = 0L,
    var bankId: Long = 0L,
    var accountNumber: String = "",
    var balance: BigDecimal = BigDecimal.ZERO
)
