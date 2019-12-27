import java.awt.Graphics

class Grid {
    private var grid: List<List<Square>> = List(GridPanel.GRID_WIDTH) { columnIndex: Int ->
        List(GridPanel.GRID_HEIGHT) {
            Square(
                it * Square.SQUARE_WIDTH,
                columnIndex * Square.SQUARE_HEIGHT,
                SquareType.BLANK
            )
        }
    }

    fun flatten(): List<Square> {
        return grid.flatten()
    }

    fun draw(g: Graphics) {
        for (row in grid) {
            for (square in row) {
                square.draw(g)
            }
        }
    }

    fun copy(): List<List<Square>> {
        return grid.toList() // toList() call makes a new copy
    }

    fun setState(grid: List<List<Square>>) {
        this.grid = grid     // old copy gets sent to GC
    }
}