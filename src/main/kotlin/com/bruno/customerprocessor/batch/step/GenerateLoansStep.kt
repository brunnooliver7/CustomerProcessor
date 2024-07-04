package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.batch.processor.GenerateLoansProcessor
import com.bruno.customerprocessor.entity.external.Loan
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class GenerateLoansStep {

    @Bean
    fun generateLoanStepBean(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("loanReader") loanReader: ItemReader<Long>,
        @Qualifier("generateLoansProcessor") loansProcessor: GenerateLoansProcessor,
        @Qualifier("loanWriter") loanWriter: ItemWriter<List<Loan>>
    ): Step {
        return StepBuilder("Loan Rule Step", jobRepository)
            .chunk<Long, List<Loan>>(10_000, transactionManager)
            .reader(loanReader)
            .processor(loansProcessor)
            .writer(loanWriter)
            .build()
    }

}