package com.bruno.customerprocessor.batch.tasklet

import com.bruno.customerprocessor.service.DomainDataService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class CreateDomainDataTasklet(
    private val domainDataService: DomainDataService
) : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        domainDataService.generatePercentages()
        domainDataService.generateDebtDelays()
        domainDataService.generateRisks()
        domainDataService.generateRules()

        return RepeatStatus.FINISHED
    }
}