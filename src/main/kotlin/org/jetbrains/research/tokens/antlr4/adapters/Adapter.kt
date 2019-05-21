package org.jetbrains.research.tokens.antlr4.adapters

import org.jetbrains.research.tokens.Function

interface Adapter {
    fun split(): List<Function>
}