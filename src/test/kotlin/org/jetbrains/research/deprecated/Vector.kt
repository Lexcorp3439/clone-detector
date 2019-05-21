package org.jetbrains.research.deprecated

import org.jetbrains.research.detectors.SWindow

@Deprecated("late version")
class Vector(
    val fileName: String, val from: Int, val to: Int, val uniq: Int,
    val vector: MutableList<SWindow>
) {

    fun getByteArray(): ByteArray {
        return writeToByteArray(vector)
    }


//    class SWindow(var from: Int, var to: Int, var firstI: Int, var weight: Double, var id: Int) {
//
//        companion object {
//            fun build(from: Int, to: Int, firstI: Int, weight: Double, id: Int): SWindow {
//                return SWindow(from, to, firstI, weight, id)
//            }
//        }
//
//        override fun equals(other: Any?): Boolean {
//            if (this === other) return true
//            if (javaClass != other?.javaClass) return false
//
//            other as SWindow
//
//            if (weight != other.weight) return false
//
//            return true
//        }
//
//        override fun hashCode(): Int {
//            return weight.hashCode()
//        }
//
//        override fun toString(): String {
//            return "SWindow(from=$from, to=$to, firstI=$firstI, weight=$weight, id=$id)"
//        }
//
//
////        override fun toString(): String {
////            return "SWindow(interval=$interval, firstI=$firstI, weight=$weight, id=$id)"
////        }
//    }

    override fun toString(): String {
        return "Vector(fileName='$fileName', from=$from, to=$to, uniq=$uniq, vector=$vector)"
    }

//    override fun toString(): String {
//        return "Vector(fileName='$fileName', interval=$interval, uniq=$uniq, vector=$vector)"
//    }

}