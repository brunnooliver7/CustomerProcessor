package com.bruno.customerprocessor.batch.tasklet

import com.bruno.customerprocessor.service.DataGeneratorService
import jakarta.transaction.Transactional
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class CreateExternalDataTasklet(
    private val dataGeneratorService: DataGeneratorService
) : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val customers = dataGeneratorService.generateCustomers(100, 10)
        val banks = dataGeneratorService.generateBanks(3, 3)
        val bankAccounts = dataGeneratorService.generateBankAccounts(customers, banks, 10)
        dataGeneratorService.generateRevenues(customers, bankAccounts, 100, 10)
        dataGeneratorService.generateLoans(customers, 10)
        return RepeatStatus.FINISHED
    }
}