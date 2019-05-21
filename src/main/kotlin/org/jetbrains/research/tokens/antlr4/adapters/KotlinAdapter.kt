package org.jetbrains.research.tokens.antlr4.adapters

import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Token
import org.jetbrains.research.tokens.Function
import org.jetbrains.research.tokens.antlr4.antlr.kotlin.KotlinLexer
import org.jetbrains.research.tokens.antlr4.antlr.kotlin.KotlinParser

class KotlinAdapter(private val tokens: MutableList<Token>): Adapter {

//    fun createTokens(): List<Token> {
//        val lexer = KotlinLexer(charStream)
//        val tokens = CommonTokenStream(lexer)
//        val parser = KotlinParser(tokens)
//        parser.kotlinFile()
//    }


    override fun split(): List<Function> {
        cleanNL()
        cleanComment()

        var start = -1
        var example = false
        val functions = mutableListOf<Function>()

        for ((i, token) in tokens.withIndex()) {
            if (token.type == 59 || i == tokens.size - 1 ||
                (token.type == 139 && token.text == "@Example")
            ) {
                if (start != -1 && !example) {
                    val t = tokens.subList(start, i)
                    val name = tokens[start + 1].text
                    functions.add(Function(name, t))
                }
                example = if( token.text == "@Example") {
                    true
                } else {
                    start = if (example) -1 else i
                    false
                }
            }
        }
        return functions
    }

    private fun cleanNL() {
        tokens.removeIf { it.type == 5 }
    }

    private fun cleanComment() {
        tokens.removeIf { it.type == 2 || it.type == 3 }
    }
}