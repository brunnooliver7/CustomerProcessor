package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.ExternalData
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "CollectDataWriterConfig")
class CollectDataWriter {

    @Bean
    fun collectDataWriter(@Qualifier("customersDataSource") dataSource: DataSource): JdbcBatchItemWriter<ExternalData> {
        return JdbcBatchItemWriterBuilder<ExternalData>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/collect_data_writer.sql"))
            .beanMapped()
            .build()
    }

}