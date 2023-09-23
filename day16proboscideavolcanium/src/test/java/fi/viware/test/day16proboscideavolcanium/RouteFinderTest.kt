package fi.viware.test.day16proboscideavolcanium

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class RouteFinderTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun parse_input_test(){

        val inputFile = File(".","input_test")
        val inputLines = inputFile.readLines()

        val routeFinder = RouteFinder(inputLines)
        val r = routeFinder.listAllValves()
        println(r)
        assertEquals("Name=AA, flowRate=0, tunnels=[DD, II, BB], Name=BB, flowRate=13, tunnels=[CC, AA], Name=CC, flowRate=2, tunnels=[DD, BB], Name=DD, flowRate=20, tunnels=[CC, AA, EE], Name=EE, flowRate=3, tunnels=[FF, DD], Name=FF, flowRate=0, tunnels=[EE, GG], Name=GG, flowRate=0, tunnels=[FF, HH], Name=HH, flowRate=22, tunnels=[GG], Name=II, flowRate=0, tunnels=[AA, JJ], Name=JJ, flowRate=21, tunnels=[II]",r)
    }

    @Test
    fun parse_input(){

        val inputFile = File(".","input")
        val inputLines = inputFile.readLines()

        val routeFinder = RouteFinder(inputLines)
        val r = routeFinder.listAllValves()
        println(r)
        assertEquals("Name=MO, flowRate=0, tunnels=[QM, ED], Name=JB, flowRate=0, tunnels=[MH, ZU], Name=BA, flowRate=0, tunnels=[XY, FF], Name=UW, flowRate=0, tunnels=[EU, SX], Name=VS, flowRate=0, tunnels=[MH, QW], Name=IK, flowRate=0, tunnels=[KF, SK], Name=EU, flowRate=10, tunnels=[DX, UW, RY, NC], Name=OA, flowRate=0, tunnels=[SX, FF], Name=NC, flowRate=0, tunnels=[ZZ, EU], Name=YB, flowRate=0, tunnels=[EO, KF], Name=VI, flowRate=0, tunnels=[FF, KF], Name=KQ, flowRate=0, tunnels=[TZ, QL], Name=WU, flowRate=0, tunnels=[NT, NW], Name=IE, flowRate=0, tunnels=[UQ, ZU], Name=UQ, flowRate=0, tunnels=[IE, VC], Name=KF, flowRate=7, tunnels=[YB, RZ, IK, PG, VI], Name=XY, flowRate=18, tunnels=[WZ, DG, BA, ZZ, PN], Name=MJ, flowRate=0, tunnels=[SX, PN], Name=KJ, flowRate=0, tunnels=[QW, ZU], Name=VC, flowRate=16, tunnels=[UQ, HN], Name=SO, flowRate=0, tunnels=[NW, PW], Name=NW, flowRate=3, tunnels=[TY, WI, ED, SO, WU], Name=SZ, flowRate=0, tunnels=[YQ, FF], Name=KU, flowRate=0, tunnels=[WI, MH], Name=QL, flowRate=9, tunnels=[KQ, DW, DX], Name=JF, flowRate=0, tunnels=[NK, NT], Name=KD, flowRate=0, tunnels=[JK, NQ], Name=ED, flowRate=0, tunnels=[NW, MO], Name=SX, flowRate=21, tunnels=[JK, MJ, OA, UW], Name=GD, flowRate=0, tunnels=[ZT, NT], Name=ZU, flowRate=19, tunnels=[KJ, JB, DN, IE], Name=HN, flowRate=0, tunnels=[QW, VC], Name=DN, flowRate=0, tunnels=[UX, ZU], Name=TZ, flowRate=17, tunnels=[KQ], Name=RY, flowRate=0, tunnels=[EU, UL], Name=MH, flowRate=15, tunnels=[KU, JB, VS, NK, GA], Name=FF, flowRate=12, tunnels=[UL, SZ, OA, VI, BA], Name=NK, flowRate=0, tunnels=[MH, JF], Name=HR, flowRate=0, tunnels=[AA, SA], Name=PG, flowRate=0, tunnels=[KF, TY], Name=PN, flowRate=0, tunnels=[XY, MJ], Name=UX, flowRate=0, tunnels=[DN, NT], Name=WZ, flowRate=0, tunnels=[NQ, XY], Name=DG, flowRate=0, tunnels=[SL, XY], Name=XM, flowRate=0, tunnels=[AA, GA], Name=UL, flowRate=0, tunnels=[FF, RY], Name=AA, flowRate=0, tunnels=[PW, ZT, XM, SK, HR], Name=GA, flowRate=0, tunnels=[MH, XM], Name=PW, flowRate=0, tunnels=[SO, AA], Name=NQ, flowRate=25, tunnels=[YQ, WZ, KD], Name=SA, flowRate=0, tunnels=[HR, QM], Name=QW, flowRate=23, tunnels=[KJ, HN, VS], Name=SK, flowRate=0, tunnels=[IK, AA], Name=YQ, flowRate=0, tunnels=[SZ, NQ], Name=ZT, flowRate=0, tunnels=[GD, AA], Name=QM, flowRate=8, tunnels=[SL, SA, EO, DW, MO], Name=NT, flowRate=13, tunnels=[WU, UX, RZ, JF, GD], Name=JK, flowRate=0, tunnels=[SX, KD], Name=SL, flowRate=0, tunnels=[DG, QM], Name=WI, flowRate=0, tunnels=[KU, NW], Name=EO, flowRate=0, tunnels=[QM, YB], Name=DW, flowRate=0, tunnels=[QM, QL], Name=DX, flowRate=0, tunnels=[EU, QL], Name=RZ, flowRate=0, tunnels=[NT, KF], Name=TY, flowRate=0, tunnels=[NW, PG], Name=ZZ, flowRate=0, tunnels=[XY, NC]",r)
        assertEquals(66, routeFinder.allValves.size)
    }

    @Test
    fun mapOfAllValvesByName_test(){
        val inputFile = File(".","input_test")
        val inputLines = inputFile.readLines()

        val routeFinder = RouteFinder(inputLines)
        val a = routeFinder.mapOfAllValvesByName
        println(a)
        assertEquals("{AA=Name=AA, flowRate=0, tunnels=[DD, II, BB], BB=Name=BB, flowRate=13, tunnels=[CC, AA], CC=Name=CC, flowRate=2, tunnels=[DD, BB], DD=Name=DD, flowRate=20, tunnels=[CC, AA, EE], EE=Name=EE, flowRate=3, tunnels=[FF, DD], FF=Name=FF, flowRate=0, tunnels=[EE, GG], GG=Name=GG, flowRate=0, tunnels=[FF, HH], HH=Name=HH, flowRate=22, tunnels=[GG], II=Name=II, flowRate=0, tunnels=[AA, JJ], JJ=Name=JJ, flowRate=21, tunnels=[II]}", a.toString())
    }

    @Test
    fun hops_test(){
        val inputFile = File(".","input_test")
        val inputLines = inputFile.readLines()
        val routeFinder = RouteFinder(inputLines)
        val a = routeFinder.hops()
        println(a)
        assertEquals("{AA={BB=2, CC=3, DD=2, EE=3, HH=6, JJ=3}, BB={CC=2, DD=3, EE=4, HH=7, JJ=4}, CC={BB=2, DD=2, EE=3, HH=6, JJ=5}, DD={BB=3, CC=2, EE=2, HH=5, JJ=4}, EE={BB=4, CC=3, DD=2, HH=4, JJ=5}, HH={BB=7, CC=6, DD=5, EE=4, JJ=8}, JJ={BB=4, CC=5, DD=4, EE=5, HH=8}}",a.toString())
    }

    @Test
    fun hops(){
        val inputFile = File(".","input")
        val inputLines = inputFile.readLines()
        val routeFinder = RouteFinder(inputLines)
        val a = routeFinder.hops()
        println(a)

        assertEquals("{EU={KF=6, XY=4, VC=14, NW=8, QL=3, SX=3, ZU=11, TZ=5, MH=11, FF=4, NQ=6, QW=13, QM=5, NT=8}, KF={EU=6, XY=5, VC=9, NW=4, QL=6, SX=5, ZU=6, TZ=8, MH=6, FF=3, NQ=6, QW=8, QM=4, NT=3}, XY={EU=4, KF=5, VC=13, NW=7, QL=6, SX=4, ZU=10, TZ=8, MH=10, FF=3, NQ=3, QW=12, QM=4, NT=7}, VC={EU=14, KF=9, XY=13, NW=8, QL=13, SX=13, ZU=4, TZ=15, MH=5, FF=11, NQ=14, QW=3, QM=11, NT=7}, NW={EU=8, KF=4, XY=7, VC=8, QL=6, SX=8, ZU=6, TZ=8, MH=4, FF=6, NQ=9, QW=6, QM=4, NT=3}, QL={EU=3, KF=6, XY=6, VC=13, NW=6, SX=5, ZU=11, TZ=3, MH=9, FF=6, NQ=8, QW=11, QM=3, NT=8}, SX={EU=3, KF=5, XY=4, VC=13, NW=8, QL=5, ZU=10, TZ=7, MH=10, FF=3, NQ=4, QW=12, QM=7, NT=7}, ZU={EU=11, KF=6, XY=10, VC=4, NW=6, QL=11, SX=10, TZ=13, MH=3, FF=8, NQ=11, QW=3, QM=9, NT=4}, TZ={EU=5, KF=8, XY=8, VC=15, NW=8, QL=3, SX=7, ZU=13, MH=11, FF=8, NQ=10, QW=13, QM=5, NT=10}, MH={EU=11, KF=6, XY=10, VC=5, NW=4, QL=9, SX=10, ZU=3, TZ=11, FF=8, NQ=11, QW=3, QM=7, NT=4}, FF={EU=4, KF=3, XY=3, VC=11, NW=6, QL=6, SX=3, ZU=8, TZ=8, MH=8, NQ=4, QW=10, QM=6, NT=5}, AA={EU=8, KF=4, XY=7, VC=8, NW=4, QL=6, SX=8, ZU=6, TZ=8, MH=4, FF=6, NQ=9, QW=6, QM=4, NT=4}, NQ={EU=6, KF=6, XY=3, VC=14, NW=9, QL=8, SX=4, ZU=11, TZ=10, MH=11, FF=4, QW=13, QM=6, NT=8}, QW={EU=13, KF=8, XY=12, VC=3, NW=6, QL=11, SX=12, ZU=3, TZ=13, MH=3, FF=10, NQ=13, QM=9, NT=6}, QM={EU=5, KF=4, XY=4, VC=11, NW=4, QL=3, SX=7, ZU=9, TZ=5, MH=7, FF=6, NQ=6, QW=9, NT=6}, NT={EU=8, KF=3, XY=7, VC=7, NW=3, QL=8, SX=7, ZU=4, TZ=10, MH=4, FF=5, NQ=8, QW=6, QM=6}}",a.toString())

    }

    @Test
    fun solve_Puzz1_test(){
        val inputFile = File(".","input_test")
        val inputLines = inputFile.readLines()
        val routeFinder = RouteFinder(inputLines)
        val a = routeFinder.solvePuzz1()
        println(a)
        assertEquals(1651,a)
    }

    @Test
    fun solve_Puzz1(){
        val inputFile = File(".","input")
        val inputLines = inputFile.readLines()
        val routeFinder = RouteFinder(inputLines)
        val a = routeFinder.solvePuzz1()
        println(a)
        assertEquals(1944,a)
    }

    @Test
    fun generic() {


        val numbers = listOf(1, 2, 3, 4, 5, -3)
        val strings = listOf("1", "2", "3", "4", "5", "-3", "4")

        val firstEven = findFirstInt(numbers) { it % 2 == 0 }
        val firstNegative = findFirst(numbers) { it < 0.3 }

        val first4 = findFirst(strings) { it == "4" }

        println("$firstEven, $firstNegative, $first4")
    }

    fun <T> findFirst(list: List<T>, predicate: (T) -> Boolean): T? {
        for (element in list) {
            if (predicate(element)) {
                return element
            }
        }
        return null
    }

    fun findFirstInt(list: List<Int>, predicate: (Int) -> Boolean): Int? {
        for (element in list) {
            if (predicate(element)) {
                return element
            }
        }
        return null
    }

    interface Graph<T> {
        /**
         * Returns all reachable neighbours from the given node id.
         */
        fun neighbours(id: T): List<T>
    }
}