package org.jetbrains.research.tokens

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.jetbrains.research.tokens.antlr4.antlr.kotlin.KotlinLexer
import org.jetbrains.research.tokens.antlr4.antlr.kotlin.KotlinParser
import java.nio.charset.Charset
import java.nio.file.Paths

object Tokenizer {
    private fun create(charStream: CharStream, fileName: String, uniq: Int): Tokens {
        val lexer = KotlinLexer(charStream)
        val tokens = CommonTokenStream(lexer)
        val parser = KotlinParser(tokens)
        parser.kotlinFile()
        return Tokens(fileName, uniq, tokens.tokens)
    }

    fun createByCode(text: String, fileName: String, uniq: Int): Tokens {
        val charStream = CharStreams.fromString(text)
        return create(charStream, fileName, uniq)
    }

    fun createByPath(path: String, fileName: String, uniq: Int): Tokens {
        val charStream = CharStreams.fromPath(
            Paths
                .get(path), Charset.defaultCharset()
        )
        return create(charStream, fileName, uniq)
    }

    enum class Language {
        KOTLIN, JAVA, HASKELL
    }

    annotation class Example
}