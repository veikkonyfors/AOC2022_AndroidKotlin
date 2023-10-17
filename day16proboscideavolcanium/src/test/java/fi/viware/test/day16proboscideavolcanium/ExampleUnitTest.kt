package fi.viware.test.day16proboscideavolcanium

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    data class Status(var maximumFlow: Int) {

    }

    val routeMap1 = mapOf(
        "AA" to mapOf("BB" to  Pair(2, 13),  "CC" to Pair(3, 2), "DD" to Pair(2, 20), "EE" to Pair(3, 3), "HH" to Pair(6, 22), "JJ" to Pair(3, 21)),
        "BB" to mapOf("CC" to  Pair(2, 2),  "DD" to Pair(3, 20), "EE" to Pair(4, 3), "HH" to Pair(7, 22), "JJ" to Pair(4, 21)),
        "CC" to mapOf("BB" to  Pair(2, 13),  "DD" to Pair(2, 20), "EE" to Pair(3, 3), "HH" to Pair(6, 22), "JJ" to Pair(5, 21)),
        "DD" to mapOf("BB" to  Pair(3, 13),  "CC" to Pair(2, 2), "EE" to Pair(2, 3), "HH" to Pair(5, 22), "JJ" to Pair(4, 21)),
        "EE" to mapOf("BB" to  Pair(4, 13),  "CC" to Pair(3, 2), "DD" to Pair(2, 20), "HH" to Pair(4, 22), "JJ" to Pair(5, 21)),
        "HH" to mapOf("BB" to  Pair(7, 13),  "CC" to Pair(6, 2), "DD" to Pair(5, 20), "EE" to Pair(4, 3), "JJ" to Pair(8, 21)),
        "JJ" to mapOf("BB" to  Pair(4, 13),  "CC" to Pair(5, 2), "DD" to Pair(4, 20), "EE" to Pair(5, 3), "HH" to Pair(8, 22)),
    )

    // AA DD BB JJ HH EE CC

    val routeMap2 = mapOf(
        "AA" to mapOf("DD" to Pair(2,20)),
        "DD" to mapOf("BB" to Pair(3, 13)),
        "BB" to mapOf("JJ" to Pair(4, 21)),
        "JJ" to mapOf("HH" to Pair(8, 22)),
        "HH" to mapOf("EE" to Pair(4, 3)),
        "EE" to mapOf("CC" to Pair(3, 2)),

        )

    val routeMap3 = mapOf(
        "AA" to mapOf("BB" to  Pair(2, 13), "CC" to Pair(3, 2)),
    )

    val routeMap = routeMap2


    @Test
    fun testMapTraverse() {

        val startingNode = "AA"
        val visitedNodes = mutableSetOf<String>()
        val status = Status(0)
        traverseRoute(startingNode, 0,0, 0, visitedNodes, 30, status)
        println("maximumFlow ${status.maximumFlow}")
    }

    /**
     *
     * @param node Current node
     * @param totalFlow Flow that has taken place so far
     * @param nodeFlow Current nodes flow
     * @param releasedFlow Amount of flow released so far
     * @param visitedNodes Nodess that have been visited so far
     * @param timeLeft Time left for gas to flow
     * @param status Current status. Mainly keeping track of maximum flow between different routes
     */
    fun traverseRoute(node: String, totalFlow: Int, nodeFlow: Int, releasedFlow: Int, visitedNodes: MutableSet<String>, timeLeft: Int, status: Status) {
        visitedNodes.add(node)

        val neighbors = routeMap[node] ?: emptyMap()

        if (neighbors.isEmpty() || visitedNodes.containsAll(neighbors.keys)) { // We have visited all nodes
            val flowThisRoute = totalFlow+releasedFlow*timeLeft
            if (status.maximumFlow < flowThisRoute) status.maximumFlow = flowThisRoute
            println("$node: totalFlow:  $totalFlow, releasedFlow $releasedFlow, nodeFlow, ${nodeFlow}, timeLeft ${timeLeft}")
            println("Route: $visitedNodes, flowThisRoute ${flowThisRoute}")
        } else {
            for ((neighbor, pairValue) in neighbors) {

                val (hops, flowRate) = pairValue
                if (neighbor !in visitedNodes) {
                    val newFlowRate = flowRate
                    val totalFlowNow = totalFlow + hops*(releasedFlow)
                    val releasedFlowNow = releasedFlow + flowRate
                    println("$node: totalFlowNow:  $totalFlow + $hops*($releasedFlow) = ${totalFlow + hops*(releasedFlow)}")
                    traverseRoute(neighbor, totalFlowNow, newFlowRate, releasedFlowNow, visitedNodes, timeLeft - hops, status)
                }
            }
        }
        visitedNodes.remove(node)
    }

}