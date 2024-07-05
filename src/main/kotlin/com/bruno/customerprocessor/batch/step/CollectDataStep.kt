package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.dinamic.ExternalData
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration(value = "CollectDataStepConfig")
class CollectDataStep {

    @Bean
    fun collectDataStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("collectDataReader") collectDataReader: ItemReader<ExternalData>,
        @Qualifier("collectDataWriter") collectDataWriter: JdbcBatchItemWriter<ExternalData>
    ): Step {
        return StepBuilder("Collect Data Step", jobRepository)
            .chunk<ExternalData, ExternalData>(1_000, transactionManager)
            .reader(collectDataReader)
            .writer(collectDataWriter)
            .build()
    }

}