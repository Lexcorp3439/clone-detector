package org.jetbrains.research

import org.jetbrains.research.detectors.CloneDetector
import org.jetbrains.research.testUtils.DataConnectorTest
import org.jetbrains.research.testUtils.SWScanner
import org.jetbrains.research.tokens.Tokenizer
import org.jetbrains.research.tokens.antlr4.adapters.KotlinAdapter
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class HelloTest {
    @Test
    fun DBtest() {
        DataConnectorTest.connect()
        DataConnectorTest.insert(9, "ss", 45)
        DataConnectorTest.get()
    }

    @Test
    fun splitFunc() {
        val file = R.getByPath("/Simple1.txt")
        val result = listOf(27, 35, 49, 48, 21, 33, 27, 53)
        val tokens = Tokenizer.createByCode(file.readText(), "Simple1.kt", file.hashCode())
        val func = tokens.split()
        for ((i, f) in func.withIndex()) {
            assertEquals(result[i], f.tokens.size)
        }

        val text1 = "fun thirdDigit(number: Int): Int = TODO()"
        val tokens1 = Tokenizer.createByCode(text1, "Simple1.kt", text1.hashCode())
        val foo = tokens1.split()
        println(foo.first().toString())
    }

    @Test
    fun kotlinAdapter() {
        val file  = R.getByPath("/squareSequenceDigit")
        val tokens = Tokenizer.createByCode(file.readText(), "squareSequenceDigit.kt", file.hashCode())
        val adapter = KotlinAdapter(tokens.tokens)
        val func = adapter.split()
        println(func.toString())
    }

    @Test
    fun scanner() {
        val text1 = "fun numberRevert(number: Int): Int {\n" +
                "    val x = number / 100\n" +
                "    val y = (number - x * 100) / 10\n" +
                "    val z = (number - 10 * y - 100 * x)\n" +
                "    return 100 * z + 10 * y + x\n" +
                "}"
        val text2 = "fun numberRevert(number: Int): Int {\n" +
                "    val x = number / 100\n" +
                "    val y = (number - x * 100) / 10\n" +
                "    val z = (number - 10 * y - 100 * x)\n" +
                "    return 100 * z + 10 * y + x\n" +
                "}"
        val scanner1 = SWScanner()
        val report1 = scanner1.scan(text1, text2)
        assertEquals(report1.matches, report1.subject)

        val file1 = R.getByPath("/roman1")
        val file2 = R.getByPath("/roman2")

        val scanner2 = SWScanner()
        val report2 = scanner2.scan(file1.readText(), file2.readText())
        println(report2.complexReport())
        assertEquals(report2.matches, report2.subject)
    }

    @Test
    fun detector() {
        val fileN = "biroots2"
        val path = "D:\\IdeaProjects\\clone-detector\\src\\main\\kotlin\\org\\jetbrains\\research\\code\\$fileN"

        val file = File(path)
        val detector = CloneDetector()
        val res = detector.scanFile(file, fileN, path.hashCode())

        // отчет о выполнении программы
        println(res)
//        val files = detector.getUniqFiles()
//        for (elem in files) {
//            println(detector.compare(elem.first, elem.second))
//        }
    }
}
