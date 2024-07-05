package com.bruno.customerprocessor.batch.tasklet

import com.bruno.customerprocessor.service.ReferenceService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class PopulateReferenceTablesTasklet(
    private val referenceService: ReferenceService
) : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        referenceService.generatePercentages()
        referenceService.generateDebtDelays()
        referenceService.generateRisks()
        referenceService.generateRules()

        return RepeatStatus.FINISHED
    }
}