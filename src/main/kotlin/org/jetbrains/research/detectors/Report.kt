package org.jetbrains.research.detectors

class Report(val matches: List<SWindow>, val subject: List<SWindow>) {

    fun complexReport(): String {
        val strReport = StringBuilder()
        val mostM = mostMatch()
        strReport.append("all uniqs= ${getUniqFiles()}")
        strReport.nl()
        strReport.append("mostMatches= $mostM")
        strReport.nl()
        strReport.append("subject= ${subject.size} matches=${matches.count{it.uniq == mostM}}")
        strReport.nl()
        strReport.append("percent match = ${compare(mostM)}")
        strReport.nl()
        return strReport.toString()
    }
    fun getUniqFiles(): Set<Int> {
        val set = mutableSetOf<Int>()
        for (sw in matches) {
            set.add(sw.uniq)
        }
        return set
    }

    fun mostMatch(): Int {
        val map = mutableMapOf<Int, Int>()
        for (sw in matches) {
            if (map.containsKey(sw.uniq)) {
                map[sw.uniq] = map[sw.uniq]!! + 1
            } else {
                map[sw.uniq] = 0
            }
        }
        return map.maxValue()
    }

    fun compare(uniq: Int): Double {
        val clones = matches.filter { it.uniq == uniq }.toMutableList()
        val mask = BooleanArray(subject.size)
        for ((i, sw) in subject.withIndex()) {
            if (sw isClone clones) {
                mask[i] = true
                clones.remove(sw)
            }
        }
        return similarity(mask)
    }

    private fun similarity(mask: BooleanArray): Double {
        var count = 0.0
        for (b in mask) {
            if (b) {
                count++
            }
        }
        return count * 100 / subject.size
    }

    private fun Map<Int, Int>.maxValue(): Int {
        var max = -1
        var maxKey = -1
        for (m in this) {
            if (m.value > max) {
                max = m.value
                maxKey = m.key
            }
        }
        return maxKey
    }

    private fun StringBuilder.nl() {
        this.append("\n")
    }
}