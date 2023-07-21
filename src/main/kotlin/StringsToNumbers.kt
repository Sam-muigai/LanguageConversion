fun main() {
    try {
        println(convertFromHundredToThousand("maghana iwi na mirongo ikenda"))
    }catch (e:Exception){
        println(e.message)
    }
}


fun convertFromHundredToThousand(number: String): Int {
    val numArray = number.split(" ")
    if (number.contains("ighana")){
        //Case for 100
        when (numArray.size) {
            1 -> {
                return 100
            }
            //110
            3 -> {
                val tens = numArray[2]
                val output = "1${convertStringToNumbers(number = tens)}"
                return output.toInt()
            }
            //120,130,140,150,160,170,180,190
            4 -> {
                val lastNo = "${numArray[2]} ${numArray[3]} "
                val output = "1${convertStringToNumbers(number = lastNo)}"
                return output.toInt()
            }
            else -> {
                val lastNo = "${numArray[2]} ${numArray[3]} ${numArray[4]} ${numArray[5]}"
                val output = "1${convertStringToNumbers(number = lastNo)}"
                return output.toInt()
            }
        }
    }else if (number.contains("maghana")){

        return when(numArray.size){
            //200,300,400 ...
            2 ->{
                "${convertToNumber(numArray[1].trim())}00".toInt()
            }
            //210,310,410 ...
            4 ->{
                "${convertToNumber(numArray[1].trim())}10".toInt()
            }
            //290,340,930
            5 ->{
                "${convertToNumber(numArray[1].trim())}${convertToNumber(numArray[4].trim())}0".toInt()
            }
            //211,314,517 ...
            6 ->{
                val tenths = "${numArray[3]} ${numArray[4]} ${numArray[5]}"
                val no = "${convertToNumber(numArray[1])}${convertStringToNumbers(tenths)}".toInt()
                no
            }
            //267
            7 ->{
                val tenths = "${numArray[3]} ${numArray[4]} ${numArray[5]} ${numArray[6]}"
                val no = "${convertToNumber(numArray[1])}${convertStringToNumbers(tenths)}".toInt()
                no
            }
            else->{
                //Just to indicate an error occured
                401
            }
        }
    }else{
        return convertStringToNumbers(number)
    }
}


//Convert from text to number
//0-99
fun convertStringToNumbers(number: String):Int{
    val input = number.trim()
    return if (input == "ikumi"){
        10
    }else if (number.contains("ikumi")){
        val lastNo = number.split(" ")[2]
        val numbers =  "1${convertToNumber(lastNo)}"
        numbers.toInt()
    } else if (number.contains("mirongo")){
        val numArray = number.split(" ")
        if (numArray.size == 4){
            //Check the value of tenth
            val tenthNo = numArray[1]
            val lastNo = numArray[3]
            val output = "${convertToNumber(tenthNo)}${convertToNumber(lastNo)}"
            output.toInt()
        }else{
            val tenthNo = numArray[1]
            val output = "${convertToNumber(tenthNo)}0"
            return output.toInt()
        }
    }else {
        convertToNumber(number)
    }
}


//Converting text to number
// 0-9
fun convertToNumber(number: String): Int{
    val numbers = listOf(
        "",
        "imweri",
        "iwi",
        "idadu",
        "inya",
        "isanu",
        "irandadu",
        "mfungade",
        "wunyanya",
        "ikenda"
    )
    if (numbers.contains(number)){
        return numbers.indexOf(number)
    }else{
        throw NumberNotInLanguageException("Language not understood")
    }
}

class NumberNotInLanguageException(message: String) : Exception(message)