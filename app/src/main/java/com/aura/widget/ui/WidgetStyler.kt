package com.aura.widget.ui

// Blueprint for individual app icon styling definitions
data class IconStyleModifier(
    val scaleFactor: Float = 1.0f,       // Size modifier (e.g., 0.8x small, 1.5x huge)
    val cornerRadius: Int = 16,          // Corner roundness radius value
    val isSquircle: Boolean = false,     // Toggle for custom adaptive smooth squircle shape
    val iconTintHex: String = "#FFFFFF", // Custom background plate tint color
    val labelVisible: Boolean = true,    // Toggle app text name below icon
    val labelColorHex: String = "#FFFFFF"// Color of the app name text string
)

// Blueprint for the parent folder/widget container canvas frame
data class FolderStyleModifier(
    val backgroundType: String = "glass", // glass, solid, gradient, transparent
    val backgroundColorHex: String = "#121212",
    val gradientStartHex: String = "#FF7E5F",
    val gradientEndHex: String = "#FEB47B",
    val backgroundOpacity: Int = 80,      // Transparency slider (0 = invisible, 100 = opaque)
    val blurRadius: Int = 20,             // Background glass blur depth level
    val borderWidthPx: Int = 2,           // Edge border thickness stroke
    val borderColorHex: String = "#FFFFFF",
    val shadowElevationDp: Int = 8        // 3D pop shadow height out of the wallpaper
)

class WidgetStylerEngine {

    // Generates the rendering configuration map for an app component plate
    fun generateAppPlateStyle(modifier: IconStyleModifier): Map<String, Any> {
        val finalShape = when {
            modifier.isSquircle -> "Adaptive Squircle Path"
            modifier.cornerRadius >= 50 -> "Perfect Circle"
            modifier.cornerRadius <= 0 -> "Sharp Square Matrix"
            else -> "Rounded Rectangle (Radius: ${modifier.cornerRadius}px)"
        }

        return mapOf(
            "renderShape" to finalShape,
            "calculatedScale" to modifier.scaleFactor,
            "alphaHex" to modifier.iconTintHex,
            "textRenderStyle" to "Color: ${modifier.labelColorHex} | Visible: ${modifier.labelVisible}"
        )
    }

    // Generates the canvas backing specs for the folder widget window
    fun generateFolderCanvasSpecs(modifier: FolderStyleModifier): Map<String, Any> {
        return mapOf(
            "backingDrawMode" to modifier.backgroundType.uppercase(),
            "compositingOpacity" to (modifier.backgroundOpacity / 100.0f),
            "blurEngineCoreRadius" to modifier.blurRadius,
            "strokeProfile" to "Width: ${modifier.borderWidthPx}px | Color: ${modifier.borderColorHex}",
            "shadowLayerDepth" to "${modifier.shadowElevationDp}dp"
        )
    }
}
