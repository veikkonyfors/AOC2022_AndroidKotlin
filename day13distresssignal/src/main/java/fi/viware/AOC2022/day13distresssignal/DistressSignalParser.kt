package fi.viware.AOC2022.day13distresssignal


// This is not working. Used listOf approach in handler instead
class DistressSignalParserNoNo(var listLine:String) {
    var listSignal:MutableList<Any> = mutableListOf()

    init {
        listSignal = mutableListOf()
    }

    //lateinit var newListItem:MutableList<Any>

    fun parse(signalAsList:MutableList<Char> = listLine.toMutableList()):MutableList<Any>{
        var newListItem:MutableList<Any> = mutableListOf()
        while(!signalAsList.isEmpty()){
            val c=signalAsList.first()
            if (c == '[') { // New list item
                signalAsList.removeFirst()
                newListItem=parse(signalAsList)
            }

            if(c.isDigit()) {
                newListItem.add(c.digitToInt())
                signalAsList.removeFirst()
            }

            if(c==',') { signalAsList.removeFirst() }

            if (c == ']') { // End of list item
                listSignal.add(newListItem.toList())
                signalAsList.removeFirst()
                //return listSignal
                return newListItem.toMutableList()
            }

        }

        return  listSignal
    }

    /*
    fun parse(line:String):MutableList<Any> {

        //line="[[1],[2,3,4]]"

        line.forEachIndexed { i, c ->
            if (c == '[') { // New list item
                newListItem = mutableListOf()
                val listItem: List<Any> = parse(line.substring(i+1))
                listSignal.add(listItem)
            }

            if (c == ']') { // End of list item
                return newListItem
            }

            if(c==',') { // Another item to current list

            }

            if(c.isDigit()) {
                newListItem.add(c.digitToInt())
            }
        }
        return listSignal
    }

     */


}