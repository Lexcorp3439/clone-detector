package org.jetbrains.research.detectors

import org.antlr.v4.runtime.Token
import org.jetbrains.research.tokens.Function
import org.jetbrains.research.tokens.Tokens

class SWBuilder {
    private var step = 0
    private var size = 0

    fun build(
        uniq: Int,
        fileName: String,
        tokens: List<Token>,
        size: Int,
        step: Int
    ): List<SWindow> {
        this.size = size
        this.step = step
        return hashing(uniq, fileName, tokens)
    }

    fun build(     //ByTokens
        tokens: Tokens,
        size: Int,
        step: Int
    ): List<SWindow> {
        return build(tokens.uniq, tokens.fileName, tokens.tokens, size, step)
    }

    fun build(    //ByFunctions
        uniq: Int,
        fileName: String,
        func: Function,
        size: Int,
        step: Int
    ): List<SWindow> {
        return build(uniq, fileName, func.tokens, size, step)
    }

    private fun hashing(
        uniq: Int,
        fileName: String,
        tokens: List<Token>
    ): List<SWindow> {
        val vector = mutableListOf<SWindow>()
        val stopIndex = tokens.size - size
        for (i in 0 .. stopIndex step step) {
            var hash = 0.0
            val stop = i + size - 1
            for (l in i..stop) {
                hash = hash * 31 + tokens[l].type.hashCode()
            }
            vector.add(SWindow(uniq, fileName,from = i,to = stop, hash = hash))
        }
        return vector
    }

//    val vector = mutableListOf<SWindow>()
//    val a = 31
//    var tail = 0.0
//    for (i in 0 until tokens.size - size step step) {
//        var hash = 0.0
//        var nose = 0.0
//        val stop = i + size - 1
//        for (l in i..stop) {
//            if (l - i < 2) {
//                nose += tokens[l].type.hashCode() * Math.pow(a.toDouble(), (size - l - i).toDouble())
//            } else {
//                tail += tokens[l].type.hashCode() * Math.pow(a.toDouble(), (size - l - i).toDouble())
//            }
//            hash = nose + tail
//        }
//        vector.add(SWindow(uniq, fileName,from = i,to = stop, hash = hash))
//    }
}