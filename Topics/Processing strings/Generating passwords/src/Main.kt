fun main() {
    // write your code here
    val (upper, lower, numbers, length) = readln().split(" ").map { it.toInt() }

    var passIsSecure = false
    while (!passIsSecure) {
        val password = generatePassword(upper, lower, numbers, length)
        if (passwordComplexityChecker(password, upper, lower, numbers, length)) {
            println(password)
            passIsSecure = true
        }
    }
}

private fun generatePassword(
    upperCount: Int,
    lowerCount: Int,
    numbers: Int,
    length: Int
): String {
    val upperCase = List(upperCount) { ('A'..'Z').random() }
    val lowerCase = List(lowerCount) { ('a'..'z').random() }
    val numCase = List(numbers) { ('0'..'9').random() }
    val other = List(
        length - (upperCount + lowerCount + numbers)
    ) { (('0'..'9') + ('a'..'z') + ('A'..'Z')).random() }

    val password: List<Char> = (upperCase + lowerCase + numCase + other).shuffled()
    return password.joinToString("")
}

private fun passwordComplexityChecker(
    pass: String,
    upperCount: Int,
    lowerCount: Int,
    numbers: Int,
    length: Int
): Boolean {
    val passCharArray = pass.toCharArray()
    if (pass.length < length) return false
    if (passCharArray.filter { it.isUpperCase() }.size < upperCount) return false
    if (passCharArray.filter { it.isLowerCase() }.size < lowerCount) return false
    if (passCharArray.filter { it.isDigit() }.size < numbers) return false

    if (pass.zipWithNext().any { (a, b) -> a == b }) return false

    return true
}