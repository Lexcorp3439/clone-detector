package org.jetbrains.research.testUtils

import org.antlr.v4.runtime.Token
import org.jetbrains.research.postgresql.DataConnector
import org.jetbrains.research.detectors.Report
import org.jetbrains.research.detectors.SWBuilder
import org.jetbrains.research.tokens.Tokenizer

class SWScanner() {
    private val file1 = "file1"
    private val file2 = "file2"
    private val uniq1 = 1
    private val uniq2 = 2

    fun scan(text1: String, text2: String, pair: Pair<Int, Int> = 8 to 1): Report {
        val tokens1 = Tokenizer.createByCode(text1, file1, uniq1).split()
        val tokens2 = Tokenizer.createByCode(text2, file2, uniq2).split()

        val child1 = SWBuilder().build(uniq1, file1, tokens1.first().tokens, pair.first, pair.second)
        val child2 = SWBuilder().build(uniq2, file2, tokens2.first().tokens, pair.first, pair.second)

        val report = Report(child1, child2)
        return report
    }

//    fun scanFile(file: File, fileName: String, uniq: Int, pair: Pair<Int, Int> = 8 to 1): List<Report> {
//        val text = file.readText()
//        val reports = Tokenizer.createByCode(text, fileName, uniq).splitFun().asSequence()
//            .map { function -> scan(fileName, function.hashCode() xor uniq, function.tokens, pair) }.toList()
//        val swLists = reports.asSequence().flatMap { report -> report.subject.asSequence() }.toList()
//        DataConnector.insert(swLists)
//        return reports
//    }

    private fun scan(fileName: String, uniq: Int, tokens: List<Token>, pair: Pair<Int, Int>): Report {
        val child = SWBuilder().build(uniq, fileName, tokens, pair.first, pair.second)
        val report = child.asSequence()
            .flatMap { window -> DataConnector.getByHash(window.hash).asSequence() }.toList()
        return Report(report, child)
    }
}