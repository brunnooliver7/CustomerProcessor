package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.external.Revenue
import com.bruno.customerprocessor.model.RevenueData
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class GenerateRevenueDataStep {

    @Bean
    fun generateRevenueDataStepBean(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("revenueReader") revenueReader: ItemReader<RevenueData>,
        @Qualifier("revenueWriter") revenueWriter: JdbcBatchItemWriter<Revenue>
    ): Step {
        return StepBuilder("Generate Revenue Data Step", jobRepository)
            .chunk<RevenueData, Revenue>(20_000, transactionManager)
            .reader(revenueReader)
            .writer(revenueWriter)
            .build()
    }

}