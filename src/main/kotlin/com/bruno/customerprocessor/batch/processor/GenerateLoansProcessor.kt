package com.bruno.customerprocessor.batch.processor

import com.bruno.customerprocessor.entity.external.Loan
import com.bruno.customerprocessor.utils.BigDecimalUtils
import org.springframework.batch.item.ItemProcessor
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import kotlin.random.Random

@Configuration
class GenerateLoansProcessor : ItemProcessor<Long, List<Loan>> {

    override fun process(customerId: Long): List<Loan>? {
        val numberOfLoans = Random.nextInt(0, 4)

        return List(numberOfLoans) {
            val loanAmount = BigDecimalUtils.randomBetween(
                BigDecimal.valueOf(1_000),
                BigDecimal.valueOf(50_000)
            )

            var payedAmount = BigDecimalUtils.randomBetween(
                BigDecimal.valueOf(1_000),
                BigDecimal.valueOf(50_000)
            )

            while (payedAmount > loanAmount) {
                payedAmount = BigDecimalUtils.randomBetween(
                    BigDecimal.valueOf(1_000),
                    BigDecimal.valueOf(50_000)
                )
            }

            Loan(
                customerId = customerId,
                loan = BigDecimalUtils.randomBetween(BigDecimal(0), BigDecimal(50_000)),
                payed = payedAmount,
                debt = loanAmount - payedAmount,
                delay = (0..100).random()
            )
        }
    }

}