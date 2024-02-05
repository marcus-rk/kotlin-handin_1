package com.example.handin1

// Link to handIn: https://behu.gitbook.io/ita23-2.-semester/handins/week-1

fun main() {
    /////// Exercise 1. ///////
    println("Exercise 1")

    println("Please enter your age:")
    val inputAge: Int? = readlnOrNull()?.toIntOrNull(); // if input is not a number, set as null

    if (inputAge != null && inputAge >= 18)
        println("You are eligible to vote")
    else
        println("You are NOT eligible to vote")


    /////// Exercise 2. ///////
    println("\nExercise 2")

    val max: Int = getMax(1, 18, 8);
    val min: Int = getMin(1, 18, -8);

    println(max); //18
    println(min); //-8


    /////// Exercise 3. ///////
    println("\nExercise 3")
    val numbers: List<Int> = listOf(1, 10, 2, 30, 99)
    println("Average is: ${calculateAverage(numbers)}")


    /////// Exercise 4. ///////
    println("\nExercise 4")
    println("Please write your CPR number:")

    val cprInput: String? = readlnOrNull();
    if (cprInput != null)
        println("Is this a valid CPR number: ${isValidCPR(cprInput)}")


    /////// Exercise 5. ///////
    println("\nExercise 5")

    for (i in 1..100) {
        var isMultiple: Boolean = false;

        if (i % 3 == 0) {
            isMultiple = true
            print("Fizz")
        }

        if (i % 5 == 0) {
            isMultiple = true
            print("Buzz")
        }

        if (!isMultiple) {
            print(i)
        }

        // Need new line after iteration
        println()
    }


    /////// Exercise 6. ///////
    println("\nExercise 6")

    println("Please write your full name")
    val fullNameInput: String? = readlnOrNull(); // if input is invalid, set as null

    if (!fullNameInput.isNullOrEmpty())
        println("Name with abbreviations is: ${abbreviationMaker(fullNameInput)}")
    else
        println("Not a valid name")


    /////// Exercise 7. ///////
    println("\nExercise 7")

    println("Please write your grade score (0-100):")
    val gradeScoreInput: Int? = readlnOrNull()?.toIntOrNull();

    if (gradeScoreInput != null && gradeScoreInput in 0..100)
        println(calculateGrade(gradeScoreInput))
    else
        println("Please enter a valid grade score between 0 and 100.")


    /////// Exercise 8. ///////
    println("\nExercise 8")

    val words: List<String> =
        listOf("SQL", "JavaScript", "HTML", "CSS", "C", "Kotlin", "Scratch", "C#");
    val minimumLength: Int = 5
    println(filterWordsByLength(words, minimumLength)) // [JavaScript, Kotlin, Scratch]


    /////// Advanced - ISBN validation ///////
    println("\nAdvanced - ISBN validation")

    val isbnOne: String = "3-598-21508-8"; // true
    val isbnTwo: String = "0-439-42089-X"; // true
    val isbnThree: String = "043942089X"; // true
    val isbnFour: String = "043942089"; // false -- missing X
    val isbnFive: String = "0-43942-089-29-011-23"; // false -- Too long and formula calc is false

    println("Is this $isbnOne a valid ISBN number: ${isValidISBN(isbnOne)}");
    println("Is this $isbnTwo a valid ISBN number: ${isValidISBN(isbnTwo)}");
    println("Is this $isbnThree a valid ISBN number: ${isValidISBN(isbnThree)}");
    println("Is this $isbnFour a valid ISBN number: ${isValidISBN(isbnFour)}");
    println("Is this $isbnFive a valid ISBN number: ${isValidISBN(isbnFive)}");
}

fun isValidISBN(isbn: String): Boolean {
    val isbnCleaned: String = isbn.replace("-", "")

    if (isbnCleaned.length != 10)
        return false

    // ISBN-10 formula from hand-in:
    // (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9 * 2 + x10 * 1) mod 11 == 0
    var n: Int = 10
    var value: Int = 0

    for (char in isbnCleaned) {
        if (char.isDigit())
            value += char.digitToInt() * n
        else if (char == 'X')
            value += 10 * n
        else
            return false // invalid character -> not a valid ISBN

        n--
    }

    return (value % 11 == 0)
}

fun getMax(a: Int, b: Int, c: Int): Int {
    val numbers: List<Int> = listOf(a, b, c)
    var currentMax: Int = a

    for (number in numbers) {
        if (number > currentMax)
            currentMax = number
    }

    return currentMax
}

fun getMin(a: Int, b: Int, c: Int): Int {
    val numbers: List<Int> = listOf(a, b, c)
    var currentMin: Int = a

    for (number in numbers) {
        if (number < currentMin)
            currentMin = number
    }

    return currentMin
}

// could also just do sum() and average() on list
fun calculateAverage(numbers: List<Int>): Float {
    var sum: Int = 0;

    for (number in numbers) {
        sum += number
    }

    return sum / numbers.size.toFloat() // float if decimal number
}

fun isValidCPR(cpr: String): Boolean {
    // check for 10 digits
    if (cpr.length != 10)
        return false;

    // First 2 digits are not above 31 (day-digits)
    val firstTwoDigits: String = cpr.substring(0, 2);
    val firstTwoDigitsAsNumber: Int? = firstTwoDigits.toIntOrNull();

    if (firstTwoDigitsAsNumber != null && firstTwoDigitsAsNumber > 31) // can be 0 or under
        return false

    // The middle 2 digits are not above 12 (I guess they mean month digits?)
    val monthDigits: String = cpr.substring(2, 4);
    val monthDigitsAsNumber: Int? = monthDigits.toIntOrNull();

    if (monthDigitsAsNumber != null && monthDigitsAsNumber > 12) // can be 0 or under
        return false;

    return true
}

fun abbreviationMaker(fullName: String): String {
    val names = fullName.split(" ")
    var abbreviation: String = "";

    for (name in names) {
        if (name == names.last())
            abbreviation += name
        else
            abbreviation += "${name[0].uppercase()}. " // first char in name as uppercase followed by dot space
    }

    return abbreviation
}

// Return type in exercise says String, but Char would make more sense.
fun calculateGrade(score: Int): Char {
    val grade: Char = when (score) {
        in 90..100 -> 'A'
        in 80..89 -> 'B'
        in 70..79 -> 'C'
        in 60..69 -> 'D'
        else -> 'F'
    }

    return grade
}

fun filterWordsByLength(words: List<String>, minimumLength: Int): List<String> {
    val filteredWords: List<String> = words.filter { it.length >= minimumLength };
    return filteredWords;
}