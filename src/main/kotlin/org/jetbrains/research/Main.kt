package org.jetbrains.research

import org.jetbrains.research.detectors.CloneDetector
import java.io.File

fun main() {
//    val file = R.getByPath("/Simple5.txt")
    val detector = CloneDetector()
    for (i in 1..5) {
        val name = "/Simple$i.txt"
        val file = R.getByPath(name)
        val reports = detector.scanFile(file, name, file.hashCode())

        println("\n")
        println(name)
        println("\n")
        for ((ind, r) in reports.withIndex()) {
            println("fun$ind")
            println(r.complexReport())
        }
    }

    val file  = R.getByPath("/biroots1")
    val reports = detector.scanFile(file, "biroots1.kt", file.hashCode())

    for (r in reports) {
        println(r.complexReport())
    }
}

object R{
    fun getByPath(name: String): File {
        val p = this::class.java.getResource(name)
        return File(p.file)
    }
}