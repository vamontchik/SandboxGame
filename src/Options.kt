import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel

class Options : JPanel(), MouseListener {
    companion object {
        const val TOP_PADDING = 100 // 100 down from the top
        const val SQUARE_PADDING = 25 // 25 between
    }

    private val options: List<SelectionSquare> = List(SquareType.values().size) {
        SelectionSquare(
            Grid.GRID_WIDTH * Square.SQUARE_WIDTH / 2 - Square.SQUARE_WIDTH / 2,
            TOP_PADDING + (SQUARE_PADDING + Square.SQUARE_HEIGHT) * it,
            SquareType.values()[it],
            false
        )
    }

    var currSelected: SelectionSquare = options[0].also { it.isToggled = true } // 0 == BLANK
        private set

    init {
        // set preferredSize for pack() in the JFrame
        preferredSize = Dimension(
            Grid.GRID_WIDTH * Square.SQUARE_WIDTH, Grid.GRID_HEIGHT * Square.SQUARE_HEIGHT
        )

        // register mouse listener
        addMouseListener(this)
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null) return

        super.paintComponent(g)

        for (square in options) {
            square.draw(g)
        }
    }

    private fun findSquare(x: Int, y: Int): SelectionSquare? {
        for (square in options) {
            val xLowerBound: Int = square.x
            val xUpperBound: Int = square.x + Square.SQUARE_WIDTH
            val yLowerBound: Int = square.y
            val yUpperBound: Int = square.y + Square.SQUARE_HEIGHT
            if (x in xLowerBound..xUpperBound && y in yLowerBound..yUpperBound) {
                return square
            }
        }
        return null
    }

    override fun mouseReleased(e: MouseEvent?) {
        if (e == null) return
        val selected: SelectionSquare = findSquare(e.x, e.y) ?: return

        currSelected.isToggled = false
        selected.isToggled = true
        currSelected = selected

        revalidate()
        repaint()
    }
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseClicked(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    override fun mousePressed(e: MouseEvent?) {}
}