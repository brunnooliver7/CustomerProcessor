package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.dinamic.LoanRule
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class LoanRuleStep {

    @Bean
    fun loanRulesStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        gatherExternalDataReader: ItemReader<LoanRule>,
        gatherExternalDataWriter: JdbcBatchItemWriter<LoanRule>
    ): Step {
        return StepBuilder("Loan Rule Step", jobRepository)
            .chunk<LoanRule, LoanRule>(1_000, transactionManager)
            .reader(gatherExternalDataReader)
            .writer(gatherExternalDataWriter)
            .build()
    }

}