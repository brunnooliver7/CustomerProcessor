package com.bruno.customerprocessor.model

import java.math.BigDecimal

class CalculusRead (
    val calculusResultId: Long?,
    val loanId: Long,
    val debt: BigDecimal,
    val feePercentage: Int,
)