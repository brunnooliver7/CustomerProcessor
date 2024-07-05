package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.external.Revenue
import com.bruno.customerprocessor.model.RevenueRead
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration(value = "GenerateRevenuesStepConfig")
class GenerateRevenuesStep {

    @Bean
    fun generateRevenuesStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("generateRevenuesReader") generateRevenuesReader: ItemReader<RevenueRead>,
        @Qualifier("generateRevenuesWriter") generateRevenuesWriter: JdbcBatchItemWriter<Revenue>
    ): Step {
        return StepBuilder("Generate Revenues Step", jobRepository)
            .chunk<RevenueRead, Revenue>(20_000, transactionManager)
            .reader(generateRevenuesReader)
            .writer(generateRevenuesWriter)
            .build()
    }

}