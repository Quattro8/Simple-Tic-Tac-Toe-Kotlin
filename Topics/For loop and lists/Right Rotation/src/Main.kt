fun main() {
    val list = MutableList<Int>(read()) { read() }
    var numOfReps = read()
    if (numOfReps % list.size != 0) numOfReps = numOfReps % list.size
    repeat(numOfReps) {
        list.add(0, list.last()).also { list.removeLast() }
    }
    println(list.joinToString(" "))
}

fun read() = readLine()!!.toInt()