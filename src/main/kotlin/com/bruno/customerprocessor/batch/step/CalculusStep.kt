package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.dinamic.Calculus
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

@Configuration(value = "CalculusStepConfig")
class CalculusStep {

//    @Bean
//    fun calculusStep(
//        jobRepository: JobRepository,
//        transactionManager: PlatformTransactionManager,
//        @Qualifier("calculusReader") calculusReader: ItemReader<CalculusRead>,
//        @Qualifier("calculusProcessor") calculusProcessor: ItemProcessor<CalculusRead, Calculus>,
//        @Qualifier("calculusWriter") calculusWriter: ItemWriter<Calculus>
//    ): Step {
//        return StepBuilder("Calculus Step", jobRepository)
//            .chunk<CalculusRead, Calculus>(1_000, transactionManager)
//            .reader(calculusReader)
//            .processor(calculusProcessor)
//            .writer(calculusWriter)
//            .build()
//    }

    @Bean
    fun calculusStepBank1(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("calculusReaderBank1") calculusReader: ItemReader<CalculusRead>,
        @Qualifier("calculusProcessor") calculusProcessor: ItemProcessor<CalculusRead, Calculus>,
        @Qualifier("calculusWriter") calculusWriter: ItemWriter<Calculus>
    ): Step {
        return StepBuilder("Calculus Step - Bank 1", jobRepository)
            .chunk<CalculusRead, Calculus>(1_000, transactionManager)
            .reader(calculusReader)
            .processor(calculusProcessor)
            .writer(calculusWriter)
            .build()
    }

    @Bean
    fun calculusStepBank2(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("calculusReaderBank2") calculusReader: ItemReader<CalculusRead>,
        @Qualifier("calculusProcessor") calculusProcessor: ItemProcessor<CalculusRead, Calculus>,
        @Qualifier("calculusWriter") calculusWriter: ItemWriter<Calculus>
    ): Step {
        return StepBuilder("Calculus Step - Bank 2", jobRepository)
            .chunk<CalculusRead, Calculus>(1_000, transactionManager)
            .reader(calculusReader)
            .processor(calculusProcessor)
            .writer(calculusWriter)
            .build()
    }

    @Bean
    fun calculusStepBank3(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("calculusReaderBank3") calculusReader: ItemReader<CalculusRead>,
        @Qualifier("calculusProcessor") calculusProcessor: ItemProcessor<CalculusRead, Calculus>,
        @Qualifier("calculusWriter") calculusWriter: ItemWriter<Calculus>
    ): Step {
        return StepBuilder("Calculus Step - Bank 3", jobRepository)
            .chunk<CalculusRead, Calculus>(1_000, transactionManager)
            .reader(calculusReader)
            .processor(calculusProcessor)
            .writer(calculusWriter)
            .build()
    }

}