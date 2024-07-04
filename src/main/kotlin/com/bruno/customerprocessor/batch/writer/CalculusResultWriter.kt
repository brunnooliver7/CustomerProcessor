package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.CalculusResult
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class CalculusResultWriter {

    @Bean
    fun calculusResultsWriter(@Qualifier("customersDataSource") dataSource: DataSource): JdbcBatchItemWriter<CalculusResult> {
        return JdbcBatchItemWriterBuilder<CalculusResult>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/calculus_result_writer.sql"))
            .beanMapped()
            .build()
    }

}