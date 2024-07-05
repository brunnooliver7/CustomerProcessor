package com.bruno.customerprocessor.batch.flow

import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.FlowBuilder
import org.springframework.batch.core.job.flow.Flow
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor

@Configuration(value = "GenerateRevenuesLoansFlowConfig")
class GenerateRevenuesLoansFlow {

    @Bean
    fun generateRevenuesFlow(@Qualifier("generateRevenuesStep") generateRevenueStep: Step): Flow {
        return FlowBuilder<Flow>("Generate Revenues Flow")
            .start(generateRevenueStep)
            .build()
    }

    @Bean
    fun generateLoansFlow(@Qualifier("generateLoanStep") generateLoanStep: Step): Flow {
        return FlowBuilder<Flow>("Generate Loans Flow")
            .start(generateLoanStep)
            .build()
    }

    @Bean
    fun generateRevenuesLoansFlow(
        @Qualifier("generateRevenuesFlow") generateRevenuesFlow: Flow,
        @Qualifier("generateLoansFlow") generateLoansFlow: Flow
    ): Flow {
        return FlowBuilder<Flow>("Generate Revenues and Loans Flow")
            .split(SimpleAsyncTaskExecutor())
            .add(generateRevenuesFlow, generateLoansFlow)
            .build()
    }

}