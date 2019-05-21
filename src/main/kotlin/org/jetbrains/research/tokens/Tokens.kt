package org.jetbrains.research.tokens

import org.antlr.v4.runtime.Token
import org.jetbrains.research.tokens.antlr4.adapters.KotlinAdapter

class Tokens(val fileName: String, val uniq: Int, val tokens: MutableList<Token>) {

    fun split(): List<Function> {
        val adapter = KotlinAdapter(tokens)
        val func = adapter.split()
        for (f in func) {
            println(f.name)
        }
        return adapter.split()
    }

    override fun toString(): String {
        return "Tokens(fileName='$fileName', uniq=$uniq, tokens=$tokens)"
    }
}