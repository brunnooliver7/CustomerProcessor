package com.bruno.customerprocessor.batch.step

import com.bruno.customerprocessor.entity.external.BankAccount
import com.bruno.customerprocessor.model.BankAccountData
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class GenerateBankAccountDataStep {

    @Bean
    fun generateBankAccountDataStepBean(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        @Qualifier("generateBankAccountsReader") generateBankAccountsReader: ItemReader<BankAccountData>,
        @Qualifier("bankAccountWriter") bankAccountWriter: JdbcBatchItemWriter<BankAccount>
    ): Step {
        return StepBuilder("Generate Bank Accounts Data Step", jobRepository)
            .chunk<BankAccountData, BankAccount>(20_000, transactionManager)
            .reader(generateBankAccountsReader)
            .writer(bankAccountWriter)
            .build()
    }

}