package fi.viware.AOC2022.day13distresssignal

class Signal (_listSignal:Any){

    var listSignal:MutableList<Any> = mutableListOf()
    var current:Int=0

    init{
        if(_listSignal is MutableList<*> ){
            listSignal=_listSignal as MutableList<Any>
        } else { // It is plain Int. Wrap it to MutableList. Signal.listSignal always is a MutableList<Any>.
            listSignal= mutableListOf(_listSignal)
        }
        current=0
    }

    fun size():Int{
        return listSignal.size
    }

    fun next():MutableList<Any>{
        val item:MutableList<Any> = listSignal[current] as MutableList<Any>
        current+=1
        return item
    }

    override fun toString(): String {
        var ret:String=""
        if(listSignal is MutableList<*> ){

            listSignal.forEach{
                ret+=it
            }

        }
        return ret

    }
}