package fi.viware.test.day16proboscideavolcanium

import java.util.ArrayDeque


/**
 *  Day 16 solution mimicked from https://github.com/nibarius/aoc/tree/master/src/main/aoc2022.
 *  Having hard time to understand it all.
 *
 *  For that reason, a more individual approach OptimumRouteFinder is composed to gather better understanding.
 *  So far it only works for puzz1 with test input.
 *
 */
class RouteFinder(val inputLines: List<String>) {

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

    /**
     * Graph over all input valves.
     * BFS makes use of this when finding shortest paths between valvesToOpen
     */
    private class ValveGraph(private val allValves: Map<String, Valve>) : PathUtils.Graph<String> {
        override fun neighbours(name: String) = allValves[name]!!.tunnels
    }

    /**
     * Returns number of hops (i.e. minutes) from any valve to any other valve plus 1 minute for opening the valve.
     */
    fun hops(): Map<String, Map<String, Int>> {
        val valveGraph = ValveGraph(mapOfAllValvesByName)
        val considerableNodes = allValves.filter { it.name == "AA" || it.flowRate > 0 }

        val hopsFromAllValves = mutableMapOf<String, Map<String, Int>>()
        considerableNodes.forEach { n ->
            val res = bfs(valveGraph, n.name, null)
            val cnnona = considerableNodes.filter { it != n && it.name != "AA" }
            val hopsFromSingleValve = mutableMapOf<String, Int>()
            cnnona.forEach { na ->
                val tuple1 = na.name to res.getPath(na.name).size + 1
                hopsFromSingleValve.put(na.name, res.getPath(na.name).size + 1) // Map of hops from single valve
            }
            hopsFromAllValves.put(n.name, hopsFromSingleValve) // Map of hops on each valve to each other valve (considerable ones only!)
        }
        return hopsFromAllValves.toMap()
    }

    /**
     * Used for the different states while searching.
     * Time and pressure released needs to be tracked, but it's not used for comparisons between states.
     * @param pos List of the current positions for the actors, has either one or two values.
     * @param dist List of remaining distance until the actor reaches the position indicated in pos.
     *             Has either one or two values, at least one value is always 0.
     * @param openValves The valves that have been opened so far, in the order they were opened.
     */
    private data class State(val pos: List<String>, val dist: List<Int>, val openValves: List<String>) {
        var timeLeft = 0
        var pressureReleased = 0
    }

    /**
     * An optimized graph representing the volcano and actions that can be taken. The only actions that can be taken
     * is to walk to an unvisited valve and open the valve there. There is no need to track any intermediate valves
     * as you always walk the shortest path to the next valve you want to open.
     * @param hops The time it takes to go from one valve to another and open it (for all combinations of valves)
     * @param valves A map between valve names and valves.
     */
    private class Graph(
        val hops: Map<String, Map<String, Int>>,
        val valves: Map<String, Valve>
    ) :
        PathUtils.WeightedGraph<State> {
        val maximumFlowRate = valves.values.sumOf { it.flowRate } // todo: better name
        val numValves = valves.values.count { it.flowRate > 0 }
        override fun neighbours(id: State): List<State> {
            if (id.openValves.size == numValves) {
                // All valves are open, fast-forward to the end
                return listOf(
                    State(listOf("Done", "Done"), listOf(0, 0), id.openValves).apply {
                        timeLeft = 0
                        pressureReleased = id.pressureReleased + id.timeLeft * maximumFlowRate
                    }
                )
            }

            // Don't walk to valves that the other player is going to
            val closedValves = valves.values.filter { it.flowRate > 0 && it.name !in id.openValves + id.pos }

            // moves is a list with two elements: List of all moves for p1 and list of all moves for p2
            // Each move is name + time it takes to go there
            val moves = (id.pos zip id.dist).map { (pos, dist) ->
                if (dist != 0) {
                    // Continue the move in progress
                    listOf(pos to dist)
                } else {
                    // Start moving toward a new valve
                    if (closedValves.isEmpty() || pos == "Done") {
                        // Everything open, or already done (elephant is always in done state in part 1): do nothing.
                        listOf("Done" to id.timeLeft)
                    } else {
                        closedValves.map { it.name to hops[pos]!![it.name]!! }
                    }
                }
            }

            // Set of all possible moves pairs, each move pair is a set of my move and the elephant move
            // Each move is in turn a destination-time pair.
            val toVisit = mutableSetOf<Set<Pair<String, Int>>>()
            moves.first().forEach { me ->
                moves.last().forEach { elephant ->
                    // Never have both go to the same node at once
                    if (me.first != elephant.first) {
                        toVisit.add(setOf(me, elephant))
                    }
                }
            }

            // Create all the neighbouring states from the move sets.
            return toVisit.map { moveSet ->
                val (me, elephant) = moveSet.toList()
                // Fast-forward to when the next move is done for either actor or to when time is up.
                val nextTimeLeft = listOf(0, id.timeLeft - me.second, id.timeLeft - elephant.second).max()
                val timeTaken = id.timeLeft - nextTimeLeft
                val currentlyVenting = id.openValves.sumOf { valves[it]!!.flowRate }
                val openValves = id.openValves.toMutableList().apply {
                    // open a valve when time to destination reaches 0
                    if (me.second - timeTaken == 0) add(me.first)
                    if (elephant.second - timeTaken == 0) add(elephant.first)
                }
                State(
                    listOf(me.first, elephant.first),
                    listOf(me.second - timeTaken, elephant.second - timeTaken),
                    openValves
                ).apply {
                    timeLeft = id.timeLeft - timeTaken
                    pressureReleased = id.pressureReleased + timeTaken * currentlyVenting
                }
            }
        }

        /**
         * The cost to move between two states is the amount of pressure that is not being released
         * during the time it takes to move.
         */
        override fun cost(from: State, to: State): Float {
            val notVenting = maximumFlowRate - from.openValves.sumOf { valves[it]!!.flowRate }
            return (notVenting * (from.timeLeft - to.timeLeft)).toFloat()
        }
    }

