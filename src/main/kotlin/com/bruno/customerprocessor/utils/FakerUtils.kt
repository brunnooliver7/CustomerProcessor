package com.bruno.customerprocessor.utils

import java.io.File
import java.io.FileWriter
import java.io.IOException

class FakerUtils {

    companion object {

        @JvmStatic
        fun generateAndSaveSSNsToCSV(fileName: String, count: Int) {
            val resourceDirectory = "src/main/resources/csv/"
            val file = File("$resourceDirectory$fileName.csv")
            val ssns = mutableSetOf<String>()

            try {
                FileWriter(file).use { writer ->
                    while (ssns.size < 1000000) {
                        val ssn = generateSSN()
                        ssns.add(ssn)
                    }

                    for (ssn in ssns) {
                        writer.append("$ssn\n")
                    }
                }
                println("Successfully generated and saved $count SSNs to $fileName")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun generateSSN(): String {
            val area = (100..999).random()
            val group = (10..99).random()
            val serial = (1000..9999).random()
            return "%03d%02d%04d".format(area, group, serial)
        }

        @JvmStatic
        fun getFirstNames(size: Int): List<String> {
            val resourceUrl = javaClass.classLoader.getResource("csv/first_names.csv")
            val lines = resourceUrl?.readText()?.lines() ?: emptyList()

            val firstNames = mutableListOf<String>()

            if (resourceUrl != null) {
                for (i in 1..size) {
                    firstNames.add(lines[i - 1])
                }
            }

            return firstNames
        }

        @JvmStatic
        fun getLastNames(size: Int): List<String> {
            val resourceUrl = javaClass.classLoader.getResource("csv/last_names.csv")
            val lines = resourceUrl?.readText()?.lines() ?: emptyList()

            val lastNames = mutableListOf<String>()

            if (resourceUrl != null) {
                for (i in 1..size) {
                    lastNames.add(lines[i - 1])
                }
            }

            return lastNames
        }

        @JvmStatic
        fun getSSNs(size: Int): List<String> {
            val resourceUrl = javaClass.classLoader.getResource("csv/ssn.csv")
            val lines = resourceUrl?.readText()?.lines() ?: emptyList()

            val ssns = mutableListOf<String>()

            if (resourceUrl != null) {
                for (i in 1..size) {
                    ssns.add(lines[i - 1])
                }
            }

            return ssns
        }

    }

}