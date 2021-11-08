package signature
import java.io.File

val romanFont = File("src/signature/roman.txt")
val medium = File("src/signature/medium.txt")

fun main() {
    val romanLines = mutableListOf<String>()
    val mediumLines = mutableListOf<String>()
    var (name, status) = mutableListOf("", "")
    println("Enter name and surname: ").also { name = readLine()!! }
    println("Enter person's status: ").also { status = readLine()!! }
    repeat(10) { i -> romanLines.add(name.map { getChar(it, i, romanFont) }.joinToString(" ")) }
    repeat(3) { i -> mediumLines.add(status.map { getChar(it, i, medium) }.joinToString(" ")) }
    Pair(romanLines[0].length, mediumLines[0].length).run {
        println("8".repeat(maxOf(first + 9, second + 9)))
        repeat(10) { println("88" + " ".repeat(maxOf(second - first, 0) / 2 + 2) + romanLines[it]
                + " ".repeat((maxOf(second - first, 0) + 1) / 2 + 3) + "88") }
        repeat(3) { println("88" + " ".repeat(maxOf(first - second, 0) / 2 + 2) + mediumLines[it]
                + " ".repeat((maxOf(first - second, 0) + 1) / 2 + 3) + "88") }
        println("8".repeat(maxOf(first + 9, second + 9)))
    }
}
fun getChar(c: Char, l: Int, file: File): String {
    if (c == ' ') { return if (file == romanFont)  "         " else "    " }
    val index =  if (file == romanFont) {
        if(c.isLowerCase()) (c.code - 'a'.code) * 11 + 2 else (c.code - 'A'.code) * 11 + 288
    } else {
        if(c.isLowerCase()) (c.code - 'a'.code) * 4 + 2 else (c.code - 'A'.code) * 4 + 106
    }
    return file.readLines()[index + l].dropLast(1)
}