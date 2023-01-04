package fi.viware.AOC2022.day13distresssignal

class SignalPair(val leftSignal:Signal, val rightSignal:Signal, val index:Int) {
    var hasRightOrder:Boolean

    init{
        //hasRightOrder=isInRightOrder()
        hasRightOrder=false
    }

    /**
     * Compare, compare left and right signal
     *
     * Return   -1 if left is smaller
     *          1 if right is smaller
     *          0 if they are equal
     *
     *          Called from isInRightOrder() for the outermost signalpair
     *          Compare will then traverse recursively into subsignals until
     *          left or right is bigger.
     *          isInRightOrder() will then return true if left one was samller
     *          (or ran out of items), false in case of the right one meeting those
     *          conditions.
     */
    fun compare():Int {
        // Go through all list items on left
        // Whether right one runs out or overflows will be checked in future
        // On each item, if left is smaller, true will be returned
        println(" Starting to compare $leftSignal and $rightSignal")
        leftSignal.listSignal.forEachIndexed { i, any ->
            val left=leftSignal.listSignal[i]

            // If right signal ran out, signal's not in right order
            if(rightSignal.listSignal.size < i+1) {
                hasRightOrder=false;
                println("Right ran out: i: $i: left $left and right rightSignal.listSignal[i]. hasRightOrder:$hasRightOrder")
                return 1}

            val right=rightSignal.listSignal[i]

            // If right is empty list and left still has items, right's running out. Not in order
            if( right is List<*> && right.isEmpty() &&
                (left is MutableList<*> && !left.isEmpty() || left is Int)){
                hasRightOrder=false
                println("Empty list on right ran out: $i: left $left and right $right. hasRightOrder:$hasRightOrder")
                return 1
            }

            // If both left and right has a list element here, compare those as SignalPairs
            if( left is MutableList<*> && right is MutableList<*> ) {
                val cmp:Int=SignalPair(Signal(left),Signal(right),i).compare()
                hasRightOrder=cmp==-1
                println("Compared List elements i: $i: left $left and right $right. hasRightOrder:$hasRightOrder")
                if(cmp==-1 || cmp==1) return cmp
                println("i: $i, $left and $right were equal, carrying on with next elements")
            }
            // If elements on both signals are integers, compare them as such
            if(left is Int && right is Int ){
                println("Comparing Int elements i: $i: left $left and right $right")
                if(left < right) { hasRightOrder=true; return -1 }
                else if(left > right) { hasRightOrder=false; return 1}
                println("i: $i, $left and $right were equal, carrying on with next elements")
            }

            // If we have left a list and Int on right: Turn right into a list and compare
            if( left is MutableList<*> && right is Int ){
                val right2:MutableList<Int> = mutableListOf(right)
                val cmp:Int=SignalPair(Signal(left),Signal(right2),i).compare()
                hasRightOrder=cmp==-1
                println("Compared List and Int elements i: $i: left $left and right $right. hasRightOrder:$hasRightOrder")
                if(cmp==-1 || cmp==1) return cmp
                println("i: $i, $left and $right were equal, carrying on with next elements")
            }

            // If we have left an Int and a List on right: Turn left into a list and compare
            if( left is Int && right is MutableList<*> ){
                val left2:MutableList<Int> = mutableListOf(left)
                val cmp:Int=SignalPair(Signal(left2),Signal(right),i).compare()
                hasRightOrder=cmp==-1
                println("Compared Int and List elements i: $i: left $left and right $right. hasRightOrder:$hasRightOrder")
                if(cmp==-1 || cmp==1) return cmp
                println("i: $i, $left and $right were equal, carrying on with next elements")
            }

            // If we have left an empty List and a non-empty List or Int on right: left ran out -> in order
            if( left is List<*> && left.isEmpty() &&
                (right is MutableList<*> && !right.isEmpty() || right is Int)){
                println("Empty list on left ran out: i: $i: left $left and right $right. hasRightOrder:$hasRightOrder")
                hasRightOrder=true
                return -1
                println("i: $i, $left and $right were equal, carrying on with next elements")
            }
        }
        // If we come here, signals have been the same so far.
        // If there still is items on the right signal, and left already ran out, this is in right order.
        if(leftSignal.listSignal.size < rightSignal.listSignal.size) {
            println("Left ran out: index: $index: left $leftSignal and right $rightSignal. hasRightOrder:$hasRightOrder")
            hasRightOrder=true; return -1 }

        return 0  // Just in case, even if spec doesn't state whether it is in order or not
    }

    fun isInRightOrder():Boolean{
        return compare()==-1  // If left was smaller
    }

    override fun toString(): String {
        return "SignalPair: ${index.toString()}: $leftSignal, $rightSignal, hasRightOrder: $hasRightOrder"
    }
}