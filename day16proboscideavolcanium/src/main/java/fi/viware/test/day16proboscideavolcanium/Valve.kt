package fi.viware.test.day16proboscideavolcanium

class Valve(val name: String, val flowRate: Int, val tunnels: List<String>) {

    override fun toString(): String {
        return "Name=$name, flowRate=$flowRate, tunnels=$tunnels"
    }

}