package com.bruno.customerprocessor.utils

import java.io.File
import java.io.FileWriter
import java.io.IOException

class FakerUtils {

    companion object {

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