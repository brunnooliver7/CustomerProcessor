package com.bruno.customerprocessor.batch.utils

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class FileUtils {

    companion object {
        fun readSqlFromFile(fileName: String): String {
            val resource = ClassPathResource(fileName)
            val inputStream = resource.inputStream
            return inputStream.bufferedReader(StandardCharsets.UTF_8).use { it.readText() }
        }
    }

}