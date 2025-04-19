package com.example.inventory.data

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class CSVExporterTest {
    @Test
    fun exportReport_producesCorrectCSV() {
        val date = LocalDate.of(2025, 4, 19)
        val stats = listOf(
            CategoryStat("MainDish", 2, 20.0, 5.0),
            CategoryStat("Dessert", 1, 5.0, 3.0)
        )
        val report = SalesReport(date, stats)
        val csv = CSVExporter.export(report).trim()  
        val expected = """
            Date,Category,Count,Revenue,AvgPrepMins
            2025-04-19,MainDish,2,20.0,5.0
            2025-04-19,Dessert,1,5.0,3.0
        """.trimIndent().trim()
        assertEquals(expected, csv)
    }
}