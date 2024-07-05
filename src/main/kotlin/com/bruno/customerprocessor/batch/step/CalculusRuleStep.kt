package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.dinamic.CalculusRule
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration(value = "CalculusRuleStepConfig")
class CalculusRuleStep {

    @Bean
    fun calculusRuleStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("calculusRuleReader") calculusRuleReader: ItemReader<CalculusRule>,
        @Qualifier("calculusRuleWriter") calculusRuleWriter: JdbcBatchItemWriter<CalculusRule>
    ): Step {
        return StepBuilder("Calculus Rule Step", jobRepository)
            .chunk<CalculusRule, CalculusRule>(1_000, transactionManager)
            .reader(calculusRuleReader)
            .writer(calculusRuleWriter)
            .build()
    }

}