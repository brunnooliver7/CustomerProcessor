package com.bruno.customerprocessor.batch.writer

import com.bruno.customerprocessor.batch.utils.FileUtils
import com.bruno.customerprocessor.entity.dinamic.ExternalData
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class GatherExternalDataWriter {

    @Bean
    fun gatherExtDataWriter(dataSource: DataSource): JdbcBatchItemWriter<ExternalData> {
        return JdbcBatchItemWriterBuilder<ExternalData>()
            .dataSource(dataSource)
            .sql(FileUtils.readSqlFromFile("sql/write/gather_external_data_writer.sql"))
            .beanMapped()
            .build()
    }

}