    /**
     * Estimate remaining cost by assuming that the distance all actors have to walk to reach a valve is
     * the shortest distance to any open valve, including from the current position of a player that's
     * on its way to a valve already. Also assume that all players will open the best valves possible
     * every time.
     *
     * This never overestimates and is fairly close with my input (88% of the real cost when estimating
     * on the first node on part 2).
     */
    private fun costHeuristic(currentState: State): Float {
        var timeLeft = currentState.timeLeft
        var estimatedCost = 0

        // Closed valves ordered by the most useful first
        val closedValves = allValves.filter { it.flowRate > 0 && it.name !in currentState.openValves }
            .sortedBy { -it.flowRate }
            .toMutableList()

        if (closedValves.isEmpty()) {
            return 0f
        }

        val timeLeftToFinishStartedAction = currentState.dist.sum() // at least one is always zero
        val minDistanceBetweenValves = closedValves.minOf { node -> hops[node.name]!!.values.min() }
        val minDistanceToAValve = if (timeLeftToFinishStartedAction in 1 until minDistanceBetweenValves) {
            timeLeftToFinishStartedAction
        } else {
            minDistanceBetweenValves
        }
        var timeToNextOpen = minDistanceToAValve
        while (timeLeft > 0 && closedValves.isNotEmpty()) {
            if (timeToNextOpen == 0) {
                // Each actor that's not done can open a valve
                repeat(currentState.pos.filter { it != "Done" }.size) {
                    closedValves.removeFirstOrNull()
                }
                timeToNextOpen = closedValves.minOfOrNull { node -> hops[node.name]!!.values.min() } ?: 0
            } else {
                timeToNextOpen--
            }
            estimatedCost += closedValves.sumOf { it.flowRate }
            timeLeft--
        }
        return estimatedCost.toFloat()
    }

    fun solvePuzz1(): Int {
        val res = PathUtils.aStar(
            Graph(hops(), mapOfAllValvesByName),
            State(listOf("AA", "Done"), listOf(0, 0), listOf()).apply { timeLeft = 30 },
            { node -> node.timeLeft == 0 },
            ::costHeuristic
        )
        return res.first.pressureReleased
    }

    fun solvePuzz2(): Int {
        val res = PathUtils.aStar(
            graph = Graph(hops(), mapOfAllValvesByName),
            start = State(listOf("AA", "AA"), listOf(0, 0), listOf()).apply { timeLeft = 26 },
            goalFn = { node -> node.timeLeft == 0 },
            heuristic = ::costHeuristic
        )
        return res.first.pressureReleased
    }

    /**
     * Standard breadth first search. Good when all locations must be visited or when there is no cost
     * involved and there is no reasonable heuristic that can be used to find the distance to the goal.
     * @param graph the graph to search
     * @param start the location to start the search from
     * @param goal the location to search for, or null if the whole graph should be searched
     */
    private fun <T> bfs(graph: PathUtils.Graph<T>, start: T, goal: T?): Result<T> {
        val toCheck = ArrayDeque<T>()
        toCheck.add(start)
        val cameFrom = mutableMapOf(start to start)
        while (toCheck.isNotEmpty()) {
            val current = toCheck.removeFirst()
            if (current == goal) {
                break
            }
            for (next in graph.neighbours(current)) {
                if (next !in cameFrom) {
                    toCheck.add(next)
                    cameFrom[next] = current
                }
            }
        }
        return Result(cameFrom, mapOf())
    }

    /**
     * Holds the result of a search.
     * @param cameFrom shows how to get to a certain location. "cameFrom[B] == A" means that the path
     * to B comes from A. The start point points back to itself, so  cameFrom[A] == A.
     * @param cost a map that shows the total cost to get to all visited locations
     */
    private data class Result<T>(val cameFrom: Map<T, T>, val cost: Map<T, Float>) {
        /**
         * Gives the best path to the given location from the start point of the search (start point
         * not included in the returned path).
         */
        fun getPath(to: T): List<T> {
            return buildList {
                var current = to
                while (cameFrom[current] != null && cameFrom[current] != current) {
                    add(current)
                    current = cameFrom.getValue(current)
                }
            }.asReversed()
        }
    }


    fun listAllValves(): String{
        var retString = ""

        allValves.forEach{
            retString += "$it, "
        }
        return retString.removeSuffix(", ")
    }
}