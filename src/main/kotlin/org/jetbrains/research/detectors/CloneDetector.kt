package org.jetbrains.research.detectors

import org.jetbrains.research.postgresql.DataConnector
import org.jetbrains.research.tokens.Function
import org.jetbrains.research.tokens.Tokenizer
import java.io.File

class CloneDetector {

    fun scanFunc(text: String, fileName: String, uniq: Int, pair: Pair<Int, Int> = 8 to 1): Report {
        val func = Tokenizer.createByCode(text, fileName, uniq).split().first()
        val report = scan(fileName, uniq, func, pair)
        DataConnector.insert(report.subject)
        return report
    }

    fun scanFile(file: File, fileName: String, uniq: Int, pair: Pair<Int, Int> = 8 to 1): List<Report> {
        val functions = Tokenizer.createByCode(file.readText(), fileName, uniq).split()

        val reports = functions.asSequence()
            .map { function ->
                scan(fileName, function.hashCode() xor uniq, function, pair)
            }.toList()

        val swLists = reports.asSequence()
            .flatMap { report ->
                report.subject.asSequence()
            }.toList()

        DataConnector.insert(swLists)
        return reports
    }

    private fun scan(fileName: String, uniq: Int, func: Function, pair: Pair<Int, Int>): Report {
        val child = SWBuilder().build(uniq, fileName, func.tokens, pair.first, pair.second)
        val report = child.asSequence()
            .flatMap { window ->
                DataConnector.getByHash(window.hash).asSequence()
            }.toList()
        return Report(report, child)
    }
}