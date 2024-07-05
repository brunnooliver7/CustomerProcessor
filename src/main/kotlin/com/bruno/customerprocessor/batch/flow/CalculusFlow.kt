package com.bruno.customerprocessor.batch.flow

import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.FlowBuilder
import org.springframework.batch.core.job.flow.Flow
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor

@Configuration(value = "CalculusFlowConfig")
class CalculusFlow {

    @Bean
    fun calculusFlowBank1(@Qualifier("calculusStepBank1") calculusStepBank1: Step): Flow {
        return FlowBuilder<Flow>("Calculus Flow - Bank 1")
            .start(calculusStepBank1)
            .build()
    }

    @Bean
    fun calculusFlowBank2(@Qualifier("calculusStepBank2") calculusStepBank2: Step): Flow {
        return FlowBuilder<Flow>("Calculus Flow - Bank 2")
            .start(calculusStepBank2)
            .build()
    }

    @Bean
    fun calculusFlowBank3(@Qualifier("calculusStepBank3") calculusStepBank3: Step): Flow {
        return FlowBuilder<Flow>("Calculus Flow - Bank 3")
            .start(calculusStepBank3)
            .build()
    }

    @Bean
    fun calculusFlow(
        @Qualifier("calculusFlowBank1") calculusFlowBank1: Flow,
        @Qualifier("calculusFlowBank2") calculusFlowBank2: Flow,
        @Qualifier("calculusFlowBank3") calculusFlowBank3: Flow,
    ): Flow {
        return FlowBuilder<Flow>("Calculus Flow")
            .split(SimpleAsyncTaskExecutor())
            .add(calculusFlowBank1, calculusFlowBank2, calculusFlowBank3)
            .build()
    }

}