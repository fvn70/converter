package converter

fun main() {

    val units = listOf(
        listOf("m", "meter", "meters", "1.0", "1"),
        listOf("km", "kilometer", "kilometers", "1000.0", "1"),
        listOf("cm", "centimeter", "centimeters", "0.01", "1"),
        listOf("mm", "millimeter", "millimeters", "0.001", "1"),
        listOf("mi", "mile", "miles", "1609.35", "1"),
        listOf("yd", "yard", "yards", "0.9144", "1"),
        listOf("ft", "foot", "feet", "0.3048", "1"),
        listOf("in", "inch", "inches", "0.0254", "1"),
        listOf("g", "gram", "grams", "1.0", "2"),
        listOf("kg", "kilogram", "kilograms", "1000.0", "2"),
        listOf("mg", "milligram", "milligrams", "0.001", "2"),
        listOf("lb", "pound", "pounds", "453.592", "2"),
        listOf("oz", "ounce", "ounces", "28.3495", "2"),
    )

    while (true) {
        print("Enter what you want to convert (or exit): ")
        val str = readLine()!!
        if (str == "exit") break
        val (v, unitIn, cmd, unitOut) = str.split(" ")

        var uIn = "???"
        var uOut = "???"
        var typeIn = "1"
        var typeOut = "1"
        val vv = v.toDouble()
        val j = if (vv == 1.0) 1 else 2
        var amt = vv
        var i1 = 0
        var i2 = 0
        for (i in 0 until units.size) {
            if (unitIn.lowercase() in units[i]) {
                i1 = i
                uIn = units[i][2]
                typeIn = units[i][4]
            }
            if (unitOut.lowercase() in units[i]) {
                i2 = i
                uOut = units[i][2]
                typeOut = units[i][4]
            }
        }
        if (typeIn != typeOut || uIn == "???" || uOut == "???") {
            println("Conversion from $uIn to $uOut is impossible")
        } else {
            amt *= units[i1][3].toDouble() / units[i2][3].toDouble()
            val k = if (amt == 1.0) 1 else 2
            uIn = units[i1][j]
            uOut = units[i2][k]
            println("$vv $uIn is $amt $uOut")
        }
        println()
    }
}
