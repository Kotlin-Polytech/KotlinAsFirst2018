@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt((v.map { it * it }).sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    if (list.isNotEmpty()) list.replaceAll { it - mean }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double =
        a.foldIndexed(0.0) { index, sum, it -> sum + it * b[index] }

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double =
        p.foldIndexed(0.0) { index, sum, it -> sum + it * x.pow(index) }

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sum = 0.0
    for (i in 0 until list.size) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun prime(n: Int): Int {
    var counter = 0
    var result = 0
    while (counter != n) {
        result++
        if (isPrime(result)) counter++
    }
    return result
}

fun factorize(n: Int): List<Int> {
    var old = n
    val list = mutableListOf<Int>()
    var i = 1
    if (isPrime(old)) return list + n else {
        while (!isPrime(old)) {
            if (old % prime(i) == 0) {
                old /= prime(i)
                list.add(prime(i))
            } else i++
        }
    }
    list.add(old)
    return list.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    var mod: Int
    val list = mutableListOf<Int>()
    while (number >= base) {
        mod = number % base
        number /= base
        list.add(0, mod)
    }
    list.add(0, number)
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String =
        convert(n, base).joinToString(separator = "") { if (it < 10) "$it" else 'a' + it - 10 + "" }

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = digits.foldIndexed(0.0) { index, sum, it ->
    sum + it * base.toDouble().pow(digits.size - 1 - index)
}.toInt()

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun listMaker(n: Int): MutableList<Int> {
    var new: Int
    var old = n
    val list = mutableListOf<Int>()
    while (old > 0) {
        new = old % 10
        list.add(0, new)
        old /= 10
    }
    return list
}

fun russian(n: Int): String {
    var string = ""
    val firstList = listMaker(n / 1000)
    val secondList = listMaker(n % 1000)
    if (n > 999) {
        string = (russianHelp(firstList) + " ").replace("два ", "две ").replace("один ", "одна ")
        string += when {
            firstList.last() == 1 -> "тысяча"
            firstList.last() == 2 || firstList.last() == 3 || firstList.last() == 4 -> "тысячи"
            else -> "тысяч"
        }
        if (secondList.isNotEmpty()) string += " " +
                russianHelp(secondList)
    } else {
        string += russianHelp(secondList)
    }
    return string
}


fun russianHelp(list: List<Int>): String {
    var string = ""
    if (list.size == 3) string += when {
        list[0] == 1 -> "сто"
        list[0] == 2 -> "двести"
        list[0] == 3 -> "триста"
        list[0] == 4 -> "четыреста"
        list[0] == 5 -> "пятьсот"
        list[0] == 6 -> "шестьсот"
        list[0] == 7 -> "семьсот"
        list[0] == 8 -> "восемьсот"
        list[0] == 9 -> "девятьсот"
        else -> ""
    } + if (list[1] != 0 || list[2] != 0) " " else ""
    if ((list.size == 2 || list.size == 3) && list[list.size - 2] != 1) {
        string += when {
            list[list.size - 2] == 2 -> "двадцать"
            list[list.size - 2] == 3 -> "тридцать"
            list[list.size - 2] == 4 -> "сорок"
            list[list.size - 2] == 5 -> "пятьдесят"
            list[list.size - 2] == 6 -> "шестьдесят"
            list[list.size - 2] == 7 -> "семьдесят"
            list[list.size - 2] == 8 -> "восемьдесят"
            list[list.size - 2] == 9 -> "девяносто"
            else -> ""
        } + if (list.last() != 0 && list[list.size - 2] != 1 && list[list.size - 2] != 0) " " else ""
    }
    if ((list.size == 2 || list.size == 3) && list[list.size - 2] == 1) {
        string += when {
            list.last() == 0 -> "десять"
            list.last() == 1 -> "одиннадцать"
            list.last() == 2 -> "двенадцать"
            list.last() == 3 -> "тринадцать"
            list.last() == 4 -> "четырнадцать"
            list.last() == 5 -> "пятнадцать"
            list.last() == 6 -> "шестьнадцать"
            list.last() == 7 -> "семнадцать"
            list.last() == 8 -> "восемнадцать"
            list.last() == 9 -> "девятнадцать"
            else -> ""
        }
    } else {
        string += when {
            list.last() == 1 -> "один"
            list.last() == 2 -> "два"
            list.last() == 3 -> "три"
            list.last() == 4 -> "четыре"
            list.last() == 5 -> "пять"
            list.last() == 6 -> "шесть"
            list.last() == 7 -> "семь"
            list.last() == 8 -> "восемь"
            list.last() == 9 -> "девять"
            list.last() == 0 -> ""
            else -> ""
        }
    }
    return string
}
