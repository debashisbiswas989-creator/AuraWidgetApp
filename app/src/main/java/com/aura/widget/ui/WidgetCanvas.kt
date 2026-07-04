package com.aura.widget.ui

import kotlin.math.cos
import kotlin.math.sin

// Data blueprint for how an app is configured by the user
data class AppIconConfig(
    val name: String,
    val hexColor: String,
    val sizeDp: Int = 60,
    val shapeType: String = "rounded" // rounded, circle, squircle
)

// Main layout coordinator configuration
data class WidgetLayoutSettings(
    val layoutStyle: String = "grid", // grid, horizontal, vertical, crisscross, circle
    val columns: Int = 4,
    val rows: Int = 4,
    val totalApps: Int = 97,
    val padding: Int = 16,
    val itemSpacing: Int = 12,
    val circleRadiusPx: Double = 150.0 // For the circular layout choice
)

class WidgetCanvasEngine {

    // Calculates exactly where an icon should sit on the screen based on the chosen geometric style
    fun calculateIconCoordinates(
        index: Int,
        config: WidgetLayoutSettings,
        app: AppIconConfig
    ): Pair<Double, Double> {
        return when (config.layoutStyle.lowercase()) {
            
            "horizontal" -> {
                // Stack flat in a single line matching your exact spacing slider
                val x = config.padding + (index * (app.sizeDp + config.itemSpacing)).toDouble()
                val y = config.padding.toDouble()
                Pair(x, y)
            }

            "vertical" -> {
                // Stack straight down as a custom sidebar component
                val x = config.padding.toDouble()
                val y = config.padding + (index * (app.sizeDp + config.itemSpacing)).toDouble()
                Pair(x, y)
            }

            "crisscross" -> {
                // Alternating brick layout patterns
                val row = index / config.columns
                val col = index % config.columns
                // Shift every odd row slightly right to create a diamond/crisscross effect
                val xOffset = if (row % 2 != 0) (app.sizeDp / 2).toDouble() else 0.0
                val x = config.padding + (col * (app.sizeDp + config.itemSpacing)) + xOffset
                val y = config.padding + (row * (app.sizeDp + config.itemSpacing)).toDouble()
                Pair(x, y)
            }

            "circle" -> {
                // Radial geometry: spreads apps evenly around a center point
                val centerPointX = 250.0 
                val centerPointY = 250.0
                // Divide 360 degrees evenly by the number of apps on the page
                val angle = index * (2 * Math.PI / config.totalApps.coerceAtMost(12)) 
                val x = centerPointX + config.circleRadiusPx * cos(angle)
                val y = centerPointY + config.circleRadiusPx * sin(angle)
                Pair(x, y)
            }

            else -> { // Default Dynamic Grid mode (Up to 10x10)
                val row = index / config.columns
                val col = index % config.columns
                val x = config.padding + (col * (app.sizeDp + config.itemSpacing)).toDouble()
                val y = config.padding + (row * (app.sizeDp + config.itemSpacing)).toDouble()
                Pair(x, y)
            }
        }
    }
}
