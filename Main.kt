package converter

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val str = readLine()!!.lowercase()
        if (str == "exit") break
        parse(str)
        println()
    }
}

enum class Type { LENGTH, WEIGHT, TEMPERATURE}

enum class Units(val names: List<String>, val ratio: Double, val type: Type) {
    Meter(listOf("m", "meter", "meters"), 1.0, Type.LENGTH),
    Kilometer(listOf("km", "kilometer", "kilometers"), 1000.0, Type.LENGTH),
    Centimeter(listOf("cm", "centimeter", "centimeters"), 0.01, Type.LENGTH),
    Millimeter(listOf("mm", "millimeter", "millimeters"), 0.001, Type.LENGTH),
    Mile(listOf("mi", "mile", "miles"), 1609.35, Type.LENGTH),
    Yard(listOf("yd", "yard", "yards"), 0.9144, Type.LENGTH),
    Foot(listOf("ft", "foot", "feet"), 0.3048, Type.LENGTH),
    Inch(listOf("in", "inch", "inches"), 0.0254, Type.LENGTH),

    Gram(listOf("g", "gram", "grams"), 1.0, Type.WEIGHT),
    Kilogram(listOf("kg", "kilogram", "kilograms"), 1000.0, Type.WEIGHT),
    Milligram(listOf("mg", "milligram", "milligrams"), 0.001, Type.WEIGHT),
    Pound(listOf("lb", "pound", "pounds"), 453.592, Type.WEIGHT),
    Ounce(listOf("oz", "ounce", "ounces"), 28.3495, Type.WEIGHT),

    Kelvins(listOf("k", "kelvin", "kelvins"), 1.0, Type.TEMPERATURE),
    Celsius(listOf("c", "degree Celsius", "degrees Celsius", "celsius", "dc"), 1.0, Type.TEMPERATURE),
    Fahrenheit(listOf("f", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df"), 1.0, Type.TEMPERATURE),
}

fun calc(t: Double, x: Char, y: Char): Double =
    if (x == y) t else
    when (x) {
        'K' -> if (y == 'C') { t - 273.15 } else { t * 1.8 - 459.67 }
        'C' -> if (y == 'K') { t + 273.15 } else { t * 1.8 + 32 }
        'F' -> if (y == 'K') { (t + 459.67) * 5 / 9 } else { (t - 32) * 5 / 9 }
        else -> 0.0
    }

fun parse(txt: String): Boolean {
    var unitIn = Units.Meter
    var unitOut = Units.Meter
    var mIn = "???"
    var mOut = "???"
    var typeIn = Type.LENGTH
    var typeOut = Type.LENGTH

    val list = txt.split(" ")
    if (list[0] == "exit") return false
    val vv = list[0].toDoubleOrNull()
    if (vv == null) {
        println("Parse error")
        return true
    }

    val j = if (vv == 1.0) 1 else 2
    var amt = vv

    val off1 = if (list[1].contains("degree")) 1 else 0
    val lIn = list[1 + off1]
    val off2 = if (list[3 + off1].contains("degree")) 1 else 0
    val lOut = list[3 + off1 + off2]
    for (unit in  Units.values()) {
        if (unit.names.contains(lIn.lowercase())) {
            unitIn = unit
            mIn = unit.names[2]
            typeIn = unit.type
        }
        if (unit.names.contains(lOut.lowercase())) {
            unitOut = unit
            mOut = unit.names[2]
            typeOut = unit.type
        }
    }
    if (typeIn != typeOut || mIn == "???" || mOut == "???") {
        println("Conversion from $mIn to $mOut is impossible")
        return true
    }

    if (amt < 0 && typeIn != Type.TEMPERATURE) {
        println("$typeIn shouldn't be negative")
        return true
    }

    if (typeIn == Type.TEMPERATURE) {
        amt = calc(amt, unitIn.name[0], unitOut.name[0])
    } else {
        amt *= unitIn.ratio / unitOut.ratio
    }
    val k = if (amt == 1.0) 1 else 2
    mIn = unitIn.names[j]
    mOut = unitOut.names[k]
    println("$vv $mIn is $amt $mOut")

    return true
}

