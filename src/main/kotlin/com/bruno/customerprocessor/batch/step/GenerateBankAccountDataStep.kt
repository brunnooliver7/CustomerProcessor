package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.external.BankAccount
import com.bruno.customerprocessor.model.BankAccountRead
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration(value = "GenerateBankAccountsStepConfig")
class GenerateBankAccountDataStep {

    @Bean
    fun generateBankAccounts(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("generateBankAccountsReader") generateBankAccountsReader: ItemReader<BankAccountRead>,
        @Qualifier("generateBankAccountsWriter") generateBankAccountsWriter: JdbcBatchItemWriter<BankAccount>
    ): Step {
        return StepBuilder("Generate Bank Accounts Step", jobRepository)
            .chunk<BankAccountRead, BankAccount>(20_000, transactionManager)
            .reader(generateBankAccountsReader)
            .writer(generateBankAccountsWriter)
            .build()
    }

}