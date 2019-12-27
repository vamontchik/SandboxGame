import java.awt.Graphics

class Options {
    // initialized once, get simply returns the object
    val data: List<SelectionSquare> = List(SquareType.values().size) {
        SelectionSquare(
            OptionsPanel.WIDTH / 2 - Square.SQUARE_WIDTH / 2,
            OptionsPanel.TOP_PADDING + (OptionsPanel.SQUARE_PADDING + Square.SQUARE_HEIGHT) * it,
            SquareType.values()[it],
            false
        )
    }

    // get returns each subsequent invocation
    val size: Int
        get() = data.size

    fun getInitialToggled() : SelectionSquare {
        return data.filter { it.type == SquareType.BLANK }[0] // should only be one square of this type...
    }

    fun draw(g: Graphics) {
        for (square in data) {
            square.draw(g)
        }
    }
}