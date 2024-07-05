package com.bruno.customerprocessor.batch.reader

import com.bruno.customerprocessor.batch.factory.CalculusReaderFactory
import com.bruno.customerprocessor.model.CalculusRead
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration(value = "CalculusReaderConfig")
class CalculusReader {

    @Bean
    fun calculusReaderBank1(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<CalculusRead> {
        return CalculusReaderFactory.createCalculusReader(
            dataSource,
            1,
            "Calculus Reader - Bank 1"
        )
    }

    @Bean
    fun calculusReaderBank2(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<CalculusRead> {
        return CalculusReaderFactory.createCalculusReader(
            dataSource,
            2,
            "Calculus Reader - Bank 2"
        )
    }

    @Bean
    fun calculusReaderBank3(
        @Qualifier("customersDataSource") dataSource: DataSource
    ): ItemReader<CalculusRead> {
        return CalculusReaderFactory.createCalculusReader(
            dataSource,
            3,
            "Calculus Reader - Bank 3"
        )
    }

}