package org.jetbrains.research.intefaces

import java.io.File

interface Detector {
    fun setCode(
        example: String, fileName: String, interval: IntRange,
        type: Builder.Type, id: Int, pair: Pair<Int, Int> = 5 to 1
    )
    fun setCode(
        file: File, fileName: String, interval: IntRange,
        type: Builder.Type, id: Int, pair: Pair<Int, Int> = 5 to 1
    )

    fun scan(example: String, fileName: String, interval: IntRange,
             type: Builder.Type, id: Int, pair: Pair<Int, Int>): Any
}