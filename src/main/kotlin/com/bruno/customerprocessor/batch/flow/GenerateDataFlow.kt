package com.bruno.customerprocessor.batch.flow

import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.FlowBuilder
import org.springframework.batch.core.job.flow.Flow
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(value = "GenerateDataFlowConfig")
class GenerateDataFlow {

    @Bean
    fun generateDataFlow(
        @Qualifier("generateCustomerStep") generateCustomerStep: Step,
        @Qualifier("generateBanksStep") generateBanksStep: Step,
        @Qualifier("generateBankAccounts") generateBankAccounts: Step,
        @Qualifier("generateRevenuesLoansFlow") generateRevenuesLoansFlow: Flow
    ): Flow {
        return FlowBuilder<Flow>("Generate Data Flow")
            .start(generateCustomerStep)
            .next(generateBanksStep)
            .next(generateBankAccounts)
            .next(generateRevenuesLoansFlow)
            .build()
    }

}