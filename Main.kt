package converter

fun main() {

    val units = listOf(
        listOf("m", "meter", "meters", "1.0"),
        listOf("km", "kilometer", "kilometers", "1000.0"),
        listOf("cm", "centimeter", "centimeters", "0.01"),
        listOf("mm", "millimeter", "millimeters", "0.001"),
        listOf("mi", "mile", "miles", "1609.35"),
        listOf("yd", "yard", "yards", "0.9144"),
        listOf("ft", "foot", "feet", "0.3048"),
        listOf("in", "inch", "inches", "0.0254"))

    print("Enter a number and a measure of length: ")
    val (v, m) = readLine()!!.split(" ")
    var isFind = false
    for (i in 0 until units.size) {
        if (m.lowercase() in units[i]) {
            val vv = v.toDouble()
            val amt = units[i][3].toDouble() * vv
            val j = if (vv == 1.0) 1 else 2
            val k = if (amt == 1.0) 1 else 2
            println("$vv ${units[i][j]} is $amt ${units[0][k]}")
            isFind = true
            break
        }
    }
    if (!isFind) {
        println("Wrong input. Unknown unit $m")
    }
}
