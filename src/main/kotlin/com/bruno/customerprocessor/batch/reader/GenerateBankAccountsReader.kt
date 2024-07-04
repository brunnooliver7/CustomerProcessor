package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.model.BankAccountData
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.sql.DataSource

@Configuration
class GenerateBankAccountReader {


    @Bean
    fun generateBankAccountsReader(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<BankAccountData> {
        return FlatFileItemReaderBuilder<BankAccountData>()
            .name("Generate Bank Accounts Reader")
            .resource(ClassPathResource("csv/bank_account.csv"))
            .linesToSkip(1)
            .lineMapper(bankAccountLineMapper())
            .build()
    }

    private fun bankAccountLineMapper(): DefaultLineMapper<BankAccountData> {
        return DefaultLineMapper<BankAccountData>().apply {
            setLineTokenizer(DelimitedLineTokenizer().apply {
                setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA)
                setNames("customerId", "bankId", "accountNumber", "balance")
            })
            setFieldSetMapper(BeanWrapperFieldSetMapper<BankAccountData>().apply {
                setTargetType(BankAccountData::class.java)
            })
        }
    }

}