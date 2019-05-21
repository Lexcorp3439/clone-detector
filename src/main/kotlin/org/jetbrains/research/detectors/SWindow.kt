package org.jetbrains.research.detectors

class SWindow(val uniq: Int, val fileName: String, val from: Int, val to: Int, val hash: Double) {

    infix fun cloneEquals(other: SWindow): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        if (hash != other.hash) return false

        return true
    }

    infix fun isClone(swList: List<SWindow>): Boolean {
        for (s in swList) {
            if (this cloneEquals s) return true
        }
        return false
    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as SWindow
//
//        if (uniq != other.uniq) return false
//        if (fileName != other.fileName) return false
//        if (from != other.from) return false
//        if (to != other.to) return false
//        if (hash != other.hash) return false
//
//        return true
//    }

    override fun hashCode(): Int {
        return hash.hashCode()
    }

    override fun toString(): String {
        return "SWindow(uniq=$uniq, fileName='$fileName', from=$from, to=$to, weight=$hash)\n"
    }
//
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SWindow

        if (hash != other.hash) return false

        return true
    }
}