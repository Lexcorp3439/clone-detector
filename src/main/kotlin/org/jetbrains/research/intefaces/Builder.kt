package org.jetbrains.research.intefaces

import org.antlr.v4.runtime.Token
import org.jetbrains.research.detectors.SWindow

interface Builder {
    fun build(from: Int, to: Int, weight: Double): SWindow
    fun buildVector(
        tokens: MutableList<Token>,
        size: Int,
        step: Int,
        type: Type = Type.HASH
    ): MutableList<SWindow>

    enum class Type{
        HASH, VECTOR
    }
}