package org.jetbrains.research.deprecated

import java.io.File

@Deprecated("change model")
object TokenMaker {
//    var id = 0
//    private fun addWord(list: MutableList<Token>, word: StringBuilder, fileName: String, line: Int, end: Int) {
//        if (word.isNotEmpty()) {
//            list.add(Token(id, fileName, line, (end - word.length + 1)..end, word.toString()))
//            id++
//            word.clear()
//        }
//    }
//
//    private fun isSeparator(ch: Char): Boolean = ch !in 'A'..'Z'
//            && ch !in 'a'..'z'
//            && ch !in '0'..'9'
//
//    private fun uselessChar(ch: Char): Boolean = ch != ' '
//            && ch != ','
//            && ch != ';'
//
//    private fun isComment(j: Int, arr: CharArray): Int {
//        var i = j
//        if (i < arr.size - 1 && arr[i] == '/' && arr[i + 1] == '/') {
//            while (i < arr.size && arr[i].toInt() != 10) {
//                i += 1
//            }
//            i += 1
//            return i
//        }
//        return -1
//    }
//
//    private fun isNextLine(j: Int, arr: CharArray): Int {
//        var i = j
//        if (i < arr.size - 1 && arr[i].toInt() == 10) {
//            i += 1
//            return i
//        }
//        return -1
//    }
//
//    private fun skipLines(arr: CharArray, to: Int): Int {
//        var line = 1
//        var i = 0
//        while (line < to) {
//            val k = isNextLine(i, arr)
//            if (k >= 0) {
//                i = k
//                line++
//            }
//        }
//        return i
//    }
//    // Исправить немного для прохождения по строчкам
//    fun getTokens(code: String, fileName: String, interval: IntRange): MutableList<Token> {
//        id = 0
//        var i = 0
//
//        val tokenList = mutableListOf<Token>()
//        val word = StringBuilder()
//        val arr = code.toCharArray()
//
//        skipLines(arr, interval.first)
//        var line = interval.first
//
//        while (line in interval && i < arr.size) {
//            var k = isNextLine(i, arr)
//            if (k >= 0) {
//                i = k
//                line++
//            } else {
//                val ch = arr[i]
//                k = isComment(i, arr)
//                if (k >= 0) {
//                    i = k
//                    line++
//                } else {
//                    if (isSeparator(ch)) {
//                        addWord(tokenList, word, fileName, line, i)
//                        if (uselessChar(ch)) {
//                            tokenList.add(Token(id, fileName, line, i..i, ch.toString()))
//                            id++
//                        }
//                    } else {
//                        word.append(ch)
//                    }
//
//                    i++
//                }
//            }
//        }
//        addWord(tokenList, word, fileName, line, i)
//        return tokenList
//    }
//
//    fun getFuns() {
//
//    }
//
//    fun getTokens(file: File, fileName: String, interval: IntRange): MutableList<Token> {
//        val code  = file.readText()
//        return getTokens(code, fileName, interval)
//    }
}