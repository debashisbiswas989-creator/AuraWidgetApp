package org.example

import java.io.File
import org.json.JSONObject
import org.json.JSONArray

fun main() {
    val jsonFile = File("essentials_widget.json")

    // FORCE GENERATE FRESH JSON DATA STRUCTURE WITH YOUR EXACT APPS LIST
    val json = JSONObject().apply {
        put("folderName", "My Cloud Apps")
        put("widgetType", "direct")
        put("grid", JSONObject().put("columns", 4).put("rows", 4).put("gap", 16).put("padding", 16).put("borderRadius", 24))
        put("background", JSONObject().apply {
            put("bgType", "glass")
            put("bgColor", "#ffffff")
            put("bgOpacity", 15)
            put("bgBlur", 20)
            put("gradientStart", "#ff7e5f")
            put("gradientEnd", "#feb47b")
            put("borderColor", "#ffffff")
            put("borderWidth", 1)
            put("borderOpacity", 20)
            put("hasShadow", true)
        })
        put("label", JSONObject().apply {
            put("labelColor", "#ffffff")
            put("labelSize", 12)
            put("showLabel", true)
            put("labelFont", "font-sans")
        })
        put("apps", JSONArray())
    }

    val appsArray = json.getJSONArray("apps")

    // Your complete custom application ecosystem array
    val masterAppList = listOf(
        Pair("Free Fire", "#FF5500"), Pair("Chess", "#7FA650"), Pair("Carrom", "#D2B48C"), Pair("Sudoku", "#007AFF"),
        Pair("Paytm", "#002E94"), Pair("PhonePe", "#5F259F"), Pair("Google Pay", "#4285F4"), Pair("SBI", "#00A4E4"),
        Pair("Punjab National Bank", "#A12830"), Pair("Axis Bank", "#971B44"), Pair("Kotak Mahindra Bank", "#ED1C24"),
        Pair("Coin DCX", "#152238"), Pair("Amazon", "#FF9900"), Pair("Flipkart", "#2874F0"), Pair("Meesho", "#F43397"),
        Pair("MX Player", "#004B93"), Pair("SnapTube", "#FFCC00"), Pair("Jio Hotstar", "#0A122C"), Pair("Music", "#EE415E"),
        Pair("Gallery", "#FF9500"), Pair("YouTube", "#FF0000"), Pair("YouTube Music", "#FF0000"), Pair("Google TV", "#4285F4"),
        Pair("Shazam", "#0088FF"), Pair("WhatsApp", "#25D366"), Pair("Instagram", "#E1306C"), Pair("Telegram", "#0088CC"),
        Pair("Facebook", "#1877F2"), Pair("Messenger", "#006AFF"), Pair("Message", "#1A73E8"), Pair("Gmail", "#EA4335"),
        Pair("Setting", "#757575"), Pair("Calculator", "#FF9500"), Pair("Files", "#4285F4"), Pair("Notes", "#FFCC00"),
        Pair("Weather", "#00B0FF"), Pair("Calendar", "#EA4335"), Pair("Speed Test", "#141526"), Pair("Security", "#4CAF50"),
        Pair("Clock", "#9C27B0"), Pair("Contact", "#1A73E8"), Pair("Recorder", "#FF5722"), Pair("Scanner", "#009688"),
        Pair("MI Remote", "#00E5FF"), Pair("Compass", "#FFEB3B"), Pair("Downloads", "#607D8B"), Pair("Battery Temperature", "#FF3D00"),
        Pair("Soul Browser", "#1DE9B6"), Pair("Chrome", "#4285F4"), Pair("Google", "#4285F4"), Pair("Photos", "#EA4335"),
        Pair("Maps", "#34A853"), Pair("Play Store", "#607D8B"), Pair("Drive", "#34A853"), Pair("Folder Widget", "#673AB7"),
        Pair("Memory Zone", "#3F51B5"), Pair("Google Lens", "#4285F4"), Pair("Everything Widget", "#E91E63"),
        Pair("Minimal Widget", "#00BCD4"), Pair("Shortcut Maker", "#9C27B0"), Pair("Draw on Screen", "#FF4081"),
        Pair("Ninja VPN", "#212121"), Pair("Proton VPN", "#6D4C41"), Pair("Excel", "#107C41"), Pair("Meet", "#00AC47"),
        Pair("Google One", "#4285F4"), Pair("Google Opinion Reward", "#FFC107"), Pair("Adobe Acrobat", "#E53935"),
        Pair("Canva", "#00C4CC"), Pair("PicsArt", "#D81B60"), Pair("Testbook", "#0052CC"), Pair("NSOU", "#003366"),
        Pair("ECI NET", "#008080"), Pair("My SSC", "#303F9F"), Pair("DigiLocker", "#0056B3"), Pair("Umang", "#003399"),
        Pair("IRCTC", "#004B93"), Pair("Rail One", "#007BFF"), Pair("Confirm Ticket", "#2E7D32"), Pair("Where is my train", "#1B5E20"),
        Pair("Aadhar", "#FF9933"), Pair("Abha", "#008000"), Pair("इस", "#4A148C"), Pair("Airtel Service", "#E60000"),
        Pair("ShareMe", "#00C853"), Pair("Indus App Store", "#E65100"), Pair("Airtel", "#E60000"), Pair("Jio", "#0F3DA1"),
        Pair("Gemini", "#4285F4"), Pair("ChatGPT", "#10A37F"), Pair("Quix", "#000000"), Pair("Organic Map", "#558B2F"),
        Pair("Pocket Pal", "#795548"), Pair("Fing", "#111111"), Pair("Bengali to English Translator", "#3F51B5"),
        Pair("Revanced Manager", "#311B92"), Pair("Micro G Setting", "#004D40")
    )

    for (app in masterAppList) {
        val appObj = JSONObject().apply {
            put("name", app.first)
            put("originalIcon", app.first.lowercase().replace(" ", "_"))
            put("color", app.second)
        }
        appsArray.put(appObj)
    }

    // Force write the update to the config file directly
    jsonFile.writeText(json.toString(2))

    // --- TERMINAL RENDER ENGINE ---
    val folderName = json.getString("folderName")
    val grid = json.getJSONObject("grid")
    val cols = grid.getInt("columns")
    val rows = grid.getInt("rows")

    println("==================================================")
    println(" MASTER WIDGET STORAGE CANVAS: $folderName")
    println("==================================================")
    println("• Grid Engine Matrix  : ${cols}x${rows} Layout Mode")
    println("• Total Loaded Slots  : ${appsArray.length()} Registered Applications")
    println("--------------------------------------------------")
    println(" ACTIVE APPLICATIONS INDEX")
    println("--------------------------------------------------")

    for (i in 0 until appsArray.length()) {
        val appObj = appsArray.getJSONObject(i)
        val name = appObj.getString("name")
        val color = appObj.getString("color")
        System.out.printf("  [%02d] App: %-28s | UI Hex Accent: %s\n", (i + 1), name, color)
    }
    println("==================================================")
}
