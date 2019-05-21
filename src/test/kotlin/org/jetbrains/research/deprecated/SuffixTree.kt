package org.jetbrains.research.deprecated

@Deprecated("Suffix tree useless for this task")
class SuffixTree(private val tokens: MutableList<String>) {
    private val root = Node(-1, -1)

    class Node(var start: Int, var end: Int) {
        internal val nextNodes = mutableListOf<Node>()

        fun addNextNode(node: Node) {
            nextNodes.add(node)
        }

        override fun toString(): String {
            return "SWindow(start=$start, end=$end)"         //, nextNodes=$nextNodes

        }
    }

    private fun extend(curN: Node, cur: Int) {
        var count = 0
        for ((index, node) in curN.nextNodes.withIndex()) {
            var i = node.start
            var t = cur
            while ((i != node.end + 1 && t != tokens.size) && tokens[i] == tokens[t]) {
                i++
                t++
                count++
            }
            if (i == node.end + 1) {
                extend(node, t)
            } else if (t != cur) {
                val n = Node(node.start, i - 1)
                node.start = i
                n.nextNodes.add(node)
                if (t != tokens.size) {
                    n.nextNodes.add(Node(t, tokens.size - 1))
                }
                curN.nextNodes[index] = n
            }
        }
        if (count == 0) {
            curN.addNextNode(Node(cur, tokens.size - 1))
        }
    }

    fun build() {
        for (i in 0 until tokens.size) {
            extend(root, i)
        }
    }

    private val mask = MutableList(tokens.size) { it == -1 }
    var clone: MutableList<String> = mutableListOf()

    var next = 0

    fun scan(clone: MutableList<String>): Double {
        this.clone.clear()
        this.clone.addAll(clone)

        var i = 0
        while (i < this.clone.size) {
            next = 0
            find(root, i)
            if (next != 0) {
                i += (next - 1)
            }
            i++
        }
        return similarity()
    }

    private fun find(curN: Node, cur: Int, index: Int = 0): Int {
        if (cur == clone.size) {
            return index
        }
        for (node in curN.nextNodes) {
            if (tokens[node.start] == clone[cur]) {
                var i = node.start
                var t = cur
                while ((i != node.end + 1 && t != clone.size) && tokens[i] == clone[t]) {
                    i++
                    t++
                    next++
                }
                if (i == node.end + 1) {
                    i = find(node, t, i)
                }
                var deg = t - cur
                while (deg != 0) {
                    mask[--i] = true
                    deg--
                }
                return i
            }
        }
        return index
    }

    private fun similarity(): Double {
        var count = 0.0
        for (b in mask) {
            if (b) {
                count++
            }
        }
        return count * 100 / tokens.size
    }

    override fun toString(): String {

        return "org.jetbrains.research.deprecated.SuffixTree()"
    }
}


//    @Test
//    fun suffixTree() {
//        ////////// SuffixTree ///////////////
//        val test3 = TokenMaker.getTokens(
//            "kt1.txt", "fun fib(n: Int): Int {\n" +
//                    "    var now = 1\n" +
//                    "    var last = 1\n" +
//                    "\n" +
//                    "    for (i in 2 until n){\n" +
//                    "        val x = now\n" +
//                    "        now += last\n" +
//                    "        last = x\n" +
//                    "    }\n" +
//                    "    return now\n" +
//                    "}", 1..6
//        )
//
//        val test4 = TokenMaker.getTokens(
//            "kt2.txt", "fun fib(n: Int): Int {\n" +
//                    "    var res = 0\n" +
//                    "    var fib1 = 1\n" +
//                    "    var fib2 = 1\n" +
//                    "    if (n <= 2) return 1\n" +
//                    "    for (i in 2 until n) {\n" +
//                    "        res = fib1 + fib2\n" +
//                    "        fib1 = fib2\n" +
//                    "        fib2 = res\n" +
//                    "    }\n" +
//                    "    return res\n" +
//                    "}", 1..6
//        )
//
//
//        val tree4 = org.jetbrains.research.deprecated.SuffixTree(test3.map { it.toString() }.toMutableList())
//        tree4.build()
//        println(tree4.scan(test4.map { it.toString() }.toMutableList()))
//    }