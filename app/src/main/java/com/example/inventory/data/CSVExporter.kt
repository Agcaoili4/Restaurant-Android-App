package com.example.inventory.data

object CSVExporter {
    fun export(report: SalesReport): String {
        val sb = StringBuilder()
        sb.append("Date,Category,Count,Revenue,AvgPrepMins\n")
        report.categoryStats.forEach { stat ->
            sb.append("${report.date},${stat.category},${stat.count},${stat.revenue},${stat.avgPrepMins}\n")
        }
        return sb.toString()
    }
}