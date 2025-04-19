package com.example.inventory.data

import java.time.LocalDate
import kotlinx.coroutines.flow.firstOrNull

object ReportGenerator {
    suspend fun generate(repository: AppRepository, forDate: LocalDate): SalesReport {
        val allOrders = repository.getAllOrders().firstOrNull() ?: emptyList()
        // Filter orders by date prefix matching forDate
        val datePrefix = forDate.toString()
        val filtered = allOrders.filter { it.datetime.startsWith(datePrefix) }

        val statsMap = mutableMapOf<String, MutableList<Pair<Double, Double>>>()
        for (order in filtered) {
            val details = repository.getAllOrderDetails(order.orderId).firstOrNull() ?: emptyList()
            for (detail in details) {
                val menu = repository.getMenuById(detail.menuId).firstOrNull()
                if (menu != null) {
                    val revenue = menu.price * detail.quantity
                    val prepTime = 0.0
                    statsMap.getOrPut(menu.category) { mutableListOf() }
                        .add(Pair(revenue, prepTime))
                }
            }
        }
        val categoryStats = statsMap.map { (category, list) ->
            val count = list.size
            val revenue = list.sumOf { it.first }
            val avgPrep = if (count > 0) list.sumOf { it.second } / count else 0.0
            CategoryStat(category, count, revenue, avgPrep)
        }
        return SalesReport(forDate, categoryStats)
    }
}