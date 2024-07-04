package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.dinamic.CalculusResult
import com.bruno.customerprocessor.model.CalculusRead
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class CalculusResultStep {

    @Bean
    fun calculusResultsStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("calculusResultsReader") calculusResultReader: ItemReader<CalculusRead>,
        @Qualifier("calculusResultProcessor") calculusResultProcessor: ItemProcessor<CalculusRead, CalculusResult>,
        @Qualifier("calculusResultsWriter") calculusResultWriter: ItemWriter<CalculusResult>
    ): Step {
        return StepBuilder("Calculus Result Step", jobRepository)
            .chunk<CalculusRead, CalculusResult>(1_000, transactionManager)
            .reader(calculusResultReader)
            .processor(calculusResultProcessor)
            .writer(calculusResultWriter)
            .build()
    }

}