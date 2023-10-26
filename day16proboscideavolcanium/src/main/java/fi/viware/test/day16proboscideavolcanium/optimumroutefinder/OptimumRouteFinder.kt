package fi.viware.test.day16proboscideavolcanium.optimumroutefinder

import fi.viware.test.day16proboscideavolcanium.PathUtils
import fi.viware.test.day16proboscideavolcanium.PathUtils.bfs
import fi.viware.test.day16proboscideavolcanium.Valve

/**
 * Finds optimum path from AA onwards, resulting in most pressure released.
 *
 * An alternate solution to RouteFinder to gather better understanding on BFS, AStar and all that stuff
 * which is made use of in RouteFinder.
 * Works only for puzz1 test input.
 * Running for Puzz1 with full input runs badly out of resources. Should implement cost heuristics next.
 * Puzz2 not yet along at all.
 *
 * To Be Continued . . .
 *
 * @param inputLines
 */
//class OptimumRouteFinder(allValves: Set<Valve>, val hops: Map<String, Map<String, Int>>) {
class OptimumRouteFinder(val inputLines: List<String>) {

    val allValves = mutableSetOf<Valve>()
    init {
        parseInput()
    }

    val mapOfAllValvesByName = allValves.associateBy { it.name }

    val hops = hops()
    /**
     * Parse input lines to build up all valves.
     */
    private fun parseInput() {
        val valvePattern = Regex("Valve (\\w+)")
        val ratePattern = Regex("rate=(\\d+)")
        val tunnelsPattern = Regex("valve(s?) (.+)")

        inputLines.forEach { l ->
            val valveMatch = valvePattern.find(l)
            val valveName = valveMatch!!.groupValues!!.get(1)
            val rateMatch = ratePattern.find(l)
            val flowRate = rateMatch?.groupValues!!.get(1)!!.toInt()
            val tunnelsMatch = tunnelsPattern.find(l)
            val connectedValves = tunnelsMatch?.groupValues?.get(2)!!.split(", ")
            allValves.add(Valve(valveName, flowRate, connectedValves))
        }
    }

    val mapOfConsiderableValvesByName: Map<String, Valve> = allValves.filter { it.name == "AA" || it.flowRate > 0 }.associateBy { it.name }

    /**
     * Graph over all input valves.
     * BFS makes use of this when finding shortest paths between considerableValves
     */
    private class ValveGraph(private val mapOfAllValvesByName: Map<String, Valve>) : PathUtils.Graph<String> {
        override fun neighbours(name: String) = mapOfAllValvesByName[name]!!.tunnels
    }

    /**
     * Returns
     *  1) number of hops (i.e. minutes) from any valve to any other valve plus 1 minute for opening the valve.
     *  2) flowRate of the destination
     *
     *  Map(fromvalve,Map(tovalve,Pair(hops,toflowrate))
     */
    fun hops(): Map<String, Map<String, Pair<Int, Int>>> {
        val valveGraph = ValveGraph(mapOfAllValvesByName)
        val considerableValves = allValves.filter { it.name == "AA" || it.flowRate > 0 }

        val hopsFromAllValves = mutableMapOf<String, Map<String, Pair<Int, Int>>>()
        considerableValves.forEach { n ->
            val res = bfs(valveGraph, n.name, null)
            val cnnona = considerableValves.filter { it != n && it.name != "AA" }
            val hopsFromSingleValve = mutableMapOf<String, Pair<Int, Int>>()
            cnnona.forEach { na ->
                val tuple = res.getPath(na.name).size + 1 to na.flowRate
                hopsFromSingleValve.put(na.name, tuple) // Map of hops from single valve
            }
            hopsFromAllValves.put(n.name, hopsFromSingleValve) // Map of hops on each valve to each other valve (considerable ones only!)
        }
        return hopsFromAllValves.toMap()
    }

    fun printHops(){
        val relasedPressure: Int = 0
        hops.keys.forEach { k1 ->
            val hopsToOtherValves = hops.get(k1)
            hopsToOtherValves?.keys?.forEach { k2 ->
                println("$k1: $k2, ${hopsToOtherValves?.get(k2)}, ${mapOfConsiderableValvesByName.get(k2)}")
            }
        }
    }
    data class Status(var maximumFlow: Int) { }

    /**
     * Traverse through all possible routes described with the map hops.
     *
     * @param node Current node
     * @param totalFlow Flow that has taken place so far
     * @param nodeFlow Current nodes flow
     * @param releasedFlow Amount of flow released so far
     * @param visitedNodes Nodess that have been visited so far
     * @param timeLeft Time left for gas to flow
     * @param status Current status. Mainly keeping track of maximum flow between different routes.
     *
     */
    fun traverseRoute(node: String, totalFlow: Int, nodeFlow: Int, releasedFlow: Int, visitedNodes: MutableSet<String>, timeLeft: Int, status: Status): Int {
        visitedNodes.add(node)

        val neighbors = hops[node] ?: emptyMap()

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

        return status.maximumFlow
    }



}