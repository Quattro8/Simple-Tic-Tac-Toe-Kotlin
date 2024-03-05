fun main() {
    // write your code here
    val linkDictionary = readln()
        .substringAfter('?')
        .split('&')

    val mapOfAttributes = mutableMapOf<String, String>()
    var password: String = ""

    for (link in linkDictionary) {
        val (key, value) = link.split('=')
        if (value == "") {
            mapOfAttributes[key] = "not found"
        } else if (key == "pass") {
            mapOfAttributes[key] = value
            password = "password : $value"
        } else {
            mapOfAttributes[key] = value
        }
    }

    mapOfAttributes.forEach {
        println("${it.key} : ${it.value}")
    }

    if (!password.isEmpty()) println(password)

}
