package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.LoanRule
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class LoanRuleWriter {

    @Bean
    fun loanRulesWriter(@Qualifier("customersDataSource") dataSource: DataSource): JdbcBatchItemWriter<LoanRule> {
        return JdbcBatchItemWriterBuilder<LoanRule>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/loan_rule_writer.sql"))
            .beanMapped()
            .build()
    }

}