package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.Calculus
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "CalculusWriterConfig")
class CalculusWriter {

    @Bean
    fun calculusWriter(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): JdbcBatchItemWriter<Calculus> {
        return JdbcBatchItemWriterBuilder<Calculus>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/calculus_writer.sql"))
            .beanMapped()
            .build()
    }

}