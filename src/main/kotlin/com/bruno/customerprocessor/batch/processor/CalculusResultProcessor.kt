package com.bruno.customerprocessor.batch.processor

import com.bruno.customerprocessor.entity.dinamic.CalculusResult
import com.bruno.customerprocessor.model.CalculusRead
import org.springframework.batch.item.ItemProcessor
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import java.math.RoundingMode

@Configuration
class CalculusResultProcessor : ItemProcessor<CalculusRead, CalculusResult> {

    override fun process(calculusRead: CalculusRead): CalculusResult? {
        val fee = calculusRead.debt
            .divide(BigDecimal(100))
            .multiply(BigDecimal(calculusRead.feePercentage))
            .setScale(2, RoundingMode.HALF_UP)

        val calculatedDebt = calculusRead.debt + fee

        return CalculusResult(
            id = calculusRead.calculusResultId,
            loanId = calculusRead.loanId,
            debt = calculusRead.debt,
            feePercentage = calculusRead.feePercentage,
            fee = fee,
            calculatedDebt = calculatedDebt
        )
    }

}