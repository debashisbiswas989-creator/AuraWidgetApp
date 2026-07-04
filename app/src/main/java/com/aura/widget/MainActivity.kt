package com.aura.widget

import android.app.Activity
import android.os.Bundle
import android.content.pm.PackageManager
import com.aura.widget.ui.WidgetCanvasEngine
import com.aura.widget.ui.WidgetLayoutSettings
import com.aura.widget.ui.AppIconConfig
import com.aura.widget.ui.WidgetStylerEngine
import com.aura.widget.ui.IconStyleModifier
import com.aura.widget.ui.FolderStyleModifier

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        println("==================================================")
        println(" AURA CUSTOM APP COUPLER ENGINE: BOOTING UP UI")
        println("==================================================")

        // 1. DYNAMIC SYSTEM RECOVERY: Scans your phone's live packages automatically
        val pm = packageManager
        val installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        println("• Live System Status : Found ${installedApps.size} apps on this phone.")

        // 2. STYLING MATRIX SETUP (Sliders/Customizer input simulation)
        // You can change layoutStyle to: "grid", "horizontal", "vertical", "crisscross", or "circle"
        val layoutSettings = WidgetLayoutSettings(
            layoutStyle = "circle", 
            columns = 5,
            rows = 5,
            totalApps = installedApps.size + 1 // Plus Angel One
        )

        val individualIconStyle = IconStyleModifier(
            scaleFactor = 1.2f,
            cornerRadius = 24,
            isSquircle = true, // Premium smooth icon mask toggled on
            iconTintHex = "#10A37F"
        )

        val folderBackgroundStyle = FolderStyleModifier(
            backgroundType = "glass",
            backgroundOpacity = 40,
            blurRadius = 25,
            borderColorHex = "#FFFFFF"
        )

        // 3. RUN INTERACTIVE MATRIX OPERATIONS
        val canvasEngine = WidgetCanvasEngine()
        val stylerEngine = WidgetStylerEngine()

        println("\n--- CONFIGURING CUSTOM BACKDROP STYLE ---")
        val backgroundSpecs = stylerEngine.generateFolderCanvasSpecs(folderBackgroundStyle)
        backgroundSpecs.forEach { (key, value) -> println("  [Backdrop Config] $key -> $value") }

        println("\n--- GENERATING INTERACTIVE GEOMETRIC VECTOR PLAN ---")
        
        // Combine your real phone apps with Angel One explicitly at the top
        val baseAppNames = mutableListOf("Angel One")
        installedApps.take(14).forEach { baseAppNames.add(it.loadLabel(pm).toString()) }

        baseAppNames.forEachIndexed { index, appName ->
            val appConfig = AppIconConfig(name = appName, hexColor = "#FFFFFF")
            val coords = canvasEngine.calculateIconCoordinates(index, layoutSettings, appConfig)
            val designStyle = stylerEngine.generateAppPlateStyle(individualIconStyle)

            System.out.printf(
                "  App #%02d: %-22s | Target UI Position: (X: %5.1f, Y: %5.1f) | Mask: %s\n",
                index + 1, appName, coords.first, coords.second, designStyle["renderShape"]
            )
        }
        println("==================================================")
        println(" STATUS: Blueprint compiled! Ready for interface rendering loop.")
        println("==================================================")
    }
}

