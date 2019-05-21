package org.jetbrains.research.deprecated

import org.jetbrains.research.detectors.SWindow
import java.io.*
import java.nio.ByteBuffer

fun ByteArray.addAll(start: Int, bytes: ByteArray) {
    for (i in 0 until bytes.size) {
        this[i + start] = bytes[i]
    }
}

fun copy(interval: IntRange, bytes: ByteArray): ByteArray {
    val cp = ByteArray(interval.last - interval.first + 1)
    for (i in 0..interval.last - interval.first) {
        cp[i] = bytes[i + interval.first]
    }
    return cp
}
@Throws(IOException::class)
fun writeToByteArray(list: MutableList<SWindow>): ByteArray {
    val bytes = ByteArray(24 * list.size)
    var j = 0
    for (element in list) {
        bytes.addAll(j , intToByte(element.from))
        j += 4
        bytes.addAll(j , intToByte(element.to))
        j += 4
//        bytes.addAll(j, intToByte(element.firstI))
//        j += 4
//        bytes.addAll(j, doubleToByte(element.weight))
//        j += 8
//        bytes.addAll(j, intToByte(element.id))
//        j += 4
    }
    return bytes
}

// read from byte array
@Throws(IOException::class, ClassNotFoundException::class)
fun readByteArray(bytes: ByteArray): MutableList<SWindow> {
    val result = mutableListOf<SWindow>()
    var i = 0
    while (i < bytes.size - 1) {
        val from = byteToInt(copy(i..i + 3, bytes))
        i += 4
        val to = byteToInt(copy(i..i + 3, bytes))
        i += 4
        val firstI =
            byteToInt(copy(i..i + 3, bytes))
        i += 4
        val weight =
            byteToDouble(copy(i..i + 7, bytes))
        i += 8
        val id = byteToInt(copy(i..i + 3, bytes))
        i += 4
//        result.add(SWindow(from, to, firstI, weight, id))
    }
    return result
}

fun intToByte(n: Int) : ByteArray {
    return ByteBuffer.allocate(4).putInt(n).array()
}

fun byteToInt(bytes: ByteArray) : Int {
    val byteBuffer = ByteBuffer.wrap(bytes)
    return byteBuffer.int
}

fun doubleToByte(n: Double) : ByteArray {
    val long = java.lang.Double.doubleToLongBits(n)
    val bytes = ByteBuffer.allocate(8).putLong(long).array()
    return bytes
}

fun byteToDouble(bytes: ByteArray) : Double {
    val byteBuffer = ByteBuffer.wrap(bytes)
    val l = byteBuffer.long
    return java.lang.Double.longBitsToDouble(l)
}