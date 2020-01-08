import java.awt.Graphics

class Grid {
    private val grid: MutableList<MutableList<Square>> = MutableList(GridPanel.GRID_WIDTH) { columnIndex: Int ->
        MutableList(GridPanel.GRID_HEIGHT) {
            Square(
                it * Square.SQUARE_WIDTH,
                columnIndex * Square.SQUARE_HEIGHT,
                SquareType.BLANK
            )
        }
    }

    fun draw(g: Graphics) {
        for (row in grid) {
            for (square in row) {
                square.draw(g)
            }
        }
    }

//fun copy(): List<List<Square>> {
//    val localCopy: MutableList<MutableList<Square>> = mutableListOf()
//    for (innerList in this) {
//        val newInnerList: MutableList<Square> = mutableListOf()
//        for (square in innerList) {
//            newInnerList.add(square.copy())
//        }
//    }
//    return localCopy
//}

    fun findSquareIndex(x: Int, y: Int): Pair<Int, Int>? {
        if (x < 0 || x > Square.SQUARE_WIDTH * GridPanel.GRID_WIDTH ||
            y < 0 || y > Square.SQUARE_HEIGHT * GridPanel.GRID_HEIGHT) {
            return null
        }

        return Pair(x / Square.SQUARE_WIDTH, y / Square.SQUARE_HEIGHT)
    }

    fun setAt(x: Int, y: Int, toSet: SquareType) {
        grid[y][x] = Square(x * Square.SQUARE_WIDTH, y * Square.SQUARE_HEIGHT, toSet)
    }

    fun getAt(x: Int, y: Int): Square = grid[y][x]

//    fun setState(grid: List<List<Square>>) {
//        for (i in 0 until this.grid.size) {
//            for (j in 0 until this.grid[0].size) {
//                this.grid[i][j] = grid[i][j].copy()
//            }
//        }
//    }
}