package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.CalculusRule
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "CalculusRuleWriterConfig")
class CalculusRuleWriter {

    @Bean
    fun calculusRuleWriter(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): JdbcBatchItemWriter<CalculusRule> {
        return JdbcBatchItemWriterBuilder<CalculusRule>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/calculus_rule_writer.sql"))
            .beanMapped()
            .build()
    }

}