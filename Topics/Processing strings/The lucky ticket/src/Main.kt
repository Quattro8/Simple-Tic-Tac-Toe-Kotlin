fun main() {
    // write your code here
    val ticket = readln().map { it.digitToInt() }
    when (ticket.take(3).sum()) {
        ticket.takeLast(3).sum() -> println("Lucky")
        else -> println("Regular")
    }
}