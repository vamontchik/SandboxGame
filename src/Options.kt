import java.awt.Graphics

class Options {
    private val data: MutableList<SelectionSquare> = MutableList(SquareType.values().size) {
        SelectionSquare(
            Square(OptionsPanel.WIDTH / 2 - Square.SQUARE_WIDTH / 2,
            OptionsPanel.TOP_PADDING + (OptionsPanel.SQUARE_PADDING + Square.SQUARE_HEIGHT) * it,
            SquareType.values()[it]),
            false
        )
    }

    private var current: Int = 0.also { setNewToggle(it) }

    fun draw(g: Graphics) {
        data.forEach { it.draw(g) }
    }

    fun setNewToggle(index: Int) {
        data[current] =
            SelectionSquare(
                Square(OptionsPanel.WIDTH / 2 - Square.SQUARE_WIDTH / 2,
                OptionsPanel.TOP_PADDING + (OptionsPanel.SQUARE_PADDING + Square.SQUARE_HEIGHT) * current,
                SquareType.values()[current]),
                false
            )

        data[index] =
            SelectionSquare(
                Square(OptionsPanel.WIDTH / 2 - Square.SQUARE_WIDTH / 2,
                OptionsPanel.TOP_PADDING + (OptionsPanel.SQUARE_PADDING + Square.SQUARE_HEIGHT) * index,
                SquareType.values()[index]),
                true
            )

        current = index
    }

    fun findSquareIndex(x: Int, y: Int): Int? {
        for (i in 0 until data.size) {
            val currSelectionSquare: SelectionSquare = data[i]
            val xLowerBound: Int = currSelectionSquare.square.x
            val xUpperBound: Int = currSelectionSquare.square.x + Square.SQUARE_WIDTH
            val yLowerBound: Int = currSelectionSquare.square.y
            val yUpperBound: Int = currSelectionSquare.square.y + Square.SQUARE_HEIGHT
            if (x in xLowerBound..xUpperBound && y in yLowerBound..yUpperBound) {
                return i
            }
        }
        return null
    }

    fun getCurrentColor(): SquareType {
        return data.filter { it.isToggled }[0].square.type
    }
}