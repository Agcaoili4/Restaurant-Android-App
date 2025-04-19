package com.example.inventory.data

import java.time.LocalDate

/**
 * SalesReport for a given date, containing stats per category.
 */
data class SalesReport(
    val date: LocalDate,
    val categoryStats: List<CategoryStat>
)

data class CategoryStat(
    val category: String,
    val count: Int,
    val revenue: Double,
    val avgPrepMins: Double
)