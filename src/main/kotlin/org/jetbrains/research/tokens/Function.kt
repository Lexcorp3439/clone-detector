package org.jetbrains.research.tokens

import org.antlr.v4.runtime.Token
import java.lang.StringBuilder

class Function(val name: String, val tokens: List<Token>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Function

        if (name != other.name) return false
//        if (uniq != other.uniq) return false
        if (tokens != other.tokens) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
//        result = 31 * result + uniq
        result = 31 * result + tokens.hashCode()
        return result
    }

    override fun toString(): String {
        val strBuilder = StringBuilder()
        strBuilder.append("$name ") // uniq= $uniq")
        strBuilder.append("\n")
        println(name)
        for (t in tokens) {
            strBuilder.append("text ${t.text}    \ttype ${t.type}  ")
            strBuilder.append("\n")
        }
        strBuilder.append("\n")
        return strBuilder.toString()
    }
}