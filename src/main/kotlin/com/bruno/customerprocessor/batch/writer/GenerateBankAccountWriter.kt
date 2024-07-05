package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.external.BankAccount
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "GenerateBankAccountWriterConfig")
class GenerateBankAccountWriter {

    @Bean
    fun generateBankAccountsWriter(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): JdbcBatchItemWriter<BankAccount> {
        return JdbcBatchItemWriterBuilder<BankAccount>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/generate_bank_accounts_writer.sql"))
            .beanMapped()
            .build()
    }

}