package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.dinamic.ExternalData
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class GatherExternalDataStep {

    @Bean
    fun gatherExtDataStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        gatherExternalDataReader: ItemReader<ExternalData>,
        gatherExternalDataWriter: JdbcBatchItemWriter<ExternalData>
    ): Step {
        return StepBuilder("Gather External Data Step", jobRepository)
            .chunk<ExternalData, ExternalData>(1_000, transactionManager)
            .reader(gatherExternalDataReader)
            .writer(gatherExternalDataWriter)
            .build()
    }

}