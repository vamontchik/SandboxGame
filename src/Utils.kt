//fun List<List<Square>>.copy(): List<List<Square>> {
//    val localCopy: MutableList<MutableList<Square>> = mutableListOf()
//    for (innerList in this) {
//        val newInnerList: MutableList<Square> = mutableListOf()
//        for (square in innerList) {
//            newInnerList.add(square.copy())
//        }
//    }
//    return localCopy
//}